package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //추상(인터페이스)에만 의존!!! => DIP를 준수
        //MemberService memberService = appConfig.memberService();//의존성이 주입된 MemberServiceImpl 객체를 반환

        //AppConfig의 환경설정 정보를 가지고 @Bean 붙은것들을 객체 생성해서 스프링 컨테이너(applicationContext)에 다 등록해서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링 컨테이너를 통해서 등록된 해당 객체를 찾아온다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//기본적으로 메서드 이름으로 등록됨

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
