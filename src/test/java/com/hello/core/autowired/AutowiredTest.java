package com.hello.core.autowired;

import org.junit.jupiter.api.Test;
import jakarta.annotation.Nullable;
import com.hello.core.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("NoBean1" + noBean1);
        }

        @Autowired(required = false)
        public void setNoBean2(@Nullable Member NoBean2){
            System.out.println("NoBean2" + NoBean2);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> NoBean3){
            System.out.println("NoBean2" + NoBean3);
        }
    }

}
