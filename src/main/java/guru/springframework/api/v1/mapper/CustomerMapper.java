package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    public static CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    public CustomerDTO customerToCustomerDTO(final Customer customer);

    public Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
