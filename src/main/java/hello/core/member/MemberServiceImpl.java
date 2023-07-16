package hello.core.member;

//구현 클래스(구현)
public class MemberServiceImpl implements MemberService{

    //인터페이스(추상화) 의존                            &        구현체(구체화) 의존   => 둘 다 의존 => 되게 안좋음!(DIP 위반)
    private final MemberRepository memberRepository = new MemoryMemberRepository();//다형성 적용

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
