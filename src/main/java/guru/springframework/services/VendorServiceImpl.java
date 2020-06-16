package guru.springframework.services;

import guru.springframework.ExceptionHandler.ResourceNotFoundException;
import guru.springframework.ExceptionHandler.VendorNotFoundException;
import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(final VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getVendors() {
        return vendorRepository.findAll().stream()
                .map(ven -> {
                    final VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(ven);
                    vendorDTO.setVendorUrl("/api/v1/vendors/" + ven.getId() );
                    return vendorDTO;
                })
                .collect(toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        final Vendor vendor = vendorRepository.findVendorById(id)
                                .orElseThrow(() -> new VendorNotFoundException(id));
        final VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);
        vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
        return vendorDTO;
    }
}
