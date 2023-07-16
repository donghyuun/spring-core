package hello.core.member;

//인터페이스(역할)
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
