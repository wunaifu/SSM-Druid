package com.wnf.mqtt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketServiceLoader implements ServletContextListener {

    //public static MyMQTTClient client;
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        MyMQTTClient.start();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        MyMQTTClient.start();
    }
}