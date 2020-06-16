package guru.springframework.controllers;


import guru.springframework.api.v1.model.ListVendorDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @RequestMapping({"/api/v1/vendors/", "/api/v1/vendors"})
    @ResponseStatus(HttpStatus.OK)
    public ListVendorDTO getVendors(){
        return new ListVendorDTO(vendorService.getVendors());
    }

    @RequestMapping("/api/v1/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable final Long id){
        return vendorService.getVendorById(id);
    }
}
