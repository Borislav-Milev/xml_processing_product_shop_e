package xml.app.domain.model.binding;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDto {

    @NonNull
    @NotNull(message = "Category name cannot be null.")
    @Length(min = 3, max = 15, message = "Category name must be between 3 and 15 characters long.")
    @XmlElement
    private String name;
}
