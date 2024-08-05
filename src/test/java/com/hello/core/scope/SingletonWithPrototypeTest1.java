package com.hello.core.scope;

import lombok.Getter;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertEquals(1, prototypeBean1.getCount());

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertEquals(1, prototypeBean2.getCount());
    }


    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);


        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        assertEquals(1, clientBean1.logic());

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        assertEquals(1, clientBean2.logic());

//        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean{
        private final Provider<PrototypeBean> prototypeBeanObjectProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    @Getter
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        @PostConstruct
        public void inti(){
            System.out.println("PrototypeBean.init"+ this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }
}
