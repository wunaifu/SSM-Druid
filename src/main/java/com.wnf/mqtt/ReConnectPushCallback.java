package com.wnf.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class ReConnectPushCallback implements MqttCallback {
    public ReConnectPushCallback() {
    }

    ReConnectMQTTClient mqttConn;

    public ReConnectPushCallback(ReConnectMQTTClient service) {
        this.mqttConn = service;
    }

    //连接丢失：一般用与重连如果发生丢失，就会调用这里
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("[MQTT] 连接断开，30S之后尝试重连...");
        while(true) {
            try {
                Thread.sleep(30000);
                mqttConn.reConnect();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("************************************* 此次接收信息开始 *****************************************");
        System.out.println(new Date());
        System.out.println("订阅主题topic:" + topic);
        System.out.println("方式Qos:" + message.getQos());
        System.out.println("信息内容message content:" + new String(message.getPayload()));
        System.out.println("************************************* 此次接收信息结束 *****************************************");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("交互完成---------" + iMqttDeliveryToken.isComplete());
    }
}
