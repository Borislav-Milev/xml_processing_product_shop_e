package xml.app.domain.model.binding.root;

import lombok.Getter;
import xml.app.domain.model.binding.UserSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UserSeedRootDto {

    public UserSeedRootDto() {
        this.userSeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "user")
    private List<UserSeedDto> userSeedDtos;
}
