package guru.springframework.ExceptionHandler;

public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException(final Long id){
        super("No vendor found with id:" + id);
    }


}
