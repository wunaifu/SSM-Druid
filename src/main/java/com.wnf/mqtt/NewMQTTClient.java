package com.wnf.mqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.annotation.PostConstruct;
import java.util.Random;

public class NewMQTTClient {
    public static final String HOST = "tcp://127.0.0.1:61613";//MQTT服务端IP以及连接端口
    public static final String TOPIC = "MyTest123";//订阅主题
    private static final String clientId = "server"+new Random().nextInt(99);//客户端ID
    private static MqttClient client;
    private static MqttConnectOptions options;
    private static String account = "admin";//MQTT服务端连接账号
    private static String password = "password";//MQTT服务端连接密码
    //生成配置对象，用户名，密码等
    public MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(account);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        //options.setAutomaticReconnect(true);
        return options;
    }
    public void connect() throws MqttException {
        //防止重复创建MQTTClient实例
        if (client==null) {
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            client.setCallback(new PushCallback(NewMQTTClient.this));
        }
        MqttConnectOptions options = getOptions();
        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
        if (!client.isConnected()) {
            client.connect(options);
            System.out.println("连接成功");
        }else {//这里的逻辑是如果连接成功就重新连接
            client.disconnect();
            client.connect(options);
            System.out.println("连接成功");
        }
    }
    //监听设备发来的消息
//PostConstruct确保该函数在类被初始化时调用
    @PostConstruct
    public void init() {
        try {
            connect();
            //getMessage是我自己封装的一个订阅主题的函数，对于聪明的你们，应该很简单吧
            //订阅消息
            client.subscribe(TOPIC, 1);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws MqttException {
        NewMQTTClient client1 = new NewMQTTClient();
        client1.init();
    }
}
