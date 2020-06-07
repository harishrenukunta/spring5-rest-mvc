package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("fruits");

        Category dryFruits = new Category();
        dryFruits.setName("dryFruits");

        Category pulses = new Category();
        pulses.setName("pulses");

        Category legumes = new Category();
        legumes.setName("legumes");

        Category flowers = new Category();
        flowers.setName("flowers");

        categoryRepository.save(fruits);
        categoryRepository.save(dryFruits);
        categoryRepository.save(pulses);
        categoryRepository.save(legumes);
        categoryRepository.save(flowers);

        System.out.println("Data successfully loaded...");

    }
}
