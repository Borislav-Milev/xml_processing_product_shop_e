package xml.app.service.contract;


public interface ProductService {

    void seedProducts(String jsonProducts);

    //Query 1
    String productsInRange();
}
