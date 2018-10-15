package com.wnf.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.annotation.PostConstruct;
import java.util.Random;

public class ReConnectMQTTClient {
    public static final String host = "tcp://113.106.8.199:61613";//MQTT服务端IP以及连接端口
    public static final String TOPIC = "MyTest123";//订阅主题
    private static final String client_id = "server"+new Random().nextInt(99);//客户端ID
    private static MqttClient client;
    private static MqttConnectOptions option;
    private static String userName = "admin";//MQTT服务端连接账号
    private static String password = "password";//MQTT服务端连接密码
    public synchronized boolean connect() {
        try {
            if(null == client) {
                //host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，
                // MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttClient(host, client_id, new MemoryPersistence());
                //设置回调
                client.setCallback(new ReConnectPushCallback(ReConnectMQTTClient.this));
            }
            //获取连接配置
            getOption();
            client.connect(option);
            System.out.println("[MQTT] connect to Mqtt Server success...");
            return isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private void getOption() {
        //MQTT连接设置
        option = new MqttConnectOptions();
        //设置是否清空session,false表示服务器会保留客户端的连接记录，true表示每次连接到服务器都以新的身份连接
        option.setCleanSession(true);
        //设置连接的用户名
        option.setUserName(userName);
        //设置连接的密码
        option.setPassword(password.toCharArray());
        //设置超时时间 单位为秒
        option.setConnectionTimeout(10);
        //设置会话心跳时间 单位为秒 服务器会每隔(1.5*keepTime)秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        option.setKeepAliveInterval(20);
        option.setAutomaticReconnect(true);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
//            option.setWill(topic, "close".getBytes(), 2, true);
    }
    //断线重连
    public void reConnect() throws Exception {
        if(null != client) {
            client.connect(option);
        }
    }
    public boolean isConnected() throws Exception {
        client.subscribe(TOPIC, 1);
        return true;
    }
    public static void main(String[] args) throws MqttException {
        ReConnectMQTTClient client1 = new ReConnectMQTTClient();
        client1.connect();
    }
}
