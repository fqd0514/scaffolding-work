package com.tf.smart.community.wechat;

import com.alibaba.fastjson.JSON;
import com.tf.smart.community.wechat.common.utils.CacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

import static com.tf.smart.community.wechat.common.constant.CommonConstant.WECHAT_ACCESS_TOKEN;

/**
 * 应用测试
 *
 * @author 翟晶
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AppTest {

    /**
     * 服务号APPID
     */
    @Value("${wechat.appid}")
    private String publicAppid;

    /**
     * 服务号SecretId
     */
    @Value("${wechat.appsecret}")
    private String publicSecret;

    /**
     * 获取微信tokenURL
     */
    private final String tokenUrl = "";

    private String accToken = "03C71C58DB56542B88337E972EC5A7EF81CFCCAFCAB4AAF0F82E56199A6BC9E2";

    private String doorUrl = "http://123.151.209.34:18098/api/door/list?pageNo=1&pageSize=20&access_token={0}";

    private String openDoorUrl = "http://123.151.209.34:18098/api/door/remoteOpenById?doorId={0}&interval={1}&access_token={2}";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheUtil cacheUtil;
//
//    @Test
    public void contextLoads() {
//        String params[]={accToken};
//        String targetUrl= MessageFormat.format(doorUrl,params);
//        String ss = restTemplate.getForObject(targetUrl,String.class);
//        DoorInfoResult drs = JSON.parseObject(ss,DoorInfoResult.class);
//        List<DoorInfo> data = drs.getData();
//        DoorInfo doorInfo = data.get(0);
//
//        String openDoorId = doorInfo.getId();
//        String interval = "5";
//        String openDoorParams[]={openDoorId,interval,accToken};
//        String targetOpenDoorUrl= MessageFormat.format(openDoorUrl,openDoorParams);
//        System.out.println(targetOpenDoorUrl);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));
//        HttpEntity<String> strEntity = new HttpEntity<String>(JSON.toJSONString(new Object()),headers);
//        String ss2 = restTemplate.postForObject(targetOpenDoorUrl,strEntity,String.class);
//
//        System.out.println(ss2);
    }
}
