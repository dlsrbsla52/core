package com.hello.core.beanDefinition;

import com.hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class beanDefinitionTest {

    AnnotationConfigApplicationContext acClass = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext acXml = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인 : 어노테이션 기반")
    void findApplicationBeanToAnnotations(){
        String[] beanDefinitionNames = acClass.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = acClass.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("빈 설정 메타정보 확인 : XML 기반")
    void findApplicationBeantoXml(){
        String[] beanDefinitionNames = acXml.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = acXml.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + beanDefinition);
            }
        }
    }
}
