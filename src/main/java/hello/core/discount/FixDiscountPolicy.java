package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){//VIP면
            return discountFixAmount;//1000원 할인
        }else{//VIP아니면
            return 0;//0원 할인
        }
    }
}
