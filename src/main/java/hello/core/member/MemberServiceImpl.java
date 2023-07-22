package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//구현 클래스(구현)
@Component
public class MemberServiceImpl implements MemberService{

    //추상(인터페이스)에만 의존!!! => DIP를 준수
    private final MemberRepository memberRepository;

    //생성자를 통해서 MemberRepository의 구현체가 들어감
    //=> 의존관계를 외부에서 주입 => DI(Dependency Injection, 의존관계주입)
    @Autowired //자동으로 스프링이 빈으로 등록된 것에서 MemberRepository 타입에 맞는 스프링 빈을 찾아서 (의존관계를)주입해준다! => ac.getBean(MemberRepository.class)와 유사
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
