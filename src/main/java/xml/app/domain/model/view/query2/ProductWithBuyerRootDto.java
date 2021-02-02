package xml.app.domain.model.view.query2;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithBuyerRootDto {

    public ProductWithBuyerRootDto() {
        this.productWithBuyerDtos = new ArrayList<>();
    }

    @XmlElement(name = "product")
    private List<ProductWithBuyerDto> productWithBuyerDtos;
}
