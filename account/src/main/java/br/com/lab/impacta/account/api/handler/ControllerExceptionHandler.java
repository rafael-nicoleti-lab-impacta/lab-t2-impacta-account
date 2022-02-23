package br.com.lab.impacta.account.api.handler;

import br.com.lab.impacta.account.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.AccountWithoutBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String MESSAGE_ERROR_DEFAULT = "Não foi possível processar sua requisição!";

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountNotFoundException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> withoutBalanceException(AccountWithoutBalanceException exception) {
        return getErrorMessageResponse(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException exception) {
        return getErrorMessageResponse(exception.getMessage(), MESSAGE_ERROR_DEFAULT, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(String message,
                                                                         String description,
                                                                         HttpStatus httpStatus) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                httpStatus.value(),
                new Date(),
                message,
                description);

        return new ResponseEntity<>(errorMessageResponse, httpStatus);
    }
}
