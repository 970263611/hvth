package world.dahua.hvth.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import world.dahua.hvth.desensitizer.DesensitizerManager;
import world.dahua.hvth.desensitizer.DesensitizerStorage;

/**
 * auth: dahua
 * time: 2025/11/12 09:33
 */
@Configuration
@ComponentScan("world.dahua.hvth")
public class HvthAutoConfiguration {

    @Bean
    public DesensitizerManager desensitizerManager(HvthProperties hvthProperties, DesensitizerStorage desensitizerStorage, ObjectMapper objectMapper) {
        return new DesensitizerManager(hvthProperties, desensitizerStorage, objectMapper);
    }
}
