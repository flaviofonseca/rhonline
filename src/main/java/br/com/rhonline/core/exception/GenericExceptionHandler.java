package br.com.rhonline.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GenericExceptionHandler {


    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<CustomErro> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                   WebRequest request) {

        CustomErro customErro = new CustomErro("Erro no servidor ao tentar enviar uma propriedade não existente",
                ex.getMessage());

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<CustomErro> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                      WebRequest request) {

        Optional<String> reduce = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce((s, s2) -> s.concat("\n").concat(s2 != null ? s2 : ""));

        CustomErro customErro = new CustomErro(reduce.orElse(""), ex.getMessage());

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
                                                                       HttpHeaders headers,
                                                                       HttpStatus status,
                                                                       WebRequest request) {

        CustomErro customErro = new CustomErro(ex.getMessage(), ex.getMessage());
        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleCustomException(BusinessException ex,
                                                        WebRequest request) {

        CustomErro customErro = new CustomErro(ex.getMessage(), ex.getMessage());
        log.warn(ex.getMessage());

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleCustomException(Exception ex,
                                                        WebRequest request) {

        CustomErro customErro = new CustomErro(ex.getMessage(), ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleCustomPSQLException(SQLException ex,
                                                            WebRequest request) {

        CustomErro customErro = new CustomErro(ex.getMessage(), ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<CustomErro> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {

        CustomErro customErro = new CustomErro("Foi detectado a falta de um parametro na pesquisa", ex.getMessage());

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({javax.validation.ConstraintViolationException.class})
    public ResponseEntity<CustomErro> handleConstraintViolationException(javax.validation.ConstraintViolationException ex,
                                                                         WebRequest request) {

        CustomErro customErro = getCustomErroConstraintVioletException(ex);
        ex.printStackTrace();

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private CustomErro getCustomErroConstraintVioletException(ConstraintViolationException ex) {
        StringBuilder mensagem = new StringBuilder();
        ex.getConstraintViolations().forEach(valor -> mensagem.append(valor.getMessageTemplate()).append(".\n"));
        return new CustomErro(mensagem.toString(), mensagem.toString());
    }


    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    public ResponseEntity<Object> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex,
                                                                           WebRequest request) {

        CustomErro customErro = new CustomErro(ex.getMessage(), ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                        WebRequest request) {

        CustomErro customErro;
        if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException constraintViolationException = (org.hibernate.exception.ConstraintViolationException) ex.getCause();
            customErro = new CustomErro(constraintViolationException.getSQLException().getMessage(), ex.getMessage());
        } else {
            customErro = new CustomErro(ex.getMessage(), ex.getMessage());
        }

        return new ResponseEntity<>(customErro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
