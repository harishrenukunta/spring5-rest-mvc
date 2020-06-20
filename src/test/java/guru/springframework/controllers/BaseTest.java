package guru.springframework.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;

public class BaseTest {

    protected String asJsonString(final CustomerDTO customerDTO) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(customerDTO);
    }

    protected String asJsonString(final VendorDTO ven) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(ven);
    }
}
