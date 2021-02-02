package xml.app.domain.model.view;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.query2.UserWithProductsDto;
import xml.app.domain.model.view.query4.UserWithSoldProductsDto;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@XmlSeeAlso({UserWithProductsDto.class, UserWithSoldProductsDto.class})
public class BaseUserDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    //@XmlAttribute
    //private Integer age;
}
