package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)//빈이 http 요청 당 하나씩 생성되고, http 요청이 끝나는 시점에 소멸함, 가짜 프록시 객체를 생성하는데, 이 객체는 내부에 진짜 myLogger 를 찾는 법을 알고  있다.
public class MyLogger {
    private String uuid;
    private String requestURL;
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    } public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " +
                message);
    }
    @PostConstruct
    public void init() {//=> http 요청 당 하나씩 생성되므로 uuid 를 저장해두면 다른 http 요청과 구분 가능
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}