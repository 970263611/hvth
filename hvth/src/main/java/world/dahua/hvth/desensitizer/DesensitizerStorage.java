package world.dahua.hvth.desensitizer;


import org.springframework.stereotype.Component;
import world.dahua.hvth.base.SensitiveType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * auth: dahua
 * time: 2025/11/10 13:58
 */
@Component
public class DesensitizerStorage {

    private Map<SensitiveType, Desensitizer> converters = new LinkedHashMap<>();

    public void register(SensitiveType type, Desensitizer desensitizer) {
        converters.put(type, desensitizer);
    }

    public Desensitizer findDesensitizer(SensitiveType sensitiveType) {
        Desensitizer desensitizer = converters.get(sensitiveType);
        if (desensitizer == null) {
            throw new IllegalArgumentException(sensitiveType.toString());
        }
        return desensitizer;
    }
}
