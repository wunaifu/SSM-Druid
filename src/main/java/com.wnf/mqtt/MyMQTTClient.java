package com.wnf.mqtt;

import com.wnf.util.Util;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;

public class MyMQTTClient {

    public static final String HOST = "tcp://113.106.8.199:61613";//MQTT服务端IP以及连接端口
    public static final String TOPIC = "Ozone";//订阅主题
    private static final String clientid = "1623808";//客户端ID
    private static MqttClient client;
    private static MqttConnectOptions options;
    private static String userName = "admin";//MQTT服务端连接账号
    private static String passWord = "password";//MQTT服务端连接密码

    public static void start() {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("链接断开，请进行重连---------");
                    //链接断开，进行重连操作
//                    try {
//                        client.connect(options);
//                        //订阅消息
//                        client.subscribe(TOPIC, 1);
//                    } catch (MqttException e) {
//                        e.printStackTrace();
//                    }
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("************************************* 此次接收信息开始 *****************************************");
                    System.out.println(new Date());
                    System.out.println("订阅主题topic:" + topic);
                    System.out.println("方式Qos:" + message.getQos());
                    System.out.println("信息内容message content:" + new String(message.getPayload()));
                    // 创建消息
//                    MqttMessage message1 = new MqttMessage("自己给自己发消息".getBytes());
//                    // 设置消息的服务质量
//                    message1.setQos(qos);
//                    // 发布消息
//                    client.publish(topic, message1);
                    String receiveString = new String(message.getPayload()).trim();
                    String[] handledString = receiveString.split(",");

                    if (Util.getJSONType(receiveString)) {
                        //获取app的开关指令，发给硬件
                        //获取app的开关指令，发给硬件
                        System.out.println("收到App开关指令，发送给硬件");
                        // 创建消息
                        MqttMessage message1 = new MqttMessage("100006".getBytes());
                        // 设置消息的服务质量
                        message1.setQos(1);
                        // 发布消息
                        client.publish("1623808", message1);

                    } else if (receiveString.contains("OK")) {
                        //获取硬件回发的OK，更改相应开关状态
                        System.out.println("获取硬件回发的OK，更改相应开关状态");
                        for (String x : handledString) {
                            System.out.println(x);
                        }
                    } else if (handledString.length > 1) {//获取电压电流数据，更新数据库电压电流信息
                        System.out.println("获取硬件回发电流数据，更新数据库电压电流信息");
                        for (String x : handledString) {
                            System.out.println(x);
                        }
                    } else {
                        System.out.println("输出信息不对");
                    }
                    System.out.println("************************************* 此次接收信息结束 *****************************************");
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("交互完成---------" + token.isComplete());
                }

            });
            client.connect(options);
            //订阅消息
            client.subscribe(TOPIC, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws MqttException {
//        MyMQTTClient client1 = new MyMQTTClient();
//        client1.start();
//    }

}
