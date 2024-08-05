package com.hello.core.beanfind;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

    @Test
    @DisplayName("부모타입으로 조회, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDup(){
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입으로 조회, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertEquals(bean.getClass(), RateDiscountPolicy.class);

    }


    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanbySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertEquals(bean.getClass(), RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        assertEquals(beans.size(), 2);
        for(String key : beans.keySet()){
            System.out.println("key = " + key + ", bean = " + beans.get(key));
        }
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        for(String key : beans.keySet()){
            System.out.println("key = " + key + ", bean = " + beans.get(key));
        }
    }


    @Configuration
    static class Config {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixDiscountPolicy();
        }


    }

}
