package guru.springframework.services;

import guru.springframework.api.v1.model.ListVendorDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;

import java.util.List;

public interface VendorService {

    public List<VendorDTO> getVendors();

    public VendorDTO getVendorById(final Long id);

    public VendorDTO addVendor(final VendorDTO vendor);

    public VendorDTO updateVendor(final Long id, final VendorDTO vendorDTO);

}
