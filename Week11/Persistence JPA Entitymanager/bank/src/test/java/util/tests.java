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
        assertEquals(expected, account.getId());
        //de database wordt geupdate met de voorbereide persist
        em.flush();
        //TODO: verklaar en pas eventueel aan
        //veranderen naar assertNotEquals flush stuurt naar db, sequence zorgt dat getid 1 zal zijn, expected = -100
        //returned true
        assertNotEquals(expected, account.getId());
        //update database met meest recente gegevens (niks veranderd)
        em.getTransaction().commit();
        //TODO: verklaar en pas eventueel aan
        /*Create new table Account
        (
            Id Number Primary Key,
            accountNr Number Unique,
            balance number,
            threshold number
        );
        
        Insert into Account(Id, accountNr, balance, threshold) 
        Values(1, 111, 0, 0);
        */
    }

    @Test
    public void vraag4()
    {
        
        Long expectedBalance = 400L;
        //new account aanmaken met accountnummer 114
        Account account = new Account(114L);
        
        em.getTransaction().begin();
        //klaarmaken van het account object voor database (genereert id)
        em.persist(account);
        //het object (hoe bekent binnen het gepersiste) wordt aangepast (de balance wordt: 400)
        account.setBalance(expectedBalance);
        //het gepersiste wordt naar de database geschreven (met id 1 accountnummer 114 balance 400 threshold 0
        em.getTransaction().commit();
        //return true. bekend in database door commit (database waarde van balance is dus 400 en expected = 400)
        assertEquals(expectedBalance, account.getBalance());
        //TODO: verklaar de waarde van account.getBalance
        //werkt omdat het object in de database staat (id is 1)
        Long acId = account.getId();
        //account wordt in de cache null niet in database
        account = null;
        //nieuwe entity manager
        EntityManager em2 = emf.createEntityManager();
        //nieuwe transaction
        em2.getTransaction().begin();
        //in de cache van entity manager 2 wordt een account opgehaald uit de database
        
        Account found = em2.find(Account.class, acId);
        //TODO: verklaar de waarde van found.getBalance
        assertEquals(expectedBalance, found.getBalance());
        
    }
    
    @Test 
    public void vraag5()
    {     
        Long expectedBalance = 400L;
        //new account aanmaken met accountnummer 114
        Account account = new Account(114L);
        
        em.getTransaction().begin();
        //klaarmaken van het account object voor database (genereert id)
        em.persist(account);
        //het object (hoe bekent binnen het gepersiste) wordt aangepast (de balance wordt: 400)
        account.setBalance(expectedBalance);
        //het gepersiste wordt naar de database geschreven (met id 1 accountnummer 114 balance 400 threshold 0
        em.getTransaction().commit();
        //return true. bekend in database door commit (database waarde van balance is dus 400 en expected = 400)
        assertEquals(expectedBalance, account.getBalance());
        //TODO: verklaar de waarde van account.getBalance
        //werkt omdat het object in de database staat (id is 1)
        Long acId = account.getId();
        //nieuwe entity manager
        EntityManager em2 = emf.createEntityManager();
        //nieuwe transaction
        em2.getTransaction().begin();
        
        
        Account found = em2.find(Account.class, acId);
        found.setBalance(200L);
        em2.persist(found);
        em2.getTransaction().commit();
        em.refresh(account);
        assertEquals(account.getId(), found.getId());
    }

    @Test
    public void vraag6()
    {
        
        Account acc = new Account(1L);
        Account acc2 = new Account(2L);
        Account acc9 = new Account(9L);

        // scenario 1
        Long balance1 = 100L;
        em.getTransaction().begin();
        em.persist(acc);
        acc.setBalance(balance1);
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifieren.
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        Long expectedid = 1L;
        Long expectedaccountnr = 1L;
        Long expectedbalance = 0L;
        assertEquals(expectedid, acc.getId());
        assertEquals(acc.getAccountNr(), expectedaccountnr);
        assertEquals(acc.getBalance(), expectedbalance);


        // scenario 2
        Long balance2a = 211L;
        //in database record met id = 1 wordt accountnummer 2
        acc = new Account(2L);
        em.getTransaction().begin();
        //acc9 wordt account 1 en wordt gemanaged  (id = 1 saldo 100, accountnr 2, threshold 0) (staat in cache!)
        acc9 = em.merge(acc);
        //acc balance wordt 211 in database:( id= 1,  accountnr = 2 saldo = 211 threshold 0)
        acc.setBalance(balance2a);
        //acc9 (cache!) saldo = 411 id 1 accountnr = 2 threshold 0
        acc9.setBalance(balance2a+balance2a);
        //acc9 wordt nu ook naar database gestuurd. voor het eerst gecommit dus idsequence overschrijft id= 1 naar id = 2 ) waarden in database worden dus id 2 accountnr 2 saldo 422 en threshold 0
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id. 
        // HINT: gebruik acccountDAO.findByAccountNr
        Long accexpectedid = 1L;
        Long accexpectedbalance = 211L;
        Long accexpectedaccountnr = 2L;
        assertEquals(acc.getId(), accexpectedid);
        assertEquals(acc.getAccountNr(), accexpectedaccountnr);
        assertEquals(acc.getBalance(), accexpectedbalance);
        
        Long acc9expectedid = 2L;
        Long acc9expectedbalance = 422L;
        Long acc9expectedaccountnr = 2L;
        assertEquals(acc9.getId(), acc9expectedid);
        assertEquals(acc9.getAccountNr(), acc9expectedaccountnr);
        assertEquals(acc9.getBalance(), acc9expectedbalance);
        


        // scenario 3
        Long balance3b = 322L;
        Long balance3c = 333L;
        //cache krijgt new account voor acc in database blijft oude bekend
        acc = new Account(3L);
        em.getTransaction().begin();
        //acc2 accountnr = 3 id = nvt saldo = 0 threshold = 0
        acc2 = em.merge(acc);
        
        assertTrue(em.contains(acc)); // verklaar //cache krijgt new account voor acc in database blijft oude bekend dankzij commit (maar de = new werkt niet op database niveau dus alleen binnen cache)
        assertTrue(em.contains(acc2)); // verklaar door de merge wordt acc2 ook bekend in de cache
        assertEquals(acc,acc2);  //verklaar nee
        
        //in de cache krijgt acc2 saldo 322
        acc2.setBalance(balance3b);
        //in de cache krijgt acc saldo van 333
        acc.setBalance(balance3c);
        //in database komt -> acc met waarden id = 3 balance = 333 accountnr = 3 threshold 0 in database komt -> acc2 id = 4 accountnr = 3 threshold = 0 balance = 322
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.


        // scenario 4
        Account account = new Account(114L);
        account.setBalance(450L);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        //id = 5
        em.persist(account);
        //in database account -> id = 5 accountnr=114 threshold = 0 balance= 450
        em.getTransaction().commit();
        //account2 wordt nieuw account met accountnr 114 
        Account account2 = new Account(114L);
        //tweedeaccountobject wordt account 2 (accountnr 114)
        Account tweedeAccountObject = account2;
        //tweedeaccountobjec balance wordt 650 (verander naar hoofdletter L)
        tweedeAccountObject.setBalance(650L);
        //account2 balance = 0 verander in assertNotEquals / assertEquals((long)650L, tweedeaccountobject);
        assertNotEquals((Long)650L,account2.getBalance());  //verklaar
        //account2 &&&&&&&&& account id = 5 
        account2.setId(account.getId());
        em.getTransaction().begin();
        
        account2 = em.merge(account2);// persist
        //account id=5 accountnr 114 saldo 450 account2 id = 6 account nr 114 balance = 0
        assertNotSame(account,account2);  //verklaar// balance niet gelijk
        assertTrue(em.contains(account2));  //verklaar wordt gemanaged op moment van merge account2 = em.merge(account2);
        assertFalse(em.contains(tweedeAccountObject));  //verklaar nooit gemerged / gepersist
        tweedeAccountObject.setBalance(850L);
        assertNotEquals((Long)650L,account.getBalance());  //verklaar account balance is 450 en niet 650
        assertEquals((Long)650L,account2.getBalance());  //verklaar account2 is balance 850 en niet 650
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void oefen1()
    {
Long expected = -100L;
Long test = 2L;
Account account = new Account(111L);
account.setId(expected);
account.setBalance(test);
em.getTransaction().begin();
em.persist(account);
//TODO: verklaar en pas eventueel aan
//assertEquals(expected, account.getId();
em.flush();
//TODO: verklaar en pas eventueel aan
//assertEquals(expected, account.getId();
account.setBalance(5L);
Account databaseding = em.find(Account.class, account.getId());
assertEquals((Long)5L, databaseding.getBalance());
em.getTransaction().commit();
//TODO: verklaar en pas eventueel aan

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}