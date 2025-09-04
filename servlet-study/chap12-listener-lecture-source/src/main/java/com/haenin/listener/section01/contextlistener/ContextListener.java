package com.haenin.listener.section01.contextlistener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
//리스너는 톰캣 관할
@WebListener
public class ContextListener implements ServletContextListener,
        ServletContextAttributeListener {

    public ContextListener() {
        System.out.println("Context liistener 인스턴스 생성!");
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context init!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroy!!");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("arttribute add!!: " + event.getName());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("attribute remove!!");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("attribute replaced!");
    }


}
