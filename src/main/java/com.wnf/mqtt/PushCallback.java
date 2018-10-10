package com.wnf.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class PushCallback implements MqttCallback {
    public PushCallback() {
    }

    NewMQTTClient service;

    public PushCallback(NewMQTTClient service) {
        this.service = service;
    }

    //连接丢失：一般用与重连如果发生丢失，就会调用这里
    public void connectionLost(Throwable throwable) {
        while (true){
            try {//如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(1000);
                service.init();
                break;
            }catch (Exception e){
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
