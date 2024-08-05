package com.hello.core;

import com.hello.core.Order.OrderService;
import com.hello.core.member.MemberService;
import com.hello.core.Order.OrderServiceImpl;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.discount.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import com.hello.core.member.MemoryMemberRepository;

public class AppConfig {
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("Call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
    @Bean
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}