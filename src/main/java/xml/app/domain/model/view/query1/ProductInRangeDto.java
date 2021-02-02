package xml.app.domain.model.view.query1;


import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
public class ProductInRangeDto {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String price;

    @XmlAttribute
    private String seller;
}
