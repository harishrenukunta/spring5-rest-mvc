package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository);
    }

    @Test
    public void getVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("The Morrisons");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Aldi");

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        assertThat(vendorService.getVendors()).hasSize(2);
    }

    @Test
    public void getVendorById() {
        final Vendor vendor = new Vendor();
        vendor.setName("Premiere");
        vendor.setId(1L);
        when(vendorRepository.findVendorById(anyLong())).thenReturn(Optional.of(vendor));
        final VendorDTO vDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);
        vDTO.setVendorUrl("/api/v1/vendors/1");
        assertThat(vendorService.getVendorById(1L)).isEqualToComparingFieldByField(vDTO);
    }

    @Test
    void addVendor(){
        final VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("TescoDummyTest");
        vendorDTO.setVendorUrl("/api/v1/vendors/1");

        final Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);
        assertThat(vendorService.addVendor(vendorDTO))
                .isEqualToComparingFieldByField(vendorDTO);
    }

    @Test
    public void updateVendor(){
        final Vendor vendor = new Vendor();
        vendor.setName("Sainsbury to be updated");
        vendor.setId(1L);

        final VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());
        vendorDTO.setVendorUrl("/api/v1/vendors/1");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        assertThat(vendorService.updateVendor(1L, vendorDTO))
                .isEqualToComparingFieldByField(vendorDTO);
    }
}