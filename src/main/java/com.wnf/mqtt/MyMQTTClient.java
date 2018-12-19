package com.wnf.mqtt;

import com.wnf.util.Util;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;
import java.util.Random;

public class MyMQTTClient {

    public final String HOST = "tcp://127.0.0.1:61613";//MQTT服务端IP以及连接端口
    public final String TOPIC = "MyTest123/+/h";//订阅主题
    private final String clientid = "serverTest"+new Random().nextInt(99);//客户端ID
    private MqttClient client;
    private MqttConnectOptions options;
    private final String userName = "admin";//MQTT服务端连接账号
    private final String passWord = "password";//MQTT服务端连接密码

    public void start() {
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
            //options.setAutomaticReconnect(true);
            // 设置回调函数
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("链接断开，请进行重连---------");
                    MyMQTTClient myMQTTClient=new MyMQTTClient();
                    try {
                        while (true) {
                            //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
                            if (!client.isConnected()) {
//                                client.close();
                                //client.connect(options);
//                                myMQTTClient.start();
//                                System.out.println("关闭再重新连接");
//                            } else {//这里的逻辑是如果连接成功就重新连接
                                client.disconnect();
                                client.close();
                                client.connect(options);
//                                myMQTTClient.start();
                                System.out.println("断开再重新连接");
                            }
                            if (client.isConnected()) {
                                System.out.println("连接成功");
                                break;
                            }
                        }
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
//                        if (!client.isConnected()) {
//                            client.close();
//                            //client.connect(options);
//                            MyMQTTClient.start();
//                            System.out.println("连接成功");
//                        }else {//这里的逻辑是如果连接成功就重新连接
//                            client.disconnect();
//                            client.close();
//                            //client.connect(options);
//                            MyMQTTClient.start();
//                            System.out.println("连接成功");
//                        }
//                        client.disconnect();
//                        client.connect(options);
//                        System.out.println("连接成功");
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

    public static void main(String[] args) throws MqttException {
        MyMQTTClient client1 = new MyMQTTClient();
        client1.start();
    }

}
