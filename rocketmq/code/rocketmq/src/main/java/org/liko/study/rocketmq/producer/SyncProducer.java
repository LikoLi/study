package org.liko.study.rocketmq.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @Author liko
 * @Date 2019/2/15
 * @Version 1.0
 * @Description TODO
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // Instantiate with a producer group name
        DefaultMQProducer producer = new DefaultMQProducer("liko");

        // Specify name server address
        producer.setNamesrvAddr("localhost:9876");

        //Launch the instance.
        producer.start();

        for (int i = 0; i < 2; i++) {
            // Create a message instance, specifying topic, tag and message body
            Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */, ("Hello RocketMQ" + i).getBytes("UTF-8") /* Message body */);

            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
