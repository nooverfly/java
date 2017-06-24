package cn.tarena.fh.controller;

import cn.tarena.fh.controller.websocket.MyWebSocketHandler;
import cn.tarena.fh.pojo.*;
import cn.tarena.fh.service.*;
import cn.tarena.fh.tool.MD5Hash;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
@Scope("prototype")
public class LoginController {

    @Resource
    MyWebSocketHandler handler;

    @Autowired
    private UserService userService;


    Map<String, MessageUser> users = new HashMap<String, MessageUser>();

    @Autowired
    private MessageUserService  messageUserService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageNewService messageNewService;
    @Autowired
    private LogInfoService logInfoService;

    private MessageUser msgUser = new MessageUser();

    // 从数据库得到所有的用户数据
    @ModelAttribute
    public void setReqAndRes() {
        List<MessageUser> messageUser = messageUserService.findAll();
        for (MessageUser u : messageUser) {
            users.put(u.getId(), u);

        }
    }








    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/_login")
    public String login(String userName, String password, HttpSession session){
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return "login";
        }
        //令牌
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        //请求的对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("session_user",user);


            //得到当前时间毫秒值
            long longTime = System.currentTimeMillis();
            //给msgUser赋值
            msgUser.setId(user.getUserId());
            msgUser.setName(userName);
            msgUser.setState(1);
            msgUser.setLongTime(longTime);
            msgUser.setHeadurl(user.getHeadUrl());
            msgUser.setDeptName(user.getDept().getDeptName());
            messageUserService.updateStateOneUser(msgUser);//将数据库里的状态改变
            users.put(msgUser.getId(),msgUser);//将users里的状态改成1 表示已经在线
            session.setAttribute("messageUser", msgUser);//将登录的用户信息放进session
            session.setAttribute("users",users);//将所有用户信息放进session
            List<Message> messageList = messageService.findMessageOne(msgUser.getId());//得到所有未收到的信息
            session.setAttribute("messageList", messageList);//保存到session中登录后显示未接受的信息



            String msg = UUID.randomUUID().toString();
            LogInfo logInfo =new LogInfo();
            logInfo.setUserId(msgUser.getId());
            logInfo.setNumber(msg);
            logInfo.setUsername(msgUser.getName());
            logInfo.setLoginTime(new Date());
            logInfoService.toLogin(logInfo);
            session.setAttribute("msg1",msg);
            return "redirect:/_index.action";
        } catch (Exception e){
            return "login";
        }


    }






    // 跳转到实时信息接收小窗口
    @RequestMapping(value = "toMessageNew")
    public ModelAndView messageNew(HttpSession session ){
        List<MessageNew> messageNew = messageNewService.findMessageNew(msgUser.getId());
        session.setAttribute("messageNew", messageNew);


        return new ModelAndView("views/messageNew");//跳转到实时信息页面

    }
    //系统退出
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        String numbe = (String) httpSession.getAttribute("msg1");
        User user = (User) httpSession.getAttribute("session_user");
        LogInfo logInfo = new LogInfo();
        logInfo.setUserId(user.getUserId());
        logInfo.setNumber(numbe);
        logInfo.setUsername(user.getUsername());
        logInfo.setLogoutTime(new Date());
        logInfoService.toLogout(logInfo);

        messageUserService.updateStateOne(msgUser.getName(),0);


        //httpSession.invalidate();
        httpSession.removeAttribute("session_user");
        httpSession.removeAttribute("messageUser");
        httpSession.removeAttribute("users");
        httpSession.removeAttribute("msg1");


        return "redirect:/login.html";
    }















    @RequestMapping("/_index")
    public String goIndex(){
        return "index";
    }

    @RequestMapping("/mlogin")
    @ResponseBody
    public String mlogin(String userName,String password) throws JsonProcessingException {
        System.out.println("==============================================================================");
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return "error";
        }
        //令牌
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        //请求的对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            String json = new ObjectMapper().writeValueAsString(user);
            System.out.println(json);
            return json;
        }catch (Exception e){
            return "error";
        }
    }
}
