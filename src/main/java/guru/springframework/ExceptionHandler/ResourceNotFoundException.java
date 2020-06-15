package guru.springframework.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){}

    public ResourceNotFoundException(final String message){
        super(message);
    }

    public ResourceNotFoundException(final String message, final Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(final Throwable cause){
        super(cause);
    }

    protected ResourceNotFoundException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


