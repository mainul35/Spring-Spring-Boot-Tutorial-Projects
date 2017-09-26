package com.springtutorials.JdbcManager;

public class QueryException extends Exception{
    
    /**
     * 
     */
    private static final long serialVersionUID = -6175840509656452568L;
    private String message;
    private Throwable cause;
    
    public QueryException(String message, Throwable cause) {
        super(message,cause);
        this.setMessage(message);
        this.setCause(cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}

