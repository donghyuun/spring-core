package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    //할인되는 금액을 return
    int discount(Member member, int price);
}
