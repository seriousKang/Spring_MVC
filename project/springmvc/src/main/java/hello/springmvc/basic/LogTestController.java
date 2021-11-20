package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String LogTest() {
        String name = "Spring";

        log.trace("trace name = {}", name);
        log.debug("debug name = {}", name);
        log.info("info name = {}", name);
        log.warn("warn name = {}", name);
        log.error("error name = {}", name);

        return "ok";
    }
}
