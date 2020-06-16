package guru.springframework.ExceptionHandler;

public class NoVendorsException extends RuntimeException {

    public NoVendorsException(){
        super("No Vendors in DB");
    }
}
