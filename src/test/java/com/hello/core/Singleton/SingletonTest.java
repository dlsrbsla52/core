package com.hello.core.Singleton;

import com.hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import com.hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {


    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appconfig = new AppConfig();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appconfig.memberService();;

        // 2. 조회: 호출할 대 마다 객체를 생성
        MemberService memberService2 = appconfig.memberService();;

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertNotSame(memberService1, memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void 싱글톤서비스테스트(){
        SingletonsService instance1 = SingletonsService.getInstance();
        SingletonsService instance2 = SingletonsService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        Assertions.assertSame(instance1, instance2);
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void 싱글톤컨테이너(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);;

        // 2. 조회: 호출할 대 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);;

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertSame(memberService1, memberService2);
    }
}
