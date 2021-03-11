package cn.likoli.study.flowable.helloworld;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;

/**
 * ConfigProcessEngine
 *
 * @author liko
 * @date 2021/3/11
 */
public class ConfigProcessEngine {
    public static void main(String[] args) {
        // 默认使用classpath下的flowable.cfg.xml配置流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine.getName());
        System.out.println(processEngine.getProcessEngineConfiguration().getMailServerHost());

        // 通过编程的方式使用配置文件, 也可以是用不同的bean id
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
//        createProcessEngineConfigurationFromResource(String resource);
//        createProcessEngineConfigurationFromResource(String resource, String beanName);
//        createProcessEngineConfigurationFromInputStream(InputStream inputStream);
//        createProcessEngineConfigurationFromInputStream(InputStream inputStream, String beanName);

        // 不使用配置文件, 使用默认配置创建流程引擎
//        ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//        ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();

    }
}
