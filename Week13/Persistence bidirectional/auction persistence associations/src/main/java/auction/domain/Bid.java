package auction.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
@Table (name="SE42_W12_1_Bid")
public class Bid implements Serializable{

    @Id
    private Long id;
    
    private FontysTime time;
    @ManyToOne
    private User buyer;
   
    private Money amount;
    //persisten en test schrijven
    @OneToOne @JoinColumn (nullable = false) 
    private Item item;
    
    public Bid()
    {
        
    }
    
    public Bid(User buyer, Money amount, Item item) {
        this.buyer = buyer;
        this.amount = amount;
        this.item = item;
    }

    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }
}
