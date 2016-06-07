package auction.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.jws.WebMethod;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.eclipse.persistence.annotations.PrivateOwned;

@Entity 
@Table (name = "SE42_W12_1_User")
@NamedQueries({
    @NamedQuery(name = "User.count", query = "select count(a) from User as a"),
    @NamedQuery(name = "User.findByEmail", query = "select a from User as a where a.email = :email")
})
@XmlRootElement
public class User implements Serializable{
    @Id 
    @XmlAttribute
    private String email;
    
    //verander naar @Column > loopt niet meer vast
    @OneToMany (mappedBy = "seller"/*, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true*/)
    //was gecomment
    //@CascadeOnDelete
    @XmlAttribute
    private Set<Item> offeredItems;
    
    public User()
    {
        
    }
    
    public User(String email) {
        this.email = email;
        
    }

    @WebMethod
    public String getEmail() {
        return email;
    }
    
    @WebMethod
    public Iterator getOfferedItems()
    {
        return offeredItems.iterator();
    }
    
    @WebMethod
    protected void addItem(Item item)
    {
        offeredItems.add(item);
    }
    
    @WebMethod
    public int numberOfOfferedItems()
    {
        return offeredItems.size();
    }
}
