package world.dahua.hvth.log.log4j;


import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;
import world.dahua.hvth.desensitizer.DesensitizerManager;
import world.dahua.hvth.utils.BeanUtil;

import java.util.Arrays;

/**
 * auth: dahua
 * time: 2025/11/10 14:16
 */
public class SensitiveConverterForLog4j implements RewritePolicy {

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
