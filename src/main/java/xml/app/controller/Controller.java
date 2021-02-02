package xml.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xml.app.queryManager.Manager;
import xml.app.service.contract.CategoryService;
import xml.app.service.contract.ProductService;
import xml.app.service.contract.UserService;
import xml.app.util.contract.FileIO;

import static xml.app.constant.FilePath.*;


@Component
public class Controller implements CommandLineRunner {

    private final Manager manager;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public Controller(Manager manager, UserService userService,
                      CategoryService categoryService, ProductService productService) {
        this.manager = manager;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();

        this.manager.run();
    }

    private void seedUsers() {
        this.userService.seedUser(USERS_PATH);
    }

    private void seedCategories() {
        this.categoryService.seedCategories(CATEGORIES_PATH);
    }

    private void seedProducts() {
        this.productService.seedProducts(PRODUCTS_PATH);
    }
}
