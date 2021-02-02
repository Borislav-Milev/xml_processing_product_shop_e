package xml.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xml.app.domain.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u " +
            "join Product p on p.seller.id = u.id " +
            "where p.buyer is not null " +
            "group by u.id " +
            "order by u.lastName, u.firstName")
    List<User> findAllSellersWithItemSold();

    @Query("select u from User u " +
            "join Product p on p.seller.id = u.id " +
            "where p.buyer is not null " +
            "group by u.id " +
            "order by u.productsSold.size desc , u.lastName")
    List<User> countOfSellers();
}
