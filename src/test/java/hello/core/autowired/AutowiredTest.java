package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        //*Autowired 의 requried 옵션값은 default: true 임 => 자동 주입할 대상 없으면 에러 발생
        @Autowired(required = false) // required = false => 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출이 안됨
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);

        }
        @Autowired //@Nullable => 자동 주입할 대상이 없으면 null이 입력됨
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired // Optional<> => 자동 주입할 대상이 없으면 Optional.empty가 입력됨
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }



}
