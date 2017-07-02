package cn.etao.ssm.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by yangli on 2016/8/17.
 */
public class LogConsumerMessageListener implements MessageListener {
    private static Logger logger = Logger.getLogger("test");
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        try {
            //System.out.println("接收到了消息，消息内容是：" + textMsg.getText());
            logger.info("接收到了消息，消息内容是：" + textMsg.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
