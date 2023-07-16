package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){//VIP면
            return price * discountPercent / 100;//금액의 10%를 할인
        }else{//VIP아니면
            return 0;//할인X
        }
    }
}
