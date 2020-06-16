package guru.springframework.services;

import guru.springframework.api.v1.model.ListVendorDTO;
import guru.springframework.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    public List<VendorDTO> getVendors();

    public VendorDTO getVendorById(final Long id);

}
