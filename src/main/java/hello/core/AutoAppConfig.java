package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(//@Component가 붙은 클래스들을 찾아서 스프링 빈으로 등록해줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)//@Configuration(@Component 가짐)은 이미 수동으로 추가된 빈들을 가지므로
)
public class AutoAppConfig {

}
