package cn.tarena.fh.service;

import cn.tarena.fh.pojo.LeaveBill;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.pojo.WorkflowBean;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public interface WorkflowService {

    void saveStartProcess(WorkflowBean workflowBean,String username);

    List<LeaveBill> findLBListByUID(String userId);

    List<Task> findTaskListByName(String user_name);

    String findTaskFormKeyByTaskId(String taskId);

    LeaveBill findLeaveBillByTaskId(String taskId);

    List<String> findOutComeListByTaskId(String taskId);

    List<Comment> findCommentByTaskId(String taskId);

    void saveSubmitTask(WorkflowBean workflowBean,String username);

    List<Comment> findCommentByLeaveBillId(Integer id);
}
