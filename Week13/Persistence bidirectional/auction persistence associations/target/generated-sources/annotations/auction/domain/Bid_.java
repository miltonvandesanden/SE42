package auction.domain;

import auction.domain.Item;
import auction.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-01T11:51:07")
@StaticMetamodel(Bid.class)
public class Bid_ { 

    public static volatile SingularAttribute<Bid, Money> amount;
    public static volatile SingularAttribute<Bid, Item> item;
    public static volatile SingularAttribute<Bid, Long> id;
    public static volatile SingularAttribute<Bid, FontysTime> time;
    public static volatile SingularAttribute<Bid, User> buyer;

}