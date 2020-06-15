package guru.springframework.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.ExceptionHandler.ResourceNotFoundException;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityControllerAdvice())
                .build();
    }

    @Test
    void getCustomers() throws Exception {
        CustomerDTO customer1 = CustomerDTO.builder()
                .firstname("Somesh")
                .lastname("Gowalikar").customerUrl(getCustomerUrl() + "66").build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .firstname("Ashutosh")
                .lastname("Rana").customerUrl(getCustomerUrl() + "95").build();

        when(customerService.getCustomers()).thenReturn(Arrays.asList(customer1, customer2));
        mockMvc.perform(get(getCustomerUrl())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerByID() throws Exception {
        final CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname("Mock firstname");
        returnDTO.setLastname("Mock Lastname");
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "1");

        when(customerService.getCustomerById(anyString())).thenReturn(returnDTO);

        mockMvc.perform(get(getCustomerUrl() + "1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstname", equalTo(returnDTO.getFirstname())))
            .andExpect(jsonPath("$.lastname", equalTo(returnDTO.getLastname())))
            .andExpect(jsonPath("$.customer_url", equalTo(returnDTO.getCustomerUrl())));
    }

    @Test
    public void updateCustomer() throws Exception {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("TobeUpdated");
        customerDTO.setLastname("Lastname");

        final CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setFirstname(customerDTO.getFirstname());
        savedCustomerDTO.setLastname(customerDTO.getLastname());
        savedCustomerDTO.setCustomerUrl("/api/v1/customers/1");
        when(customerService.addCustomer(any(CustomerDTO.class))).thenReturn(savedCustomerDTO);

        mockMvc.perform(post("/api/v1/customers/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customerDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.lastname", equalTo(customerDTO.getLastname())));
    }

    @Test
    public void patchCustomer() throws Exception {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Harish patch test");
        final CustomerDTO returnDtO = new CustomerDTO();
        returnDtO.setFirstname("Harish");
        returnDtO.setLastname(customerDTO.getLastname());
        returnDtO.setCustomerUrl("/api/v1/customers/1");
        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class) )).thenReturn(returnDtO);
        mockMvc.perform(patch("/api/v1/customers/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customerDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname", equalTo(returnDtO.getFirstname())))
        .andExpect(jsonPath("$.lastname", equalTo(returnDtO.getLastname())))
        .andExpect(jsonPath("$.customer_url", equalTo(returnDtO.getCustomerUrl())));
    }

    @Test
    public void deleteCustomerById() throws Exception {
       mockMvc.perform(delete(("/api/v1/customers/1")))
        .andExpect(status().isNoContent())  ;
    }

    @Test
    public void resourceNotFoundException() throws Exception {
        when(customerService.getCustomerById(anyString())).thenThrow(new ResourceNotFoundException());
        mockMvc.perform(get(CustomerController.BASE_URL + "29")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private String asJsonString(final CustomerDTO customerDTO) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(customerDTO);
    }

    private String getCustomerUrl(){
        return CustomerController.BASE_URL;
    }
}