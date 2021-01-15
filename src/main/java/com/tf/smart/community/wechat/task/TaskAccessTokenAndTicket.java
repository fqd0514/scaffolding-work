package com.tf.smart.community.wechat.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时获取accessToken
 * @Author LeeYoung
 * @Date: 2020/12/18
 */
@Component
@Slf4j
public class TaskAccessTokenAndTicket {

//    /**
//     * 登录相关Service
//     */
//    @Autowired
//    private LoginService loginService;
//
//    /***
//     * 定时获取accessToken和ticket (每一个半小时)
//     * @return void
//     * @Author Leeyoung
//     * @Date 2020/12/20
//     **/
//    @Scheduled(fixedRate = 5400*1000,initialDelay = 5400*1000)
//    public void buildAccessTokenAndTicketTask() {
//        log.info("定时任务——开始构建AccessToken 和 ticket.....");
//
//        loginService.buildAccessTokenAndTicket();
//
//        log.info("定时任务——AccessToken 和 ticket 构建完毕....");
//    }
}
