package guru.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListVendorDTO {
    private List<VendorDTO> vendors;

}
