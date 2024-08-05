package com.hello.core.order;

import org.junit.jupiter.api.Test;
import com.hello.core.Order.Order;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.Order.OrderServiceImpl;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.member.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertEquals(order.getDiscountPrice(), 1000);
    }

}