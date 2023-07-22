package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(//@Component가 붙은 클래스들을 찾아서 스프링 빈으로 등록해줌
        basePackages = "hello.core.member",//컴포넌트 탐색 위치 지정, 이 부분부터 하위로 컴포넌트 스캔함
        basePackageClasses = AutoAppConfig.class,//해당 클래스가 속한 패키지부터 하위로 컴포넌트 스캔
        //=> 지정하지 않으면 @ComponentScan 이 속한 패키지부터 하위로 컴포넌트 스캔, CoreApplication의 @SpingBootApplication 안에 이미 @ComponentScan 있음 => 내가 따로 붙힐 필요가 없다.

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)//@Configuration(@Component 가짐)은 이미 수동으로 추가된 빈들을 가지므로
)
public class AutoAppConfig {
//    자동 빈 vs 수동 빈 충돌: 스프링에선 수동 빈이 자동 빈을 override, 스프링 부트에선 기본적으로 충돌 허용 X
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
