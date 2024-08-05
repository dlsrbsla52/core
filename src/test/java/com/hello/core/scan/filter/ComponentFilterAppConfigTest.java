package com.hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void 필터스캔(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertNotNull(beanA);

//        ac.getBean("beanB", BeanB.class);
        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("beanB", BeanA.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
