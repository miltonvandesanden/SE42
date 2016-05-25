package auction.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
@Table (name="SE42_W12_1_Bid")
public class Bid {

    @Id
    private Long id;
    @Column
    private FontysTime time;
    @Column
    private User buyer;
    @Column
    private Money amount;
    
    public Bid()
    {
        
    }
    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
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
