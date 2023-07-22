package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.*;

//메모리 버전의 구현 클래스(구현)
@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);//key: member.getId(), value: Member 객체
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);//key가 memberId인 Member객체(value)를 찾아서 반환
    }
}
