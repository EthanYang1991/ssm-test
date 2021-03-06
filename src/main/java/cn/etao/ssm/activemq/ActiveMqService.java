package cn.etao.ssm.activemq;

import cn.etao.ssm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

/**
 * Created by yangli on 2016/8/17.
 */
@Service
public class ActiveMqService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination queueDestination;
    @Autowired
    private Destination serviceLogQueueDestination;
    //线上环境用线程池，可能导致cpu和内存暴涨，原因暂时不清楚
    //private static   ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    /**
     * 异步发送mq消息
     * @author hailongzhao
     * @date 20151023
     * @param message
     */
    public void asynSendMessage(final String sourceSys, final String message) {
        //线上环境用线程池，可能导致cpu和内存暴涨，原因暂时不清楚
//			fixedThreadPool.execute(new Runnable() {
//				@Override
//				public void run() {
//					synSendMessage(sourceSys,message);
//				}
//			});
        Thread dThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synSendMessage(sourceSys,message);
            }
        });
        dThread.setDaemon(false);
        dThread.start();
    }
    /**
     * 同步发送mq消息
     * @author hailongzhao
     * @date 20151023
     * @param message
     */
    public void synSendMessage(String sourceSys,final String message){
        try {
            jmsTemplate.send(queueDestination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("ActiveMq发送日志消息时出错：" + e.getMessage());
//            String stackTrace = StringUtils.getStackTrace(e);
//            List<String> ipinfoList = SystemUtils.getLocalIpInfo();
//            String appServerIP = JsonUtil.obj2string(ipinfoList);
//            SystemUtils.sendAlertEmail("ActiveMq_"+sourceSys+"_java项目预警", "appServerIP:"+appServerIP+"\n"+e.getMessage()+"\n"+stackTrace);
        }
    }
    /**
     * 异步发送mq服务日志消息
     * @author hailongzhao
     * @date 20151214
     * @param message
     */
    public void asynSendServiceLogMessage(final String message) {
        Thread dThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synSendServiceLogMessage(message);
            }
        });
        dThread.setDaemon(false);
        dThread.start();
    }
    /**
     * 同步发送mq服务日志消息
     * @author hailongzhao
     * @date 20151214
     * @param message
     */
    public void synSendServiceLogMessage(final String message){
        try {
            jmsTemplate.send(serviceLogQueueDestination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("ActiveMq发送service日志消息时出错：" + e.getMessage());
//            String stackTrace = StringUtils.getStackTrace(e);
//            List<String> ipinfoList = SystemUtils.getLocalIpInfo();
//            String appServerIP = JsonUtil.obj2string(ipinfoList);
//            SystemUtils.sendAlertEmail("ActiveMq_synSendServiceLogMessage_java项目预警", "appServerIP:"+appServerIP+"\n"+e.getMessage()+"\n"+stackTrace);
        }
    }
}
