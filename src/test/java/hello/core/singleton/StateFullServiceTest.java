package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StateFullServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA: 사용자 A가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: 사용자 B가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자 A의 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        //스프링 빈은 항상 무상태(stateless)로 설계하자!, 필드대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}