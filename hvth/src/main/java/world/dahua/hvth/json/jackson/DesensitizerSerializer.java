package world.dahua.hvth.json.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import world.dahua.hvth.base.SensitiveField;
import world.dahua.hvth.base.SensitiveType;
import world.dahua.hvth.desensitizer.DesensitizerManager;
import world.dahua.hvth.utils.BeanUtil;

import java.io.IOException;

/**
 * auth: dahua
 * time: 2025/11/12 09:26
 */
public class DesensitizerSerializer extends JsonSerializer implements ContextualSerializer {

    private SensitiveField sensitiveField;

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SensitiveType sensitiveType = sensitiveField.type();
        DesensitizerManager desensitizerManager = BeanUtil.getBean(DesensitizerManager.class);
        String desensitize = desensitizerManager.desensitize(sensitiveType, o);
        jsonGenerator.writeString(desensitize);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty == null) {
            return serializerProvider.findNullValueSerializer(null);
        }
        SensitiveField sensitiveField = beanProperty.getAnnotation(SensitiveField.class);
        if (sensitiveField != null) {
            DesensitizerSerializer desensitizerSerializer = new DesensitizerSerializer();
            desensitizerSerializer.setSensitiveField(sensitiveField);
            return desensitizerSerializer;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }

    public void setSensitiveField(SensitiveField sensitiveField) {
        this.sensitiveField = sensitiveField;
    }
}
