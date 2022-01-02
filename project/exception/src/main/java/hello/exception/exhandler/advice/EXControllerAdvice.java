package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 대상 미적용(글로벌 적용)
 */
@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api")
public class EXControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException ex) {
        log.error("[illegalExHandler] ex", ex);
        return new ErrorResult("BAD", ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException ex) {
        log.error("[userExHandler] ex", ex);
        ErrorResult errorResult = new ErrorResult("USER-EX", ex.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception ex) {
        log.error("[exHandler] ex", ex);
        return new ErrorResult("EX", "internal server error");
    }
}
