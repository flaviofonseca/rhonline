package br.com.rhonline.core.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

}
