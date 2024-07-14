package com.hello.core.Singleton;

public class SingletonsService {

    private static final SingletonsService instance = new SingletonsService();

    public static SingletonsService getInstance() {
        return instance;
    }

    private SingletonsService() {

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
