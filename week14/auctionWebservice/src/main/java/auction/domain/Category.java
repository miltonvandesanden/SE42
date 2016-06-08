package auction.domain;

import java.io.Serializable;
import javax.jws.WebMethod;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table (name="SE42_W12_1_Category")
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Category implements Serializable{

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String description = "undefined";
    
    private Category() 
    {

    }

    public Category(String description)
    {
        this.description = description;
    }

    @WebMethod
    public String getDiscription() {
        return description;
    }
}
