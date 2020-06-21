package guru.springframework.services;

import guru.springframework.ExceptionHandler.NoVendorsException;
import guru.springframework.ExceptionHandler.ResourceNotFoundException;
import guru.springframework.ExceptionHandler.VendorNotFoundException;
import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.ListVendorDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public VendorDTO addVendor(VendorDTO vendorDTO) {
        final Vendor addedVendor = vendorRepository.save(VendorMapper.INSTANCE.vendorDTOToVendor(vendorDTO));
        final VendorDTO addedVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(addedVendor);
        addedVendorDTO.setVendorUrl("/api/v1/vendors/" + addedVendor.getId());
        return addedVendorDTO;
    }

    @Override
    public VendorDTO updateVendor(final Long id, final VendorDTO vendorDTO) {
        final Vendor vendor = VendorMapper.INSTANCE.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        final Vendor updatedVendor = vendorRepository.save(vendor);
        final VendorDTO updatedVendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(updatedVendor);
        updatedVendorDTO.setVendorUrl("/api/v1/vendors/" + id);
        return updatedVendorDTO;
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }
}
