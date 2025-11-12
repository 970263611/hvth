package world.dahua.hvth.entity;


import world.dahua.hvth.base.SensitiveField;
import world.dahua.hvth.base.SensitiveType;

public class DemoResponse {

    @SensitiveField(type = SensitiveType.ADDRESS)
    public String address;

    @SensitiveField(type = SensitiveType.EMAIL)
    public String email;

    @SensitiveField(type = SensitiveType.ENTERPRISE_ACCOUNT_NAME)
    private String enterpriseAcctNameByVoid;

    private String descByUnEnc;

    /**
     * encType优先级高于type
     */
    private String phoneByMd5;

    private String secretKeyBySM3;

    private String addressByRevBase64;

    private String orderDesc;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnterpriseAcctNameByVoid() {
        return enterpriseAcctNameByVoid;
    }

    public void setEnterpriseAcctNameByVoid(String enterpriseAcctNameByVoid) {
        this.enterpriseAcctNameByVoid = enterpriseAcctNameByVoid;
    }

    public String getDescByUnEnc() {
        return descByUnEnc;
    }

    public void setDescByUnEnc(String descByUnEnc) {
        this.descByUnEnc = descByUnEnc;
    }

    public String getPhoneByMd5() {
        return phoneByMd5;
    }

    public void setPhoneByMd5(String phoneByMd5) {
        this.phoneByMd5 = phoneByMd5;
    }

    public String getSecretKeyBySM3() {
        return secretKeyBySM3;
    }

    public void setSecretKeyBySM3(String secretKeyBySM3) {
        this.secretKeyBySM3 = secretKeyBySM3;
    }

    public String getAddressByRevBase64() {
        return addressByRevBase64;
    }

    public void setAddressByRevBase64(String addressByRevBase64) {
        this.addressByRevBase64 = addressByRevBase64;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
