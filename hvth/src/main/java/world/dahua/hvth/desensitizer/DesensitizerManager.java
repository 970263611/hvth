package world.dahua.hvth.desensitizer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;
import world.dahua.hvth.base.SensitiveType;
import world.dahua.hvth.config.HvthProperties;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * auth: dahua
 * time: 2025/11/11 09:35
 */
public class DesensitizerManager {

    private static final String[] excludeClassNames = new String[]{
            "org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer",
            "com.psbc.cd.monitor.sdk.push.task.OutServicePushTaskCore",
            "com.psbc.cd.monitor.sdk.push.task.InnerServicePushTaskCore",
            "com.psbc.cd.monitor.sdk.push.task.TradePushTaskCore",
            "com.psbc.cd.monitor.sdk.push.task.PushTaskCore",
            "com.psbc.cd.monitor.sdk.push.task.CommonPushTaskCore",
            "com.netflix.loadbalancer.ILoadBalancer",
            "com.netflix.loadbalancer.IRule",
            "com.fasterxml.classmate.types.ResolvedObjectType"
    };
    private DesensitizerStorage desensitizerStorage;
    private ObjectMapper objectMapper;
    List<RegexDesensitizer> regexDesensitizers = new ArrayList<>();
    Set<Class> excludeClass = new HashSet<>();

    public DesensitizerManager(HvthProperties hvthProperties, DesensitizerStorage desensitizerStorage, ObjectMapper objectMapper) {
        this.desensitizerStorage = desensitizerStorage;
        this.objectMapper = objectMapper;
        List<HvthProperties.Rule> rules = hvthProperties.getRules();
        if (!ObjectUtils.isEmpty(rules)) {
            rules.forEach(rule -> regexDesensitizers.add(new RegexDesensitizer(Pattern.compile(rule.getRegex()), rule.getType())));
        }
        Set<String> excludeClasses = hvthProperties.getExcludeClass();
        if (excludeClasses == null) {
            excludeClasses = new HashSet<>();
        }
        excludeClasses.addAll(Arrays.asList(excludeClassNames));
        excludeClasses.forEach(clz -> {
            try {
                Class<?> aClass = Class.forName(clz);
                excludeClass.add(aClass);
            } catch (ClassNotFoundException e) {
            }
        });
    }

    public String desensitize(Object obj) {
        try {
            String objStr = objectMapper.writeValueAsString(obj);
            if (regexDesensitizers.isEmpty()) {
                return objStr;
            }
            Class<?> clz = obj.getClass();
            for (Class excludeClz : excludeClass) {
                if (clz.isAssignableFrom(excludeClz)) {
                    return objStr;
                }
            }
            StringBuffer processedStr = new StringBuffer(objStr);
            for (RegexDesensitizer regexDesensitizer : regexDesensitizers) {
                Pattern pattern = regexDesensitizer.getPattern();
                SensitiveType type = regexDesensitizer.getType();
                Matcher matcher = pattern.matcher(processedStr.toString());
                StringBuffer currentBuffer = new StringBuffer();
                while (matcher.find()) {
                    String originalStr = matcher.group();
                    String desensitizedStr = desensitize(type, originalStr);
                    matcher.appendReplacement(currentBuffer, Matcher.quoteReplacement(desensitizedStr));
                }
                matcher.appendTail(currentBuffer);
                processedStr = currentBuffer;
            }
            return processedStr.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String desensitize(SensitiveType sensitiveType, Object obj) {
        Desensitizer desensitizer = desensitizerStorage.findDesensitizer(sensitiveType);
        return desensitizer.desensitize(String.valueOf(obj));
    }

    class RegexDesensitizer {

        private Pattern pattern;
        private SensitiveType type;

        public RegexDesensitizer(Pattern pattern, SensitiveType type) {
            this.pattern = pattern;
            this.type = type;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        public SensitiveType getType() {
            return type;
        }

        public void setType(SensitiveType type) {
            this.type = type;
        }
    }
}
