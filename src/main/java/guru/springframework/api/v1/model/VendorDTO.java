package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorDTO {

    @ApiModelProperty(value="Vendor name", required=true)
    private String name;

    @ApiModelProperty(value="Vendor url", required=false)
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
