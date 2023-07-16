package hello.core.member;

//인터페이스(역할)
public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
