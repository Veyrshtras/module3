package com.epam.esm.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Class {@code ErrorResponse} represents objects that will be returned as a response when an error is generated.
 *
 * @author Shohboz Juraev
 * @version 1.0
 */
@Getter
public class ErrorResponse {

    private HttpStatus status;
    private String errorCode;
    private String errorMessage;

    public ErrorResponse(HttpStatus status, String errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "HTTP Status: "+ status.value()+
                "\nresponse body\n{\n" +
                "\"errorMessage\"=\"" + errorMessage + "\"," +
                "\n\"errorCode\"=" + errorCode + "\n" +
                '}';
    }
}
