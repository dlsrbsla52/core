package com.hello.core.discount;

import com.hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @BeforeEach
    void setUp() {
        AppConfig appconfig = new AppConfig();

    }

    @Test
    @DisplayName("Vip는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertEquals(1000, discount);

    }


    @Test
    @DisplayName("Vip는 10% 할인이 적용되지 않아야 한다.")
    void vip_x(){
        // given
        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertEquals(0, discount);

    }


}