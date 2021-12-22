package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
@RestController
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if(session == null) {
            return "session not exist";
        }

        // 세션 데이터 출력
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            log.info("name = {}, value = {}", name, session.getAttribute(name));
        }

        // 세션 ID(`JSESSIONID`)
        log.info("sessionId = {}", session.getId());
        // 세션 유효 시간(초 단위)
        log.info("maxInactiveInterval = {}", session.getMaxInactiveInterval());
        // 새로 생성된 세션인지,
        // 아니면 이미 과거에 만들어졌고 클라이언트에서 서버로 sessionId(`JSESSIONID`)를 요청해서 조회된 세션인지 여부
        log.info("isNew = {}", session.isNew());

        // 세션 생성 일시
        long creationTime = session.getCreationTime();
        log.info("creationTime = {}, date creationTime = {}", creationTime, new Date(creationTime));

        // 세션과 연결된 사용자가 최근에 서버에 접근한 시간
        // 클라이언트에서 서버로 sessionId(`JSESSIONID`)를 요청한 경우 갱신
        long lastAccessedTime = session.getLastAccessedTime();
        log.info("lastAccessedTime = {}, date lastAccessedTime = {}", lastAccessedTime, new Date(lastAccessedTime));

        return "session info";
    }
}
