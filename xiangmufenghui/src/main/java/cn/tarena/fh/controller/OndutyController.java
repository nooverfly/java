package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.Onduty;
import cn.tarena.fh.service.OndutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
public class OndutyController extends BaseController {
    @Autowired
    private OndutyService ondutyService;

    @RequestMapping("toonduty")
    public String ondutyTable(Model model){

        List<Onduty> ondutyList = ondutyService.findAll();
        model.addAttribute("ondutyList",ondutyList);

        return "/onduty/onduty";
    }

    @RequestMapping("tosaveOnduty")
    public String saveOnduty(){

        return "/onduty/saveOnduty";
    }

    @RequestMapping("saveOnduty")
    public String saveOnduty(Onduty onduty){
        System.out.println(onduty.getOndutyTime());
        ondutyService.saveOnduty(onduty);

        return "redirect:/toonduty";
    }

    @RequestMapping("toupdateOnduty")
    public String updateOnduty(String ondutyId,Model model){
        Onduty onduty = ondutyService.findOndutyByOndutyId(ondutyId);

        System.out.println(onduty);

        model.addAttribute("onduty",onduty);
        model.addAttribute("ondutyId",ondutyId);
        return "/onduty/updateOnduty";
    }

    @RequestMapping("updateOnduty")
    public String updateOnduty(Onduty onduty,Model model){
        ondutyService.updateOnduty(onduty);
        return "redirect:/toonduty";
    }

    @RequestMapping("todeleteOnduty")
    public String deleteNotice(@RequestParam(value = "ondutyId",required = true) String[] ondutyIds){

        ondutyService.deleteOnduty(ondutyIds);

        return "redirect:/toonduty";
    }
}
