package auction.dao;

import auction.domain.User;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class UserDAOJPAImpl implements UserDAO {
    private final EntityManager em;
    //private HashMap<String, User> users;

    public UserDAOJPAImpl(EntityManager em) {
        //users = new HashMap<String, User>();
        this.em = em;
    }

    @Override
    public int count()
    {
        Query q = em.createNamedQuery("User.count", User.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(User user)
    {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void edit(User user)
    {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        
    }


    @Override
    public List<User> findAll()
    {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public User findByEmail(String email)
    {
        //Query q = em.createNamedQuery("User.findByEmail", User.class);
        Query q = em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email);
        //q.setParameter("email", email);
        try
        {
            return (User) q.getSingleResult();
        }
        catch(Exception ex)
        {
            return null;
        }
        
        
    }

    @Override
    public void remove(User user)
    {
        em.getTransaction().begin();
        em.remove(em.merge(user));
        em.getTransaction().commit();
    }
}
