package auction.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.jws.WebMethod;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity 
@Table (name = "SE42_W12_1_User")
@NamedQueries({
    @NamedQuery(name = "User.count", query = "select count(a) from User as a"),
    @NamedQuery(name = "User.findByEmail", query = "select a from User as a where a.email = :email")
})
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class User implements Serializable{
    @Id 
    private String email;
    
    //verander naar @Column > loopt niet meer vast
    @OneToMany (mappedBy = "seller"/*, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true*/)
    //was gecomment
    //@CascadeOnDelete
    private Set<Item> offeredItems;
    
    public User()
    {
        
    }
    
    public User(String email) {
        this.email = email;
        offeredItems = new TreeSet<>();
        
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
