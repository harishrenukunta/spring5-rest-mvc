package guru.springframework.repository;

import guru.springframework.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    public Optional<Vendor> findVendorByName(final String name);
    public Optional<Vendor> findVendorById(final Long id);
}
