package auction.service;

import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class AuctionMgr  {

    private EntityManager em = (Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU")).createEntityManager();
   /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     *         geretourneerd
     */
    @WebMethod
    public Item getItem(Long id) {
        return em.find(Item.class, id);
    }

  
   /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    @WebMethod
    public List<Item> findItemByDescription(String description) {
        // TODO
        Query q = em.createNamedQuery("Item.findByDescription", Item.class).setParameter("description", description);
        try
        {
            return q.getResultList();
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     *         amount niet hoger was dan het laatste bod, dan null
     */
    @WebMethod
    public Bid newBid(Item item, User buyer, Money amount) {
        // TODO 
        Bid result = null;
        
        try
        {
            if(item.getHighestBid() != null)
            {
                if(item.getHighestBid().getAmount().compareTo(amount) < 0)
                {
                    em.getTransaction().begin();
                    em.persist(item.newBid(buyer, amount));
                    em.getTransaction().commit();
                    result = item.getHighestBid();
                }
            }
            else
            {
                em.getTransaction().begin();
                em.persist(item.newBid(buyer, amount));
                em.getTransaction().commit();
                result = item.getHighestBid();
            }    
        }
        catch(Exception exception)
        {
            return result;
        }
        
        return result;
    }
    
    public Money createMoney(Long amount, String currency)
    {
        return new Money(amount, currency);
    }
}
