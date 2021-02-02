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
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsRootDto {

    public UserWithProductsRootDto() {
        this.userWithProductsDtos = new ArrayList<>();
    }

    @XmlElement(name = "user")
    private List<UserWithProductsDto> userWithProductsDtos;
}
