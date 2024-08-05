package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.Order.Order;
import org.junit.jupiter.api.Test;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import com.hello.core.Order.OrderService;
import com.hello.core.member.MemberService;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertEquals(order.getDiscountPrice(), 1000);
    }



}
