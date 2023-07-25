package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor//자동 생성자 생성 & 의존관계주입(Autowired)
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;//생성자 함수에서 @Autowired 되는것과 같음, but 현재 http요청이 없어 빈으로 등록되어 있지 않기 때문에 에러 발생

    @RequestMapping("log-demo")
    @ResponseBody//view 없이 문자 바로 반환
    public String logDemo(HttpServletRequest request) {//HttpServletRequest => 자바에서 제공하는 표준 규약을 이용해 고객 요청 정보를 받을 수 있음
        String requestURL = request.getRequestURL().toString();//고객이 어떤 URL로 요청했는지 알 수 있다.

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}