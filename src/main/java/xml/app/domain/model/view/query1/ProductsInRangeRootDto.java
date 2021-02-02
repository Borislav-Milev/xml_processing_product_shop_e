package xml.app.domain.model.view.query1;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "products")
public class ProductsInRangeRootDto {

    public ProductsInRangeRootDto() {
        this.productInRangeDtos = new ArrayList<>();
    }

    @XmlElement(name = "product")
    private List<ProductInRangeDto> productInRangeDtos;
}
