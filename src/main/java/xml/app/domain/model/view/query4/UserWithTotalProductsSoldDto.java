package xml.app.domain.model.view.query4;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UserWithTotalProductsSoldDto {

    public UserWithTotalProductsSoldDto() {
        this.userWithSoldProductsDtos = new ArrayList<>();
    }

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "user")
    private List<UserWithSoldProductsDto> userWithSoldProductsDtos;
}
