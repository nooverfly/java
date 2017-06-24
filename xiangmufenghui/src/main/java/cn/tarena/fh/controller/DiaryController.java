package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.DiaryEntry;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.DiaryService;
import cn.tarena.fh.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
//日志录入
@Controller
public class DiaryController extends BaseController{
    @Autowired
    private DiaryService diaryService;
    @Autowired
   private UserService userService;
    //日记录入
    @RequestMapping("/diary/entry")
    public String toEntry(String username,String deptName,Model model){
        model.addAttribute("username",username);
        model.addAttribute("deptName",deptName);
        return "/diary/diaryEntry";
    }
    @RequestMapping("/diary/save")
    public String toSaveDiaryEntry(DiaryEntry diaryEntry,Model model) {
        if (StringUtils.isEmpty(diaryEntry.getWorkContent())|| StringUtils.isEmpty(diaryEntry.getOutcome())){
              model.addAttribute("errorInfo","工作内容和工作结果不能为空");
            return "forward:/diary/entry";

        }
        diaryService.saveDiaryEntry(diaryEntry);
        return "forward:/diary/entry";
    }
    //日志查询
    @RequestMapping("/diary/show")
    public String toView(String userId,Model model){
        List<DiaryEntry> diaryEntryList = diaryService.findByUserId(userId);
        model.addAttribute("diaryEntryList",diaryEntryList);
        model.addAttribute("userId",userId);
        return "/diary/showdiary";
    }
    //按时间段查询
    @RequestMapping("/diary/select")
    public String toSelectByTime(String userId, Date startTime,Date endTime,Model model){
     List<DiaryEntry> diaryEntryList = diaryService.findByTime(userId,startTime,endTime);
     model.addAttribute("diaryEntryList",diaryEntryList);
     model.addAttribute("userId",userId);
     model.addAttribute("startTime",startTime);
     model.addAttribute("endTime",endTime);
     return "/diary/showdiary";


    }

    //日记管理
    @RequestMapping("/diary/manage")
    public String toMenage(String deptName,Model model){
        List<DiaryEntry> diaryEntryList = diaryService.findByDeptName(deptName);
        List<User> userList = userService.findByDeptName(deptName);
        model.addAttribute("diaryEntryList",diaryEntryList);
        model.addAttribute("userList",userList);
        return "/diary/diaryManage";
    }

    //管理查询
    @RequestMapping("/diary/managefind")
    public String toFindManage(String deptName,String userId,Model model){
        List<User> userList = userService.findByDeptName(deptName);
        model.addAttribute("userList",userList);
        List<DiaryEntry> diaryEntryList = diaryService.findByUserId(userId);
        model.addAttribute("diaryEntryList",diaryEntryList);
        model.addAttribute("userId",userId);
        return "/diary/diaryManage";
    }
}
