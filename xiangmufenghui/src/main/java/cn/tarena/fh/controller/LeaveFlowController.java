package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.LeaveBill;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.LeaveBillService;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/6/4.
 */
@Controller
@RequestMapping("/LBflow")
public class LeaveFlowController {

    @Autowired
    private LeaveBillService leaveBillService;

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("/toaddqj")
    public String toaddqj(){
        return "flow/leave/addLeave";
    }

    @RequestMapping("/addqj")
    public String addqj(LeaveBill leaveBill){
        leaveBillService.saveLeaveBill(leaveBill);
        System.out.println("请假申请提交");
        return "redirect:/workflow/flowList";
    }

    @RequestMapping("/_maddLB")
    @ResponseBody
    public String maddLB(LeaveBill leaveBill){
        leaveBillService.saveLeaveBill(leaveBill);
        return "success";
    }

    @RequestMapping("/toupdateLB")
    public String toupdateLB(Integer id,Model model){
        if(id!=null){
            LeaveBill bill = leaveBillService.findLeaveBillById(id);
            model.addAttribute("lBill",bill);
        }
        return "flow/leave/updateLeave";
    }

    @RequestMapping("deleteLB")
    public String deleteLB(Integer id){
        leaveBillService.deleteLeaveBillById(id);
        return "redirect:/workflow/flowList";
    }

    @RequestMapping("/updateLB")
    public String updateLB(LeaveBill leaveBill){
        leaveBillService.updateLeaveBill(leaveBill);
        return "redirect:/workflow/flowList";
    }
}
