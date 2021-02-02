package xml.app.domain.model.view.query4;

import lombok.Getter;
import lombok.Setter;
import xml.app.domain.model.view.BaseProductDto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {

    public SoldProductsDto() {
        this.baseProductDtoList = new ArrayList<>();
    }

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "product")
    private List<BaseProductDto> baseProductDtoList;
}
