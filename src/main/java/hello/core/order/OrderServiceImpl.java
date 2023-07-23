package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //추상(인터페이스)에만 의존 => DIP준수!!!
    private final MemberRepository memberRepository;//final 은 값이 무조건 있어야 한고, 한번 초기화되면 변경할 수 없는 상수값이 된다. 생성자 주입 방식만 final 키워드 사용 가능
    private final DiscountPolicy discountPolicy;

    @Autowired
    private DiscountPolicy rateDiscountPolicy;
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
//스프링 빈 등록(이때 생성자도 호출됨) -> 의존 관계 주입 순서이므로, 함수 호출 순서는: OrderServiceImpl 함수 -> setMemberRepository 함수 & setDiscountPolicy 함수 순으로 호출됨