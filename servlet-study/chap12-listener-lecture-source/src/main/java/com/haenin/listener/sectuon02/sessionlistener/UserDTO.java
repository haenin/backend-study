package com.haenin.listener.sectuon02.sessionlistener;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class UserDTO implements HttpSessionBindingListener {
    private String name;
    private int age;

    /* 설명. HttpSessionBindingListener는 각 class마다 별도로 작성 */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("UserDTO 객체가 session에 담김");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("UserDTO가 객체가 session에서 제거됨");
    }

    public UserDTO() {
    }

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
