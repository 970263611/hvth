package world.dahua.hvth.desensitizer;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import world.dahua.hvth.base.SensitiveType;

/**
 * auth: dahua
 * time: 2025/11/10 11:07
 */
@Component
public class DesensitizerLoader implements BeanPostProcessor {

    private final DesensitizerStorage storage;

    public DesensitizerLoader(DesensitizerStorage storage) {
        this.storage = storage;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Desensitizer) {
            Desensitizer desensitizer = (Desensitizer) bean;
            storage.register(desensitizer.type(), desensitizer);
        }
        return bean;
    }

    @Component
    class None implements Desensitizer {
        @Override
        public String desensitize(String sensitisation) {
            return sensitisation;
        }

        @Override
        public SensitiveType type() {
            return SensitiveType.NONE;
        }
    }


    @Component
    @ConditionalOnMissingBean(Desensitizer.FinancialAccount.class)
    class FinancialAccount implements Desensitizer.FinancialAccount {
        @Override
        public SensitiveType type() {
            return SensitiveType.FINANCIAL_ACCOUNT;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.EnterpriseAccountName.class)
    class EnterpriseAccountName implements Desensitizer.EnterpriseAccountName {
        @Override
        public SensitiveType type() {
            return SensitiveType.ENTERPRISE_ACCOUNT_NAME;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.Email.class)
    class Email implements Desensitizer.Email {
        @Override
        public SensitiveType type() {
            return SensitiveType.EMAIL;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.DsszSuffix.class)
    class DsszSuffix implements Desensitizer.DsszSuffix {
        @Override
        public SensitiveType type() {
            return SensitiveType.DSSZ_SUFFIX;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.DsszIndex.class)
    class DsszIndex implements Desensitizer.DsszIndex {
        @Override
        public SensitiveType type() {
            return SensitiveType.DSSZ_INDEX;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.ChineseName.class)
    class ChineseName implements Desensitizer.ChineseName {
        @Override
        public SensitiveType type() {
            return SensitiveType.CHINESE_NAME;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.DsszPrefix.class)
    class DsszPrefix implements Desensitizer.DsszPrefix {
        @Override
        public SensitiveType type() {
            return SensitiveType.DSSZ_PREFIX;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.Address.class)
    class Address implements Desensitizer.Address {
        @Override
        public SensitiveType type() {
            return SensitiveType.ADDRESS;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.Amount.class)
    class Amount implements Desensitizer.Amount {
        @Override
        public SensitiveType type() {
            return SensitiveType.AMOUNT;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.BankCard.class)
    class BankCard implements Desensitizer.BankCard {
        @Override
        public SensitiveType type() {
            return SensitiveType.BANK_CARD;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.Date.class)
    class Date implements Desensitizer.Date {
        @Override
        public SensitiveType type() {
            return SensitiveType.DATE;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.CarLicense.class)
    class CarLicense implements Desensitizer.CarLicense {
        @Override
        public SensitiveType type() {
            return SensitiveType.CAR_LICENSE;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.VatNo.class)
    class VatNo implements Desensitizer.VatNo {
        @Override
        public SensitiveType type() {
            return SensitiveType.VATNO;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.VatBankNo.class)
    class VatBankNo implements Desensitizer.VatBankNo {
        @Override
        public SensitiveType type() {
            return SensitiveType.VATBANKNO;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.UserId.class)
    class UserId implements Desensitizer.UserId {
        @Override
        public SensitiveType type() {
            return SensitiveType.USER_ID;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.Password.class)
    class Password implements Desensitizer.Password {
        @Override
        public SensitiveType type() {
            return SensitiveType.PASSWORD;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.PassportNo.class)
    class PassportNo implements Desensitizer.PassportNo {
        @Override
        public SensitiveType type() {
            return SensitiveType.PASSPORTNO;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.PassbookAccount.class)
    class PassbookAccount implements Desensitizer.PassbookAccount {
        @Override
        public SensitiveType type() {
            return SensitiveType.PASSBOOK_ACCOUNT;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.MobilePhone.class)
    class MobilePhone implements Desensitizer.MobilePhone {
        @Override
        public SensitiveType type() {
            return SensitiveType.MOBILE_PHONE;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.IdCard.class)
    class IdCard implements Desensitizer.IdCard {
        @Override
        public SensitiveType type() {
            return SensitiveType.ID_CARD;
        }
    }

    @Component
    @ConditionalOnMissingBean(Desensitizer.FixedPhone.class)
    class FixedPhone implements Desensitizer.FixedPhone {
        @Override
        public SensitiveType type() {
            return SensitiveType.FIXED_PHONE;
        }
    }
}
