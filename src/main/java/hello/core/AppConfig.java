package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//공연 기힉자의 역할, 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택. 애플리케이션 동작의 전체 구성을 책임진다.
@Configuration
public class AppConfig {
    /*싱글톤이 아닐때 아래 메서드들을 한번씩 호출할때(discountPolicy 제외)*/
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    /*싱글톤일때 ...*/
    //call AppConfig.memberService
    //calL AppConfig.memberRepository
    //call AppConfig.orderService

    //생성자 주입 방법
    @Bean //스프링 컨테이너에 메서드이름을 key, 리턴값을 value 로 하여 등록됨
    public MemberService memberService(){//인터페이스(역할)
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());//구현클래스(구현)
    }

    @Bean
    public MemberRepository memberRepository() {//인터페이스(역할)
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();//구현클래스(구현)
    }

    //생성자 주입 방법
    @Bean
    public OrderService orderService(){//인터페이스(역할)
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());//구현클래스(구현)
    }

    @Bean
    public static DiscountPolicy discountPolicy() {//인터페이스(역할)
        return new RateDiscountPolicy();//구현클래스(구현)
    }
}
