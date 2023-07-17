package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //추상(인터페이스)에도 의존하고                        구체(구현 클래스)에도 의존하고 있다. => DIP 위반!
    private final MemberRepository memberRepository = new MemoryMemberRepository();//회원을 조회하기 위해
    private DiscountPolicy discountPolicy;//인터페이스에만 의존!!!
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
