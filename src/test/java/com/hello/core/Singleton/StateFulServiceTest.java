package com.hello.core.Singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    void 쓰레드풀한싱글톤서비스(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA: A사용자 10000원 주문
        stateFulService1.order("userA", 10000);
        //threadB : B사용자 20000원 주문
        stateFulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price  = stateFulService1.getPrice();
        System.out.println("price = " + price);

        assertEquals(price, 20000);
    }

    static class TestConfig{

        @Bean
        public StateFulService statefulService(){
            return new StateFulService();
        }
    }
}