package com.hello.core.autowired;

import org.junit.jupiter.api.Test;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.AutoAppConfig;
import com.hello.core.discount.DiscountPolicy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AllBeanTest {

    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discount = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertInstanceOf(DiscountService.class, discountService);
        assertEquals(discount, 1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertEquals(rateDiscountPrice, 2000);
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policys;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policys) {
            this.policyMap = policyMap;
            this.policys = policys;
            System.out.println("policyMap = " + this.policyMap);
            System.out.println("policys = " + this.policys);
        }


        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
