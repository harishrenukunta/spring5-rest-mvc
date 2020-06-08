package guru.springframework.bootstrap;

import guru.springframework.domain.Customer;
import guru.springframework.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerBootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustomerBootstrap(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstname("Harish");
        customer1.setLastname("Renukunta");

        Customer customer2 = new Customer();
        customer2.setFirstname("Nagarjuna");
        customer2.setLastname("Marri");

        Customer customer3 = new Customer();
        customer3.setFirstname("Sudhendhra");
        customer3.setLastname("Immedi");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }
}
