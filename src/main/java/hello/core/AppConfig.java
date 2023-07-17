package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//공연 기힉자의 역할, 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택. 애플리케이션 동작의 전체 구성을 책임진다.
public class AppConfig {

    //생성자 주입 방법
    public MemberService memberService(){//인터페이스(역할)
        return new MemberServiceImpl(memberRepository());//구현클래스(구현)
    }

    private static MemberRepository memberRepository() {//인터페이스(역할)
        return new MemoryMemberRepository();//구현클래스(구현)
    }

    //생성자 주입 방법
    public OrderService orderService(){//인터페이스(역할)
        return new OrderServiceImpl(memberRepository(), discountPolicy());//구현클래스(구현)
    }

    private static DiscountPolicy discountPolicy() {//인터페이스(역할)
        return new FixDiscountPolicy();//구현클래스(구현)
    }
}
