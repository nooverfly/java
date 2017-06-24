package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.CommitLog;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.CommitLogService;
import cn.tarena.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
public class CommitLogController {
    @Autowired
    private CommitLogService commitLogService;
    @Autowired
    private UserService userService;
    @RequestMapping("/commitLog/list")
    public String toCommitList(Model model){
        List<CommitLog> commitLogList = commitLogService.findAll();
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("commitLogList",commitLogList);
        return "/commitLog/commitLogList";
    }
    @RequestMapping("/commitLog/select")
    public String findOne(String userId,Model model){
        List<CommitLog> commitList = commitLogService.findAll();
        List<CommitLog> commitLogList = commitLogService.findOne(userId);
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("commitList",commitList);
        model.addAttribute("commitLogList",commitLogList);
        model.addAttribute("userId",userId);
        return "/commitLog/commitLogListByUser";
    }
}
