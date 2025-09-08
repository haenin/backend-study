package com.haenin.section01.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.haenin.section01.aop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfiguration {

}
