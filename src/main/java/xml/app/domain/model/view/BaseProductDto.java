package xml.app.domain.model.view;

import lombok.*;
import xml.app.domain.model.view.query1.ProductInRangeDto;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
@XmlSeeAlso(ProductInRangeDto.class)
public class BaseProductDto {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String price;
}
