package world.dahua.hvth.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import world.dahua.hvth.base.SensitiveType;

import java.util.List;
import java.util.Set;

/**
 * auth: dahua
 * time: 2025/11/12 14:00
 */
@Component
@ConfigurationProperties(prefix = "hvth")
public class HvthProperties {

    private List<Rule> rules;
    private Set<String> excludeClass;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public Set<String> getExcludeClass() {
        return excludeClass;
    }

    public void setExcludeClass(Set<String> excludeClass) {
        this.excludeClass = excludeClass;
    }

    public static class Rule {

        private String regex;
        private SensitiveType type;

        public String getRegex() {
            return regex;
        }

        public void setRegex(String regex) {
            this.regex = regex;
        }

        public SensitiveType getType() {
            return type;
        }

        public void setType(SensitiveType type) {
            this.type = type;
        }
    }
}
