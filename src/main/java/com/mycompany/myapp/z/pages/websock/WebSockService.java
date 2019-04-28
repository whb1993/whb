package com.mycompany.myapp.z.pages.websock;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mycompany.myapp.z.pages.util.LinuxConnetionHelper;
import com.mycompany.myapp.z.pages.websock.dto.ActivityShellDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.time.Instant;

import static com.mycompany.myapp.config.WebsocketConfiguration.IP_ADDRESS;

/**
 * shell连接websock
 * @author wanghongbin
 */
@Controller
public class WebSockService implements ApplicationListener<SessionDisconnectEvent> {

    private static final Logger log = LoggerFactory.getLogger(WebSockService.class);

    private final SimpMessageSendingOperations messagingTemplate;
    private Session session;
    private LinuxConnetionHelper linuxConnetionHelper;

    public WebSockService(
        SimpMessageSendingOperations messagingTemplate
    ) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        ActivityShellDTO activityDTO = new ActivityShellDTO();
        activityDTO.setSessionId(event.getSessionId());
        activityDTO.setPage("logout");
        messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
    }

    @MessageMapping("/topic/sendMes")
    @SendTo("/topic/reviveMes")
    public ActivityShellDTO sendActivity(@Payload ActivityShellDTO activityDTO, StompHeaderAccessor stompHeaderAccessor, Principal principal) {
        activityDTO.setUserLogin(principal.getName());
        activityDTO.setSessionId(stompHeaderAccessor.getSessionId());
        activityDTO.setIpAddress(stompHeaderAccessor.getSessionAttributes().get(IP_ADDRESS).toString());
        activityDTO.setTime(Instant.now());
        activityDTO.setMes("处理成功");
        log.debug("Sending user tracking data {}", activityDTO);
        return activityDTO;
    }
    @MessageMapping("/topic/shell")
    @SendTo("/topic/reviceShell")
    public ActivityShellDTO shellActivity(@Payload ActivityShellDTO activityDTO, StompHeaderAccessor stompHeaderAccessor, Principal principal) {
//        String[] cmds, Session session, int timeout, int sleepTimeout
        String[] cmds = {"ls","ls","ls"};
        try {
            activityDTO.setMes(LinuxConnetionHelper.execShellCmd(cmds, session, 1000, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("Sending user tracking data {}", activityDTO);
        return activityDTO;
    }
    @MessageMapping("/topic/startShell")
    @SendTo("/topic/reviveMes")
    public ActivityShellDTO startShell(@Payload ActivityShellDTO activityDTO, StompHeaderAccessor stompHeaderAccessor, Principal principal) {
//        String host, String userName, String password, int port
        String host = "144.34.228.229";
        String userName = "root";
        String password = "wjT5Tu2eStYM";
        int port = 28274;

        try {
            session = LinuxConnetionHelper.longConnect(host, userName, password, port);

        } catch (JSchException e) {
            e.printStackTrace();
        }
        activityDTO.setTime(Instant.now());
        activityDTO.setMes("成功创建连接");
        log.debug("Sending user tracking data {}", activityDTO);
        return activityDTO;
    }

    /**
     * 每60秒发送一下当前时间
     */
    @Scheduled(cron = "0/60 * * * * ?")
    public void sendInfo() {
        messagingTemplate.convertAndSend("/topic/reviveMes",
            Instant.now());
    }
}
