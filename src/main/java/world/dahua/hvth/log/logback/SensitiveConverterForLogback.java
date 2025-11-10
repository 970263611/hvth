package world.dahua.hvth.log.logback;


import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * auth: dahua
 * time: 2025/11/10 14:09
 */
public class SensitiveConverterForLogback extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return "";
    }
}
