package xml.app.domain.model.view.query4;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.BaseUserDto;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsDto extends BaseUserDto {


    @XmlAttribute
    private Integer age;

    @XmlElement(name = "sold-products")
    private SoldProductsDto soldProductsDto;
}
