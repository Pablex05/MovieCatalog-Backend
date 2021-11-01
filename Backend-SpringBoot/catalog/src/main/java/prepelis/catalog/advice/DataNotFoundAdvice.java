package prepelis.catalog.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import prepelis.catalog.exception.DataNotFoundException;

import java.util.Date;

/**
 * @RestControllerAdvice is the combination of @ControllerAdvice and @ResponseBody
 * We can use the @ControllerAdvice annotation for handling exceptions in the RESTful Services,
 * but we need to add @ResponseBody separately.
 * it is a kind of interceptor that surrounds the logic in our Controllers and allows us to apply some common logic to them.
 */

@RestControllerAdvice
public class DataNotFoundAdvice {

    @ExceptionHandler(value = DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDataNotFoundAdviceException(DataNotFoundException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
