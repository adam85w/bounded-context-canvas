package net.adam85w.ddd.boundedcontextcanvas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;
import net.adam85w.ddd.boundedcontextcanvas.template.html.InvalidTemplateFileNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String INTERNAL_ERROR_SERVER_MESSAGE = "Internal server error";

    private static final String VALIDATION_ERROR_MESSAGE = "Value of the field: %s is invalid against rule: %s";

    private final ApplicationContext applicationContext;

    GlobalExceptionHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ExceptionHandler(value = HandlerMethodValidationException.class)
    ResponseEntity<ErrorResponse> handleJsonValidationException(HttpServletRequest request, HandlerMethodValidationException exception) {
        LOGGER.warn(exception.getMessage(), exception);
        final MessageSourceResolvable error = exception.getAllErrors().get(0);
        if (error instanceof FieldError) {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationContext, request.getRequestURI(), String.format(VALIDATION_ERROR_MESSAGE, name, message)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationContext, request.getRequestURI(), error.getDefaultMessage()));
    }

    @ExceptionHandler(value = InvalidTemplateFileNameException.class)
    ResponseEntity<ErrorResponse> handleInvalidTemplateNameException(HttpServletRequest request, InvalidTemplateFileNameException exception) {
        LOGGER.warn(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationContext, request.getRequestURI(), exception.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleInvalidEnumValueException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        LOGGER.warn(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationContext, request.getRequestURI(), exception.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ErrorResponse> handleNotSpecifiedException(HttpServletRequest request, Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(applicationContext, request.getRequestURI(), INTERNAL_ERROR_SERVER_MESSAGE));
    }
}
