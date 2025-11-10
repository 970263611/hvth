package world.dahua.hvth.log.log4j;


import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * auth: dahua
 * time: 2025/11/10 14:16
 */
public class SensitiveConverterForLog4j extends LogEventPatternConverter {

    public SensitiveConverterForLog4j() {
        super("hvth", null);
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

    }
}
