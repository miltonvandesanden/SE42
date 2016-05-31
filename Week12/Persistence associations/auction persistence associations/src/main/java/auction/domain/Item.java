package auction.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import nl.fontys.util.Money;

@Entity
@Table (name = "SE42_W12_1_Item")
@NamedQueries({
    @NamedQuery(name = "Item.findByDescription", query = "select a from Item as a where a.description = :description"),
    @NamedQuery(name = "Item.findWithUserAndCatAndDesc", query = "select a from Item as a where a.seller = :seller AND a.category = :category AND a.description = :description")
})
public class Item implements Comparable, Serializable{

    @Id
    private Long id;
    @JoinColumn
    private User seller;
    @JoinColumn
    private Category category;
    @Column
    private String description;
    @JoinColumn(nullable = true) 
    private Bid highest;

    public Item()
    {
        
    }
    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    @Override
    public int compareTo(Object arg0) {
        //TODO
        return (int) (highest.getAmount().getCents() - ((Item) arg0).getHighestBid().getAmount().getCents());
    }
    
    @Override
    public boolean equals(Object o) {

        if(o == null)
        {
            return false;
        }
        Item a = (Item) o;
         
        if(a.getSeller() == null || a.getCategory() == null || a.getDescription() == null || a.getHighestBid() == null)
        {
            return false;
        }
        else if(this.seller != a.getSeller())
        {
            return false;
        }
        else if(this.category != a.getCategory())
        {
            return false;
        }
        else if(!description.equals(a.getDescription()))
        {
            return false;
        }
        else if(highest != a.getHighestBid())
        {
            return false;
        }
        else
        {
            return true;
        }  
    }

    @Override
    public int hashCode() {
        //TODO
        //return (int) hashCode();
        return 0;
    }
}
