package com.epam.esm.exceptions;

import com.epam.esm.configs.Translator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Iterator;
import java.util.Map;

import static com.epam.esm.exceptions.ExceptionCodes.*;
import static org.springframework.http.HttpStatus.*;

/**
 * Class {@code ExceptionsHandler} presents entity which will be returned from controller
 * in case generating exceptions
 *
 * @author Shohboz Juraev
 * @version 1.0
 */
@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(DuplicateEntityException.class)
    public final String handleDuplicateEntityExceptions(DuplicateEntityException ex) {
        String details = Translator.toLocale(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(CONFLICT, CONFLICT_EXCEPTION.toString(), details);
        return errorResponse.toString();
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public final String handleNoSuchEntityExceptions(NoSuchEntityException ex) {
        String details = Translator.toLocale(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND, NOT_FOUND_EXCEPTION.toString(), details);
        return errorResponse.toString();
    }

    @ExceptionHandler(IncorrectParameterException.class)
    public final String handleIncorrectParameterExceptions(IncorrectParameterException ex) {
        Iterator<Map.Entry<String, Object[]>> exceptions = ex.getExceptionResult().getExceptionMessages().entrySet().iterator();
        StringBuilder details = new StringBuilder();
        while (exceptions.hasNext()) {
            Map.Entry<String, Object[]> exception = exceptions.next();
            String message = Translator.toLocale(exception.getKey());
            String detail = String.format(message, exception.getValue());
            details.append(detail).append(' ');
        }

        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, BAD_REQUEST_EXCEPTION.toString(), details.toString());
        return errorResponse.toString();
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public final String handleUnsupportedOperationExceptions() {
        String details = Translator.toLocale("exception.unsupportedOperation");
        ErrorResponse errorResponse = new ErrorResponse(METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED_EXCEPTION.toString(), details);
        return errorResponse.toString();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, JsonProcessingException.class})
    public final String handleBadRequestExceptions() {
        String details = Translator.toLocale("exception.badRequest");
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, BAD_REQUEST_EXCEPTION.toString(), details);
        return errorResponse.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final String handleBadRequestException() {
        String details = Translator.toLocale("exception.noHandler");
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND, NOT_FOUND_EXCEPTION.toString(), details);
        return errorResponse.toString();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final String methodNotAllowedExceptionException() {
        String details = Translator.toLocale("exception.notSupported");
        ErrorResponse errorResponse = new ErrorResponse(METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED_EXCEPTION.toString(), details);
        return errorResponse.toString();
    }
}
