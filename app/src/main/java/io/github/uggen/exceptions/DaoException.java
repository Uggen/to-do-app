package io.github.uggen.exceptions;

public class DaoException extends RuntimeException {
    public DaoException(Throwable throwable){
        super(throwable);
    }
}
