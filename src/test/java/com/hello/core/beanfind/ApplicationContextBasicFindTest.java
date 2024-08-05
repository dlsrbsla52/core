package com.hello.core.beanfind;

import com.hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertEquals(memberService.getClass(), MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertEquals(memberService.getClass(), MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        Assertions.assertEquals(memberService.getClass(), MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){

//        ac.getBean("xxxx", MemberService.class);
        try{
            ac.getBean("xxxx", MemberService.class);
        }catch (Exception e){
            Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                    () -> ac.getBean("xxxx", MemberServiceImpl.class));
        }
    }



}
