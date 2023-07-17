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
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//회원의 등급에 따른 할인 정책을 사용하기 위해
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();//할인정책 이걸로 바꾸면 클라이언트 코드에 영향 줌 => OCP 위반!
    //=> 저장소에서 회원을 찾아서 반환한 값을 아이템 가격과 함께 할인 정책에 넘겨주어 할인되는 가격을 구함
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
