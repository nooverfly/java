package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.Notice;
import cn.tarena.fh.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/tonotice")
    public String findAll(Model model){
        List<Notice> noticeList = noticeService.findAll();
        model.addAttribute("noticeList",noticeList);
        return "/notice/notice";
    }

    //查看公告
    @RequestMapping("/toViewNotice")
    public String getNotice(String noticeId,Model model){

        Notice notice = noticeService.findNoticeByNoticeId(noticeId);
        model.addAttribute("notice",notice);
        model.addAttribute("noticeId",noticeId);
        return "/notice/toViewNotice";
    }

    //根据noticeId更新公告
    @RequestMapping("toupdateNotice")
    public String updateNotice(String noticeId,Model model){

        //准备数据
        //根据noticeId查询notice回显
        Notice notice = noticeService.findNoticeByNoticeId(noticeId);
        model.addAttribute("notice",notice);
        model.addAttribute("noticeId",noticeId);
        return "/notice/updateNotice";
    }

    @RequestMapping("updateNotice")
    public String updateNotice(Notice notice ,String noticeId){

        noticeService.updateNoticeByNoticeId(notice,noticeId);

        return "redirect:/tonotice";
    }

    @RequestMapping("tosaveNotice")
    public String saveNotice(){

        return "/notice/saveNotice";
    }

    @RequestMapping("saveNotice")
    public String saveNotice(Notice notice){

        noticeService.saveNotice(notice);

        return "redirect:/tonotice";
    }

    @RequestMapping("todeleteNotice")
    public String deleteNotice(@RequestParam(value = "noticeId",required = true) String[] noticeIds){
        noticeService.deleteNotice(noticeIds);
        return "redirect:/tonotice";
    }

    @RequestMapping("tosearchNotice")
    public String searchNotice(String message,Model model){

        List<Notice> noticeList = noticeService.findNoticeByNoticeTitleOrCreateBy(message);
        System.out.println(noticeList);
        model.addAttribute("noticeList",noticeList);

        return "/notice/notice";
    }
}
