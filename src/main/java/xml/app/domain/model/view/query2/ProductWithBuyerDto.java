package xml.app.domain.model.view.query2;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithBuyerDto {

    @XmlElement
    private String name;

    @XmlElement
    private String price;

    @XmlElement(name = "buyer-first-name")
    private String firstName;

    @XmlElement(name = "buyer-last-name")
    private String lastName;


}
