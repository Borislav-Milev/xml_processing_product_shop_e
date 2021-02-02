package xml.app.queryManager;

import org.springframework.stereotype.Component;
import xml.app.service.contract.CategoryService;
import xml.app.service.contract.ProductService;
import xml.app.service.contract.UserService;
import xml.app.util.contract.Reader;

@Component
public class Manager implements Runnable{

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Reader reader;

    public Manager(UserService userService, CategoryService categoryService,
                   ProductService productService, Reader reader) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Please enter query from 1 to 4 you wish to test, or 0 to exit.");
            try {
                int input = Integer.parseInt(this.reader.getReader());
                if(input == 0) break;
                switch (input) {
                    case 1 -> System.out.println(this.productService.productsInRange());
                    case 2 -> System.out.println(this.userService.soldProducts());
                    case 3 -> System.out.println(this.categoryService.getCategoriesByProductCount());
                    case 4 -> System.out.println(this.userService.usersWithAtLeastOneSoldProduct());
                    default -> System.out.println("No such exercise.");
                }
            }catch (NumberFormatException e){
                System.out.println("Number required.");
            }
        }
    }
}
