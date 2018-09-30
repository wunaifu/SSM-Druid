package com.wnf.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Random;

/**
 *发布端
 */
public class PublishSample {

    String topic = "Ozone";
    String content = "{'ozoneId':'1623808','param':'100006','userId':'2'}";
    int qos = 1;
    String broker = "tcp://113.106.8.199:61613";
    String userName = "admin";
    String password = "password";
    String clientId = "pubClient"+new Random().nextInt(99);
    // 内存存储
    MemoryPersistence persistence = new MemoryPersistence();

    public PublishSample(String topic,String msg) {
        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(msg.getBytes());
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            sampleClient.publish(topic, message);
            // 断开连接
            sampleClient.disconnect();
            // 关闭客户端
            sampleClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String topic = "MyTest123";
        String content = "{'ozoneId':'1623808','param':'100006','userId':'2'}";
        new PublishSample(topic,content);

    }
}
