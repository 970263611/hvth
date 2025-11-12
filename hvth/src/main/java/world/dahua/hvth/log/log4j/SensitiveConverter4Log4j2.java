package world.dahua.hvth.log.log4j;


import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;
import world.dahua.hvth.desensitizer.DesensitizerManager;
import world.dahua.hvth.utils.BeanUtil;

import java.util.Arrays;

/**
 * auth: dahua
 * time: 2025/11/10 14:16
 */
@Plugin(name = "SensitiveConverter4Log4j", category = "Core", elementType = "rewritePolicy", printObject = true)
public class SensitiveConverter4Log4j2 implements RewritePolicy {

    @PluginFactory
    public static SensitiveConverter4Log4j2 create() {
        return new SensitiveConverter4Log4j2();
    }

    @Override
    public LogEvent rewrite(LogEvent logEvent) {
        if (!(logEvent instanceof Log4jLogEvent)) {
            return logEvent;
        }
        Log4jLogEvent log4jLogEvent = (Log4jLogEvent) logEvent;
        Message message = log4jLogEvent.getMessage();
        if (!(message instanceof ParameterizedMessage)) {
            return logEvent;
        }
        ParameterizedMessage parameterizedMessage = (ParameterizedMessage) message;
        Object[] params = parameterizedMessage.getParameters();
        if (params != null) {
            DesensitizerManager desensitizerManager = BeanUtil.getBean(DesensitizerManager.class);
            String[] resultAry = Arrays.stream(params).map(obj -> desensitizerManager.desensitize(obj)).toArray(String[]::new);
            return log4jLogEvent.asBuilder().setMessage(
                    new ParameterizedMessage(parameterizedMessage.getFormat(), resultAry, parameterizedMessage.getThrowable())
            ).build();
        }
        return logEvent;
    }
}
