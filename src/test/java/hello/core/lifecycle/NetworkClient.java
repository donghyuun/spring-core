package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서버 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {//연결된 상태에서 연결된 서버에 메시지 던짐ㅁ
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    public void init() throws Exception {//afterProperties, 즉 의존관계 주입 이후 실행되는 메서드
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() throws Exception {//destroy, 즉 스프링 빈 소멸 직전 실행되는 메서드
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
