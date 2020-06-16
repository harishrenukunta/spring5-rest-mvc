package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    public static VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    public VendorDTO vendorToVendorDTO(final Vendor vendor);
}
