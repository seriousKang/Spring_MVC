package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 로직의 일관성을 위해 interface로 구현
public interface ControllerV1 {
    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
