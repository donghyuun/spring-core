package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //추상(인터페이스)에만 의존 => DIP준수!!!
    private MemberRepository memberRepository;//final 은 값이 무조건 있어야 한고, 한번 초기화되면 변경할 수 없는 상수값이 된다.
    private DiscountPolicy discountPolicy;

    @Autowired(required = false)//MemberRepository가 스프링 빈에 등록되지 않았을 때도 사용가능, 필수값 X(선택적 의존관계 주입), default: true
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    //객체 의존관계가 주입됨 => DI(dependency injection)
    @Autowired //자동으로 스프링 컨테이너에서 해당 타입 객체 찾아서 (의존관계)주입, ac.getBean(MemberRepository.class), ac.getBean(DiscountPolicy) 와 유사, 생성자 하나면 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
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