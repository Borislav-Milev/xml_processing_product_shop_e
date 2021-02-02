package xml.app.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import xml.app.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    public Product(@NonNull @NotNull @Length(min = 3) String name,
                   @NotNull @NonNull @DecimalMin(value = "0") BigDecimal price,
                   User buyer, User seller) {
        this(name, price);
        this.buyer = buyer;
        this.seller = seller;
    }

    @NonNull
    @NotNull
    @Length(min = 3)
    @Column(nullable = false)
    private String name;

    @NotNull
    @NonNull
    @DecimalMin(value = "0.01")
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)")
    private BigDecimal price;

    @ManyToOne
    @ToString.Exclude
    private User buyer;

    @ManyToOne
    @ToString.Exclude
    private User seller;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Category> categories = new HashSet<>();
}
