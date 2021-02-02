package xml.app.domain.model.binding.root;

import lombok.Getter;
import xml.app.domain.model.binding.ProductSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {

    public ProductSeedRootDto() {
        this.productSeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "product")
    private List<ProductSeedDto> productSeedDtos;
}
