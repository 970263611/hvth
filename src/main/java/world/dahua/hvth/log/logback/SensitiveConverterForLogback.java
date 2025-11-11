package world.dahua.hvth.log.logback;


import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.helpers.MessageFormatter;
import world.dahua.hvth.desensitizer.DesensitizerManager;
import world.dahua.hvth.utils.BeanUtil;

import java.util.Arrays;

/**
 * auth: dahua
 * time: 2025/11/10 14:09
 */
public class SensitiveConverterForLogback extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        Object[] array = event.getArgumentArray();
        if (array == null) {
            return event.getFormattedMessage();
        }
        DesensitizerManager desensitizerManager = BeanUtil.getBean(DesensitizerManager.class);
        String[] resultAry = Arrays.stream(array).map(obj -> desensitizerManager.desensitize(obj)).toArray(String[]::new);
        return MessageFormatter.arrayFormat(event.getMessage(), resultAry).getMessage();
    }
}
