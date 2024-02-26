package net.adam85w.ddd.boundedcontextcanvas.api;

import jakarta.servlet.http.HttpServletRequest;
import net.adam85w.ddd.boundedcontextcanvas.template.html.InvalidTemplateNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String INTERNAL_ERROR_SERVER_MESSAGE = "Internal server error";

    private static final String VALIDATION_ERROR_MESSAGE = "Value of the field: %s is invalid against: %s";

    private final String applicationName;

    private final String applicationVersion;

    GlobalExceptionHandler(@Value("${application.name}") String applicationName, @Value("${application.version}") String applicationVersion) {
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleJsonValidationException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        LOGGER.warn(exception.getMessage(), exception);
        final FieldError fieldError = ((FieldError) exception.getBindingResult().getAllErrors().get(0));
        String fieldName = fieldError.getField();
        String message = fieldError.getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationName, applicationVersion, request.getRequestURI(), String.format(VALIDATION_ERROR_MESSAGE, fieldName, message)));
    }

    @ExceptionHandler(value = InvalidTemplateNameException.class)
    ResponseEntity<ErrorResponse> handleInvalidTemplateNameException(HttpServletRequest request, InvalidTemplateNameException exception) {
        LOGGER.warn(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationName, applicationVersion, request.getRequestURI(), exception.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleInvalidEnumValueException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        LOGGER.warn(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(applicationName, applicationVersion, request.getRequestURI(), exception.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ErrorResponse> handleNotSpecifiedException(HttpServletRequest request, Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(applicationName, applicationVersion, request.getRequestURI(), INTERNAL_ERROR_SERVER_MESSAGE));
    }
}
