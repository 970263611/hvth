package world.dahua.hvth.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.dahua.hvth.entity.DemoRequest;
import world.dahua.hvth.entity.DemoResponse;

@RestController
public class DemoController {

    Logger log = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/demo/request")
    public DemoResponse demo(@RequestBody DemoRequest demoRequest) {
        log.info("正则测试:{}", "18988887777汉东省滨海市南中大道");
        log.info("请求为:{}", demoRequest);
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setAddress("汉东省滨海市南中大道888号1栋777号");
        demoResponse.setEmail("1234567@qq.com");
        demoResponse.setPhoneByMd5("18988887777");
        demoResponse.setSecretKeyBySM3("664422222");
        demoResponse.setAddressByRevBase64("汉东省滨海市南中大道888号1栋777号");
        demoResponse.setDescByUnEnc("这是描述信息");
        demoResponse.setEnterpriseAcctNameByVoid("这是银行账户姓名");
        demoResponse.setOrderDesc("订单描述信息");
        log.info("响应为:{}", demoResponse);
        return demoResponse;
    }
}
