package guru.springframework.controllers;

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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {

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
}