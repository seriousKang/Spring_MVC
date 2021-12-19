package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        // 세션 생성
        MockHttpServletResponse resp = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, resp);

        // 요청에 응답 쿠키 저장
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCookies(resp.getCookies());

        // 세션 조회
        Object result = sessionManager.getSession(req);
        Assertions.assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(req);
        Object expired = sessionManager.getSession(req);
        Assertions.assertThat(expired).isEqualTo(null);
    }
}
