package world.dahua.hvth.log.logback;


import ch.qos.logback.classic.PatternLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;

/**
 * auth: dahua
 * time: 2025/11/12 09:56
 */
public class LogConverterRegister4Logback implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (ClassUtils.isPresent("ch.qos.logback.classic.PatternLayout", getClass().getClassLoader())) {
            PatternLayout.defaultConverterMap.put("m", SensitiveConverter4Logback.class.getName());
            PatternLayout.defaultConverterMap.put("msg", SensitiveConverter4Logback.class.getName());
            PatternLayout.defaultConverterMap.put("message", SensitiveConverter4Logback.class.getName());
            PatternLayout.defaultConverterMap.put(SensitiveConverter4Logback.class.getName(), "message");
        }
    }
}
