package auction.service;

import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SellerMgr {
    
    private EntityManagerFactory emf;
    public EntityManager em;

    public SellerMgr()
    {
        emf = Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }
    
    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     *         en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
        // TODO 
        try
        {
            
            Item item = new Item(seller, cat, description);
//            em.getTransaction().begin();
//            em.persist(item);
//            em.getTransaction().commit();
            return item;
            
            /*Query query = em.createNamedQuery("Item.findWithUserAndCatAndDesc", Item.class);
            query.setParameter("description", description);
            query.setParameter("category", cat.getDiscription());
            query.setParameter("seller", seller.getEmail());
            
            return (Item)query.getSingleResult();*/
        }
        catch(Exception ex)
        {
            //lalalallala haaai bjorn!        
            return null;
        }

    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        // TODO 
        if(item.getHighestBid() == null)
        {
            em.getTransaction().begin();
            em.remove(item);
            em.getTransaction().commit();
            return true;
        }
        else
        {
            return false;
        }
    }
}
