package guru.springframework.bootstrap;

import guru.springframework.domain.Vendor;
import guru.springframework.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VendorBootstrap implements CommandLineRunner {

    private final VendorRepository vendorRepository;

    public VendorBootstrap(final VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Vendor tesco = new Vendor();
        tesco.setName("Tesco");

        final Vendor sainsbury = new Vendor();
        sainsbury.setName("Sainsbury");

        final Vendor waitrose = new Vendor();
        waitrose.setName("Waitrose");

        vendorRepository.save(tesco);
        vendorRepository.save(sainsbury);
        vendorRepository.save(waitrose);

        log.info("Vendors loaded successfully");

    }
}
