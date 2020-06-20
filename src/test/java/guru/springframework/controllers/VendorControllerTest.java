package guru.springframework.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.ListVendorDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest extends BaseTest{

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getVendors() throws Exception {
        final VendorDTO vendor1 = VendorDTO.builder().name("TescoDummy").vendorUrl("/api/v1/vendors/1").build();
        final VendorDTO vendor2 = VendorDTO.builder().name("SainsburyDummy").vendorUrl("/api/v1/vendors/2").build();

        when(vendorService.getVendors()).thenReturn(Arrays.asList(vendor1, vendor2));

        mockMvc.perform(get("/api/v1/vendors/")
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    void getVendorById() throws Exception {
        final VendorDTO returnVendorDTO = VendorDTO.builder().name("my dummy retailer").vendorUrl("api/v1/vendors/2").build();
        when(vendorService.getVendorById(anyLong())).thenReturn(returnVendorDTO);

        mockMvc.perform(get("/api/v1/vendors/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", equalTo(returnVendorDTO.getName())))
        .andExpect(jsonPath("$.vendor_url", equalTo(returnVendorDTO.getVendorUrl())));
    }

    @Test
    public void addVendor() throws Exception {
        final VendorDTO vendor = VendorDTO.builder().name("Added Vendor").vendorUrl("/api/v1/vendors/1").build();
        final Vendor ven = VendorMapper.INSTANCE.vendorDTOToVendor(vendor);
        when(vendorService.addVendor(any(VendorDTO.class))).thenReturn(vendor);

        mockMvc.perform(post("/api/v1/vendors/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendor.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendor.getVendorUrl())));
    }

    @Test
    public void updateVendor() throws Exception {
        final Long id = 1L;
        final VendorDTO inputVendorDTO = new VendorDTO();
        inputVendorDTO.setName("TesCo Dummy vendor");

        final VendorDTO updatedVendorDTO = new VendorDTO();
        updatedVendorDTO.setName(inputVendorDTO.getName());
        updatedVendorDTO.setVendorUrl("/api/v1/vendors/" + id);

        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class)))
                .thenReturn(updatedVendorDTO);

        mockMvc.perform(put("/api/v1/vendors/" + id)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(inputVendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(updatedVendorDTO.getName())))
                .andExpect(jsonPath("$.vendor_url", equalTo(updatedVendorDTO.getVendorUrl())));
    }
}