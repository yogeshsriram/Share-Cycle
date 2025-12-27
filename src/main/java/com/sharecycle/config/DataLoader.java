package com.sharecycle.config;

import com.sharecycle.entity.Category;
import com.sharecycle.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        List<String> categories = Arrays.asList(
                "Books",
                "Food",
                "Medicines",
                "Stationery",
                "Clothing",
                "Others"
        );

        categories.forEach(name -> {
            // Check if category already exists
            boolean exists = categoryRepository.findAll()
                    .stream()
                    .anyMatch(c -> c.getName().equalsIgnoreCase(name));

            if (!exists) {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
                System.out.println("✅ Inserted category: " + name);
            }
        });

        System.out.println("ℹ️ Category seeding complete.");
    }
}