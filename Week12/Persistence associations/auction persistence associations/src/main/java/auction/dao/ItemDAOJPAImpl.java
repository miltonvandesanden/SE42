/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Stefan
 */
public class ItemDAOJPAImpl implements ItemDAO
{
    private EntityManager em = (Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU")).createEntityManager();
    private List<Item> items = new ArrayList<>();
    
    @Override
    public int count() {
        return items.size();
    }

    @Override
    public void create(Item item) throws EntityExistsException{
        if(em.contains(item))
        {
           throw new EntityExistsException();
        }
        else
        {
            items.add(item);
            em.persist(item);
            em.getTransaction().begin();
            em.getTransaction().commit();
        }
        
    }

    @Override
    public void edit(Item item) throws IllegalArgumentException{
        if(em.contains(item))
        {
            //hey, het kan zijn dat het geëdite item niet gemanaged is maar wel in de database bekend is (mochten er problemen komen bij het testen kan dit het zijn)
            em.getTransaction().begin();
            Item tempItem = em.find(Item.class, item.getId());
            tempItem = em.merge(item);
            em.getTransaction().commit();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Item find(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Item> findByDescription(String description) {
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

    @Override
    public void remove(Item item) {
        try{
            em.getTransaction().begin();
            em.remove(item);
            em.getTransaction().commit();
        }
        catch(Exception ex)
        {
            //lalalalalalalala haaai bjorn
        }
        
    }
    
}
