package xml.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xml.app.domain.entity.Category;
import xml.app.domain.model.view.query3.CategoryByProductCountDto;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select new xml.app.domain.model.view.query3.CategoryByProductCountDto" +
            "(c.name, c.products.size, avg(p.price), sum(p.price)) from Category c " +
            "join c.products p group by c.id order by c.products.size desc ")
    List<CategoryByProductCountDto> categoryByProductCount();
}
