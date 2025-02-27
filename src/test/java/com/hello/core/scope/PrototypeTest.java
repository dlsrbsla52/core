package com.hello.core.scope;

import org.junit.jupiter.api.Test;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

//        Assertions.assertSame(prototypeBean1, prototypeBean2);
        Assertions.assertNotSame(prototypeBean1, prototypeBean2);

        ac.close();
    }


    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("ProtoType.init");
        }

        @PreDestroy // prototype(은) 실행되지 않음
        public void destroy(){
            System.out.println("ProtoType.destroy");
        }
    }
}
