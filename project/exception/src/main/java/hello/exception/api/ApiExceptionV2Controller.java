package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
    /**
     * 정상 흐름으로 리턴 가능
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException ex) {
        log.error("[illegalExHandler] ex", ex);
        return new ErrorResult("BAD", ex.getMessage());
    }

    /**
     * annotation 내 `value`는 생략 가능
     *   - 생략 시, 메서드 파라미터의 예외가 지정
     * `ResponseEntity`를 사용할 경우, `@ResponseStatus`와는 다르게 동적으로 HTTP 응답 코드 변경 가능
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException ex) {
        log.error("[userExHandler] ex", ex);
        ErrorResult errorResult = new ErrorResult("USER-EX", ex.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    /**
     * 위에서 처리 못하는 예외들을 처리
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception ex) {
        log.error("[exHandler] ex", ex);
        return new ErrorResult("EX", "internal server error");
    }

    @GetMapping("/api2/member/{id}")
    public ApiExceptionController.MemberDto getMember(@PathVariable("id") String id) {
        if(id.equals("ex")) {
            throw new RuntimeException("invalid user");
        }

        if(id.equals("bad")) {
            throw new IllegalArgumentException("invalid input value");
        }

        if(id.equals("user-ex")) {
            throw new UserException("user exception");
        }

        return new ApiExceptionController.MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
