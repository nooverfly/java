package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.LogInfo;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.LogInfoService;
import cn.tarena.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
@Controller
public class LogInfoController extends BaseController{
    @Autowired
    private LogInfoService logInfoService ;
    @Autowired
    private UserService userService;
    //查询所有记录
    @RequestMapping("/logInfo/list")
    public String toListlogInfo( Model model){
        List<LogInfo> logInfoList = logInfoService.findAll();
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("logInfoList",logInfoList);
        return "/logInfo/logInfoList";
    }
    //根据userId查询记录
    @RequestMapping("/logInfo/select")
    public String tofindLogInfoByUserId(String userId,Model model){
        List<LogInfo> logList = logInfoService.findAll();
        List<LogInfo> logInfoList = logInfoService.findOne(userId);
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("logList",logList);
        model.addAttribute("logInfoList",logInfoList);
        model.addAttribute("userId",userId);
     return "/logInfo/logInfoListByUser";
    }
}
