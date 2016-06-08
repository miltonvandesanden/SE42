

import auction.web.User;
import com.mycompany.auctionwebclient.AuctionClient;
import com.mycompany.auctionwebclient.RegistrationClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

//import auction.domain.User;
import org.junit.After;

public class JPARegistrationMgrTest {

//    private RegistrationMgr registrationMgr;
//    private DatabaseCleaner dbc; 
    
    private AuctionClient auctionClient;
    private RegistrationClient registrationClient;
    
    @Before
    public void setUp() throws Exception {
//        registrationMgr = new RegistrationMgr();
//        dbc = new DatabaseCleaner(registrationMgr.em);
        auctionClient = new AuctionClient();
        registrationClient = new RegistrationClient();
    }
    
    @After
    public void tearDown() {
        try
        {
//            dbc.clean();
        }
        catch(Exception ex)
        {
            
        }
    }

    @Test
    public void registerUser() {
        User user1 = registrationClient.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = registrationClient.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = registrationClient.registerUser("xxx2@yyy2");
        assertSame(user2bis, user2);
        //geen @ in het adres
        assertNull(registrationClient.registerUser("abc"));
    }

    @Test
    public void getUser() {
        User user1 = registrationClient.registerUser("xxx5@yyy5");
        User userGet = registrationClient.getUser("xxx5@yyy5");
        assertSame(userGet, user1);
        assertNull(registrationClient.getUser("aaa4@bb5"));
        registrationClient.registerUser("abc");
        assertNull(registrationClient.getUser("abc"));
    }

    @Test
    public void getUsers() {
        Iterator<User> iterator = registrationClient.getUsers();
        List<User> users = new ArrayList<>();
                
        while(iterator.hasNext())
        {
            users.add(iterator.next());
        }
//        List<User> users = registrationClient.getUsers();
        assertEquals(0, users.size());

        User user1 = registrationClient.registerUser("xxx8@yyy");
//        users = registrationClient.getUsers();
        iterator = registrationClient.getUsers();
        users = new ArrayList<>();
                
        while(iterator.hasNext())
        {
            users.add(iterator.next());
        }

        assertEquals(1, users.size());
        assertSame(users.get(0), user1);


        User user2 = registrationClient.registerUser("xxx9@yyy");
//        users = registrationClient.getUsers();
iterator = registrationClient.getUsers();
        users = new ArrayList<>();
                
        while(iterator.hasNext())
        {
            users.add(iterator.next());
        }
        assertEquals(2, users.size());

        registrationClient.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
//        users = registrationClient.getUsers();
        iterator = registrationClient.getUsers();
        users = new ArrayList<>();
                
        while(iterator.hasNext())
        {
            users.add(iterator.next());
        }
        assertEquals(2, users.size());
    }
}
