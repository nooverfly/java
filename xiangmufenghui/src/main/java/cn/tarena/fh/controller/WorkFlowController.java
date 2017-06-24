package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.ActTask;
import cn.tarena.fh.pojo.LeaveBill;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.pojo.WorkflowBean;
import cn.tarena.fh.service.LeaveBillService;
import cn.tarena.fh.service.UserService;
import cn.tarena.fh.service.WorkflowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
@Controller
@RequestMapping("/workflow")
public class WorkFlowController {
    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private LeaveBillService leaveBillService;

    @Autowired
    private UserService userService;
    /**
     * 跳到流程选择界面
     * @return
     */
    @RequestMapping("/toselectflow")
    public String toselectFlow(){
        return "/flow/selectflow";
    }

    @RequestMapping("/flowList")
    public String toFlowList(HttpSession session, Model model){
        User user = (User) session.getAttribute("session_user");
        List<LeaveBill> list = workflowService.findLBListByUID(user.getUserId());
        model.addAttribute("lbList",list);
        return "flow/flowList";
    }

    @RequestMapping("/_mflowList")
    @ResponseBody
    public String mFlowList(String userId) throws JsonProcessingException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        List<LeaveBill> list = workflowService.findLBListByUID(userId);
        String json = new ObjectMapper().writeValueAsString(list);
        return json;
    }

    @RequestMapping("/startProcess")
    public String startProcess(WorkflowBean workflowBean, HttpSession httpSession){
        System.out.println("==============================================================");
        User user = (User) httpSession.getAttribute("session_user");
        workflowService.saveStartProcess(workflowBean,user.getUsername());
        return "redirect:/workflow/flowList";
    }

    @RequestMapping("/_mstartProcess")
    @ResponseBody
    public String mstartProcess(WorkflowBean workflowBean, String user_name){
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        workflowService.saveStartProcess(workflowBean,user_name);
        return "success";
    }

    @RequestMapping("/listTask")
    public String listTask(Model model,String user_name){
        List<Task> list = workflowService.findTaskListByName(user_name);
        model.addAttribute("taskList",list);
        return "flow/taskList";
    }

    @RequestMapping("/mlistTask")
    @ResponseBody
    public String mlistTask(String user_name) throws JsonProcessingException {
        List<Task> list = workflowService.findTaskListByName(user_name);
        List<ActTask> tasklist = new ArrayList<ActTask>();
        for (Task t:list){
            ActTask task = new ActTask();
            task.setId(t.getId());
            task.setName(t.getName());
            task.setAssignee(t.getAssignee());
            task.setCreateTime(t.getCreateTime());
            tasklist.add(task);
        }
        String json = new ObjectMapper().writeValueAsString(tasklist);
        return json;
    }

    @RequestMapping("/viewTaskForm")
    public String viewTaskForm(String taskId,Model model){
        model.addAttribute("taskId",taskId);
        /**一：使用任务ID，查找请假单ID，从而获取请假单信息*/
        LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
        System.out.println(leaveBill);
        model.addAttribute("leaveBill",leaveBill);
        /**二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中*/
        List<String> outcomeList = workflowService.findOutComeListByTaskId(taskId);
        System.out.println(outcomeList);
        model.addAttribute("outcomeList",outcomeList);
        /**三：查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment>*/
        List<Comment> commentList = workflowService.findCommentByTaskId(taskId);
        System.out.println(commentList);
        model.addAttribute("commentList",commentList);
        return "flow/leave/taskForm";
    }

    @RequestMapping("/mviewTaskForm")
    @ResponseBody
    public String mviewTaskForm(String taskId) throws JsonProcessingException {
        LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
        String JSON = new ObjectMapper().writeValueAsString(leaveBill);
        System.out.println("***********************************************");
        System.out.println(JSON);
        return JSON;
    }

    /**
     * 提交任务
     */
    @RequestMapping("submitTask")
    public String submitTask(WorkflowBean workflowBean,HttpSession session,Model model){
        User user = (User) session.getAttribute("session_user");
        System.out.println(user);
        workflowService.saveSubmitTask(workflowBean,user.getUsername());
        return "redirect:/test";
    }

    @RequestMapping("/msubmitTask")
    @ResponseBody
    public String msubmitTask(WorkflowBean workflowBean,String user_name,HttpSession session){
        User user = userService.findUserByUserName(user_name);
        session.setAttribute("session_user",user);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        workflowService.saveSubmitTask(workflowBean,user_name);
        return "success";
    }

    @RequestMapping("viewHisComment")
    public String viewHisComment(Integer id,Model model){
        LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
        model.addAttribute("leaveBill",leaveBill);
        List<Comment> comments = workflowService.findCommentByLeaveBillId(id);
        model.addAttribute("commentList",comments);
        return "flow/viewComment";
    }

    @RequestMapping("/mviewHisComment")
    @ResponseBody
    public String mviewHisComment(Integer id) throws JsonProcessingException {
        System.out.println(id);
        List<Comment> comments = workflowService.findCommentByLeaveBillId(id);
        String json = new ObjectMapper().writeValueAsString(comments);
        System.out.println(json);
        return json;
    }
}
