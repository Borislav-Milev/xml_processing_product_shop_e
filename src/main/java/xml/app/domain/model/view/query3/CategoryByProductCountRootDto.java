package xml.app.domain.model.view.query3;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductCountRootDto {

    public CategoryByProductCountRootDto() {
        this.categoryByProductCountDtos = new ArrayList<>();
    }

    @XmlElement(name = "category")
    private List<CategoryByProductCountDto> categoryByProductCountDtos;
}
