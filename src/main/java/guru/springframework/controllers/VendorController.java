package guru.springframework.controllers;


import guru.springframework.api.v1.model.ListVendorDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description="This is a vendor controller to perform vendor management")
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value="This api to get all vendors", notes="All vendors are retrieved")
    @GetMapping({"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public ListVendorDTO getVendors(){
        return new ListVendorDTO(vendorService.getVendors());
    }

    @ApiOperation(value="Endpoint to get a vendor by id", notes="Vendor info by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable final Long id){
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value="Endpoint to add a new vendor", notes="Add new vendor")
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO addVendor(@RequestBody final VendorDTO vendorDTO){
        return vendorService.addVendor(vendorDTO);
    }

    @ApiOperation(value="Endpoint to update vendor", notes="Updates vendor info")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable final Long id, @RequestBody final VendorDTO vendorDTO){
        return vendorService.updateVendor(id, vendorDTO);
    }

    @ApiOperation(value="Endpoint to delete a vendor", notes="Deletes a vendor")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendor(@PathVariable final Long id){
        vendorService.deleteById(id);
    }
}
