/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import bank.domain.Account;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class tests
{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
    private EntityManager em = emf.createEntityManager(); 
    private DatabaseCleaner dbc = new DatabaseCleaner(em);
    
    public tests() {
        
        //create entity manager factory
        //create entity manager 
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        try
        {
            dbc.clean();
        }
        catch(Exception ex)
        {
            
        }
    }
    @Test
    public void vraag1()
    {
        Account account = new Account(111L);
        em.getTransaction().begin();
        //klaarmaken voor database
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        //return true omdat het object nog niet naar de database is geschreven, dat gebeurd pas bij de commit
        assertNull(account.getId());
        //naar de database
        em.getTransaction().commit();
        //returned "AccountId: 1"
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        //1based dus return true
        assertTrue(account.getId() > 0L);
        //returned "AccountId: 1"
        /*Create new table Account
        (
            Id Number Primary Key,
            accountNr Number Unique,
            balance number,
            threshold number
        );
        
        Insert into Account(Id, accountNr, balance, threshold) 
        Values(1, 111, 0, 0);
        
        eindresultaat = tabel account aangemaakt met een row met de gegevens : 1, 111, 0, 0
        */
    }
    
    @Test
    public void vraag2()
    {
        //new account maken
        Account account = new Account(111L);
        em.getTransaction().begin();
        //account klaarmaken voor database
        em.persist(account);
        //return true omdat er geen record in de database bekend is (geen commit)
        assertNull(account.getId());
        //er wordt nooit een commit uitgevoerd, onderstaande clear local cashe
        em.getTransaction().rollback();
        // TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl
        //geen sqlqueries geenv erandering in database
    }
    
    @Test
    public void vraag3()
    {
        Long expected = -100L;
        //nieuw account met accountNr 111
        Account account = new Account(111L);
        //we passen in het lokaal bekende account de id aan naar -100
        account.setId(expected);
        em.getTransaction().begin();
        //we maken de lokaal bekende account object klaar voor de database
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        assertNotEquals(expected, account.getId());
        //de database wordt geupdate (possibly lege kopie)
        em.flush();
        //TODO: verklaar en pas eventueel aan
        //assertEquals(expected, account.getId();
        em.getTransaction().commit();
        //TODO: verklaar en pas eventueel aan
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}