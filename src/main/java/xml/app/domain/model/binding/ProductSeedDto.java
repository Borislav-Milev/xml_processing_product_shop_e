package xml.app.domain.model.binding;

import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;


@Getter
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto {

    @NonNull
    @NotNull(message = "Product name cannot be null.")
    @Length(min = 3)
    @XmlElement
    private String name;

    @NonNull
    @NotNull(message = "Product price cannot be null.")
    @DecimalMin(value = "0.01", message = "Product price cannot be negative.")
    @XmlElement
    private BigDecimal price;
}
