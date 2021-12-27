package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {
    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("throw exception!");
    }

    @GetMapping("/error-400")
    public void error400(HttpServletResponse resp) throws IOException {
        resp.sendError(400, "400 error!");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse resp) throws IOException {
        resp.sendError(404, "404 error!");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse resp) throws IOException {
        resp.sendError(500);
    }
}
