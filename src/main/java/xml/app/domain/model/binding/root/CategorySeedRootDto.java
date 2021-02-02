package xml.app.domain.model.binding.root;

import lombok.Getter;
import xml.app.domain.model.binding.CategorySeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDto {

    public CategorySeedRootDto() {
        this.categorySeedDtos = new ArrayList<>();
    }

    @XmlElement(name = "category")
    private List<CategorySeedDto> categorySeedDtos;
}
