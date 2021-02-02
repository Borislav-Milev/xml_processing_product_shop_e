package xml.app.domain.model.binding;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @NonNull
    @NotNull(message = "User last name cannot be null.")
    @Length(min = 3, message = "User last name must be at least 3 characters long.")
    @XmlAttribute(name = "last-name")
    private String lastName;

    @PositiveOrZero(message = "Age cannot be negative.")
    @XmlAttribute
    private Short age;
}
