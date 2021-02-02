package xml.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xml.app.domain.entity.Product;
import xml.app.domain.entity.User;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Set<Product> findAllByPriceBetweenAndBuyerOrderByPrice(BigDecimal more, BigDecimal less, User buyer);
}
