package com.wnf.mqtt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketServiceLoader implements ServletContextListener {

    public MyMQTTClient client;
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        client.start();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        client.start();
    }
}