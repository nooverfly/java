package cn.tarena.fh.tool;

import cn.tarena.fh.pojo.User;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/6/4.
 */
public class LeaveTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        /**从新查询当前用户，再获取当前用户对应的领导*/
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User)session.getAttribute("session_user");
        String name = user.getUserInfo().getManager().getName();
        delegateTask.setAssignee(name);
    }
}
