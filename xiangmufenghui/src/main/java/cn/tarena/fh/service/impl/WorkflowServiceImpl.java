package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.LeaveBillMapper;
import cn.tarena.fh.pojo.LeaveBill;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.pojo.WorkflowBean;
import cn.tarena.fh.service.WorkflowService;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/4.
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private LeaveBillMapper leaveBillMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

    /**
     * 更新请假状态，启动流程实例，让启动的流程实例关联业务
     * @param workflowBean 封装了流程信息的实体
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveStartProcess(WorkflowBean workflowBean,String username) {
        Integer id = workflowBean.getId();
        LeaveBill leaveBill = leaveBillMapper.findOne(id);
        //更新请假单状态（初始录入-->审核中）
        leaveBill.setState(1);
        leaveBillMapper.updateState(leaveBill.getId(),leaveBill.getState());
        //使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
        String key = leaveBill.getClass().getSimpleName();
        //从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("inputUser",username);
        //使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
        String objId = key+"."+id;
        variables.put("objId",objId);
        //使用流程定义的key，启动流程实例，同时设置流程变量
        runtimeService.startProcessInstanceByKey(key,objId,variables);
    }

    @Override
    public List<LeaveBill> findLBListByUID(String userId) {
        return leaveBillMapper.findLBListByUID(userId);
    }

    @Override
    public List<Task> findTaskListByName(String user_name) {
        return taskService.createTaskQuery().taskAssignee(user_name).list();
    }

    @Override
    public String findTaskFormKeyByTaskId(String taskId) {
        TaskFormData formData = formService.getTaskFormData(taskId);
        //获取Form key的值
        String url = formData.getFormKey();
        return url;
    }

    /**一：使用任务ID，查找请假单ID，从而获取请假单信息*/
    @Override
    public LeaveBill findLeaveBillByTaskId(String taskId) {
        //1：使用任务ID，查询任务对象Task
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //2：使用任务对象Task获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //4：使用流程实例对象获取BUSINESS_KEY
        String businessKey = pi.getBusinessKey();
        //5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
        String id = "";
        if(StringUtils.isNotBlank(businessKey)){
            //截取字符串，取buniness_key小数点的第2个值
            id = businessKey.split("\\.")[1];
        }
        //查询请假单对象
        LeaveBill leaveBill = leaveBillMapper.findOne(id);
        return leaveBill;
    }

    /**二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中*/
    @Override
    public List<String> findOutComeListByTaskId(String taskId) {
        //返回存放连线的名称集合
        List<String> list = new ArrayList<String>();
        //1:使用任务ID，查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //2：获取流程定义ID
        String processDefinitionId = task.getProcessDefinitionId();
        //3：查询ProcessDefinitionEntiy对象
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //使用任务对象Task获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        //获取当前活动的id
        String activityId = pi.getActivityId();
        //4：获取当前的活动
        ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
        //5：获取当前活动完成之后连线的名称
        List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
        if(pvmList!=null && pvmList.size()>0){
            for(PvmTransition pvm:pvmList){
                String name = (String) pvm.getProperty("name");
                if(StringUtils.isNotBlank(name)){
                    list.add(name);
                }
                else{
                    list.add("默认提交");
                }
            }
        }
        return list;
    }
    /**获取批注信息，传递的是当前任务ID，获取历史任务ID对应的批注*/
    @Override
    public List<Comment> findCommentByTaskId(String taskId) {
        List<Comment> list = new ArrayList<Comment>();
        //使用当前的任务ID，查询当前流程对应的历史任务ID
        //使用当前任务ID，获取当前任务对象
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        list = taskService.getProcessInstanceComments(processInstanceId);
        return list;
    }

    /**指定连线的名称完成任务*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSubmitTask(WorkflowBean workflowBean,String username) {
        //获取任务ID
        String taskId = workflowBean.getTaskId();
        //获取连线的名称
        String outcome = workflowBean.getOutcome();
        //批注信息
        String message = workflowBean.getComment();
        //获取请假单ID
        Integer id = workflowBean.getId();
        LeaveBill bill = leaveBillMapper.findOne(id);
        //使用任务ID，查询任务对象，获取流程流程实例ID
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
        Authentication.setAuthenticatedUserId(username);
        taskService.addComment(taskId, processInstanceId, message);
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("days",bill.getDays());
        if(outcome!=null && !outcome.equals("默认提交")){
            variables.put("outcome", outcome);
        }
        variables.put("yj",workflowBean.getYj());
        //使用任务ID，完成当前人的个人任务，同时流程变量
        taskService.complete(taskId, variables);

        // 5：在完成任务之后，判断流程是否结束
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        //流程结束了
        if(pi==null||workflowBean.getYj().equals("驳回")){
            //更新请假单表的状态从1变成2（审核中-->审核完成）
            bill.setState(2);
            leaveBillMapper.updateState(bill.getId(),bill.getState());
        }
    }

    @Override
    public List<Comment> findCommentByLeaveBillId(Integer id) {
        String objName = LeaveBill.class.getSimpleName();
        String objId = objName+"."+id;
        /**2:使用历史的流程变量查询，返回历史的流程变量的对象，获取流程实例ID*/
        HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery()//对应历史的流程变量表
                .variableValueEquals("objId", objId)//使用流程变量的名称和流程变量的值查询
                .singleResult();
        //流程实例ID
        String processInstanceId = hvi.getProcessInstanceId();
        List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
        return list;
    }
}
