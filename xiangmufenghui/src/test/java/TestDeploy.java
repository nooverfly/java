import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.impl.UserServiceImpl;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/5.
 */
public class TestDeploy {

    @Test
    public void testDeploy(){
        ProcessEngine processEngine =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("processes/leavebill.bpmn")
                .addClasspathResource("processes/leavebill.png").deploy();
    }
}
