package world.dahua.hvth.desensitizer;


import world.dahua.hvth.SensitiveType;
import world.dahua.hvth.utils.StrUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * auth: dahua
 * time: 2025/11/10 10:06
 */
public interface Desensitizer {

    String desensitize(String sensitisation);

    SensitiveType type();

    interface Amount extends Desensitizer {
        @Override
        default String desensitize(String amt) {
            if (StrUtil.isBlank(amt)) {
                return new String();
            } else if (amt.length() == 1) {
                return "*";
            } else {
                return StrUtil.hide(amt, 0, amt.length() - 1);
            }
        }
    }

    interface UserId extends Desensitizer {
        @Override
        default String desensitize(String userId) {
            if (StrUtil.isBlank(userId)) {
                return new String();
            } else {
                return StrUtil.hide(userId, 0, 1);
            }
        }
    }

    interface ChineseName extends Desensitizer {
        @Override
        default String desensitize(String fullName) {
            if (StrUtil.isBlank(fullName)) {
                return new String();
            } else {
                // 姓名3个字及以内隐藏后一位，  4-6个字隐藏后两位，大于6个字隐藏 3-6位
                if (fullName.length() < 4) {
                    return StrUtil.hide(fullName, fullName.length() - 1, fullName.length());
                } else if (fullName.length() < 7) {
                    return StrUtil.hide(fullName, fullName.length() - 2, fullName.length());
                } else {
                    return StrUtil.hide(fullName, 2, 6);
                }
            }
        }
    }

    interface IdCard extends Desensitizer {
        @Override
        default String desensitize(String idCardNum) {
            if (!StrUtil.isBlank(idCardNum)) {
                // 身份证保留前六位和后四位
                if (idCardNum.length() != 18) {
                    return idCardNum.substring(0, 6) + "********" + idCardNum.substring(idCardNum.length() - 4);
                }
            }
            return new String();
        }
    }

    interface FixedPhone extends Desensitizer {
        @Override
        default String desensitize(String num) {
            // 座机号码保留后三位
            return StrUtil.isBlank(num) ? new String() : StrUtil.hide(num, 4, num.length() - 3);
        }
    }

    interface MobilePhone extends Desensitizer {
        @Override
        default String desensitize(String num) {
            // 手机号码保留前三位和最后4位
            return StrUtil.isBlank(num) ? new String() : StrUtil.hide(num, 3, num.length() - 4);
        }
    }

    interface Address extends Desensitizer {
        @Override
        default String desensitize(String address) {
            if (StrUtil.isBlank(address)) {
                return new String();
            } else {
                // 地址长度五位及以下的，保留前三个字；长度六个字及以上的保留前五个字
                int length = address.length();
                if (length < 6) {
                    return StrUtil.hide(address, 3, length);
                } else {
                    return StrUtil.hide(address, 5, length);
                }
            }
        }
    }

    interface Email extends Desensitizer {
        @Override
        default String desensitize(String email) {
            if (StrUtil.isBlank(email)) {
                return new String();
            } else {
                // @前小于等于5位的，隐藏前2位，大于五位的保留后三位
                int index = StrUtil.indexOf(email, '@');
                if (index < 6) {
                    return index <= 1 ? StrUtil.hide(email, 0, 1) : StrUtil.hide(email, 0, 2);
                } else {
                    return StrUtil.hide(email, 0, index - 3);
                }
            }
        }
    }

    interface Password extends Desensitizer {
        @Override
        default String desensitize(String password) {
            return StrUtil.isBlank(password) ? new String() : StrUtil.repeat('*', password.length());
        }
    }

    interface CarLicense extends Desensitizer {
        @Override
        default String desensitize(String carLicense) {
            if (StrUtil.isBlank(carLicense)) {
                return new String();
            } else {
                // 车牌保留前两位和最后两位
                return StrUtil.hide(carLicense, 2, carLicense.length() - 2);
            }
        }
    }

    interface BankCard extends Desensitizer {
        @Override
        default String desensitize(String bankCardNo) {
            if (StrUtil.isBlank(bankCardNo)) {
                return bankCardNo;
            } else {
                // 银行卡号保留前六位和后四位
                return StrUtil.hide(bankCardNo, 6, bankCardNo.length() - 4);
            }
        }
    }

    interface VatBankNo extends Desensitizer {
        @Override
        default String desensitize(String vatbankno) {
            if (StrUtil.isBlank(vatbankno)) {
                return new String();
            } else {
                // 增值税账号：保留最后4位，其余用*号代替
                if (vatbankno.length() > 4) {
                    return StrUtil.hide(vatbankno, 0, vatbankno.length() - 4);
                } else {
                    return vatbankno;
                }
            }
        }
    }

    interface VatNo extends Desensitizer {
        @Override
        default String desensitize(String vatno) {
            if (StrUtil.isBlank(vatno)) {
                return new String();
            } else {
                // 增值税税号：保留前4位和最后4位，中间用*代替
                if (vatno.length() > 8) {
                    return StrUtil.hide(vatno, 4, vatno.length() - 4);
                } else {
                    return vatno;
                }
            }
        }
    }

    interface PassbookAccount extends Desensitizer {
        @Override
        default String desensitize(String passbookAccount) {
            if (StrUtil.isBlank(passbookAccount)) {
                return new String();
            } else {
                // 存折账号：保留前4位和最后4位，中间用*代替
                if (passbookAccount.length() > 8) {
                    return StrUtil.hide(passbookAccount, 4, passbookAccount.length() - 4);
                } else {
                    return passbookAccount;
                }
            }
        }
    }

    interface FinancialAccount extends Desensitizer {
        @Override
        default String desensitize(String financialAccount) {
            if (StrUtil.isBlank(financialAccount)) {
                return new String();
            } else {
                // 金融账号：分段屏蔽，每隔2位用*代替2位
                StringBuffer str = new StringBuffer(financialAccount);
                int len = 0;
                if (financialAccount.length() % 2 == 0) {
                    for (int i = 0; i < financialAccount.length() / 2 / 2; i++) {
                        str.replace(len + 2, len + 4, "**");
                        len += 4;
                    }
                    return str.toString();
                } else {
                    for (int i = 0; i < (financialAccount.length() - 1) / 2 / 2; i++) {
                        str.replace(len + 2, len + 4, "**");
                        len += 4;
                    }
                    if ((financialAccount.length() - 1) / 2 % 2 == 0) {
                        return str.toString();
                    }
                    return str.replace(str.length() - 1, str.length(), "*").toString();
                }
            }
        }
    }

    interface Date extends Desensitizer {
        @Override
        default String desensitize(String date) {
            if (StrUtil.isBlank(date)) {
                return new String();
            } else {
                // 日期时间：数据格式为"年月日"，保留年月，截取剩余位数
                SimpleDateFormat sdf = new SimpleDateFormat("YYYYmmdd");
                try {
                    sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return new String();
                }
                return date.substring(0, 6);
            }
        }
    }

    interface PassportNo extends Desensitizer {
        @Override
        default String desensitize(String passportNo) {
            if (StrUtil.isBlank(passportNo)) {
                return new String();
            } else {
                // 护照号码：保留前4位，其余用*代替
                if (passportNo.length() > 4) {
                    return StrUtil.hide(passportNo, 4, passportNo.length());
                } else {
                    return passportNo;
                }
            }
        }
    }

    interface EnterpriseAccountName extends Desensitizer {
        @Override
        default String desensitize(String enterpriseAccountName) {
            if (StrUtil.isBlank(enterpriseAccountName)) {
                return new String();
            } else {
                // 企业户名:按长度阶梯保留：长度4个字及以下的，首尾各保留1个字；长度5-6个字的，首尾各保留2个字；长度7个字及以上奇数的，隐藏中间3个字；长度8个字及以上偶数，隐藏中间4个字，隐藏字段用*代替
                if (enterpriseAccountName.length() < 5) {
                    return StrUtil.hide(enterpriseAccountName, 1, enterpriseAccountName.length() - 1);
                } else if (enterpriseAccountName.length() < 7 && enterpriseAccountName.length() > 4) {
                    return StrUtil.hide(enterpriseAccountName, 2, enterpriseAccountName.length() - 2);
                } else if (enterpriseAccountName.length() >= 7 && enterpriseAccountName.length() % 2 != 0) {
                    return StrUtil.hide(enterpriseAccountName, (enterpriseAccountName.length() - 3) / 2, enterpriseAccountName.length() - (enterpriseAccountName.length() - 3) / 2);
                } else if (enterpriseAccountName.length() >= 8 && enterpriseAccountName.length() % 2 == 0) {
                    return StrUtil.hide(enterpriseAccountName, (enterpriseAccountName.length() - 4) / 2, enterpriseAccountName.length() - (enterpriseAccountName.length() - 4) / 2);
                } else {
                    return enterpriseAccountName;
                }
            }
        }
    }

    interface DsszPrefix extends Desensitizer {
        @Override
        default String desensitize(String dsszPrefix) {
            if (StrUtil.isBlank(dsszPrefix)) {
                return new String();
            } else {
                // 脱敏前50%:隐藏前50%字段用*代替
                return StrUtil.hide(dsszPrefix, 0, dsszPrefix.length() / 2);
            }
        }
    }

    interface DsszIndex extends Desensitizer {
        @Override
        default String desensitize(String dsszIndex) {
            if (StrUtil.isBlank(dsszIndex)) {
                return new String();
            } else {
                if (dsszIndex.length() % 2 == 0) {
                    return StrUtil.hide(dsszIndex, dsszIndex.length() / 2 / 2, dsszIndex.length() / 2 / 2 + dsszIndex.length() / 2);
                } else {
                    return StrUtil.hide(dsszIndex, dsszIndex.length() / 2 / 2 + 1, dsszIndex.length() / 2 / 2 + dsszIndex.length() / 2 + 1);
                }
            }
        }
    }

    interface DsszSuffix extends Desensitizer {
        @Override
        default String desensitize(String dsszSuffix) {
            if (StrUtil.isBlank(dsszSuffix)) {
                return new String();
            } else {
                // 脱敏后50%:隐藏后50%字段用*代替
                return StrUtil.hide(dsszSuffix, dsszSuffix.length() / 2, dsszSuffix.length());
            }
        }
    }
}
