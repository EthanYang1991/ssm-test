package cn.etao.ssm;

import cn.etao.ssm.activemq.ActiveMqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yangli on 2016/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class TestActiveMQ {

    @Resource
    ActiveMqService activeMqService;

    @Test
    public void test1() {
        activeMqService.asynSendMessage("test_name","123");


        System.out.println("-----------------aa---------------" +"");
    }

}
