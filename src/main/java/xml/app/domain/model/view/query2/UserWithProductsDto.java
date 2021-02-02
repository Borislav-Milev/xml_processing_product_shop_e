package xml.app.domain.model.view.query2;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.BaseUserDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsDto extends BaseUserDto {

    @XmlElement(name = "sold-products")
    private ProductWithBuyerRootDto productWithBuyerRootDto;
}
