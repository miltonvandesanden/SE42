package auction.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="SE42_W12_1_Category")
public class Category implements Serializable{

    @Id
    private Long id;
    @Column
    private String description = "undefined";
    
    private Category() 
    {

    }

    public Category(String description) {
        this.description = description;
    }

    public String getDiscription() {
        return description;
    }
}
