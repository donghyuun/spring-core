package hello.core.member;

//구현 클래스(구현)
public class MemberServiceImpl implements MemberService{

    //추상(인터페이스)에만 의존!!! => DIP를 준수
    private final MemberRepository memberRepository;

    //생성자를 통해서 MemberRepository의 구현체가 들어감
    //=> 의존관계를 외부에서 주입 => DI(Dependency Injection, 의존관계주입)
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
