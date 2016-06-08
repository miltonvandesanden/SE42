

import auction.web.Category;
import auction.web.Item;
import auction.web.User;
import org.junit.Ignore;
//import javax.persistence.*;
//import auction.domain.Category;
//import auction.domain.Item;
//import auction.domain.User;
import com.mycompany.auctionwebclient.AuctionClient;
import com.mycompany.auctionwebclient.RegistrationClient;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemsFromSellerTest {
    
    private AuctionClient auctionClient;
    private RegistrationClient registrationClient;

//    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU");
//    final EntityManager em = emf.createEntityManager();
//    private AuctionMgr auctionMgr;
//    private RegistrationMgr registrationMgr;
//    private SellerMgr sellerMgr;

    public ItemsFromSellerTest() {
    }

    @Before
    public void setUp() throws Exception {
//        registrationMgr = new RegistrationMgr();
//        auctionMgr = new AuctionMgr();
//        sellerMgr = new SellerMgr();
//       new DatabaseCleaner(em).clean();

        auctionClient = new AuctionClient();
        registrationClient = new RegistrationClient();
    }

    @Test
 //   @Ignore
    public void numberOfOfferdItems() {

        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        User user1 = registrationClient.registerUser(email);
        assertEquals(0, registrationClient.numberOfOfferedItems(user1));

        //Category cat = registrationMgr.createCategory("cat2");
//        Category cat = new Category("cat2");
        Category cat = registrationClient.createCategory("cat2");
        Item item1 = auctionClient.offerItem(user1, cat, omsch1);

       
        // test number of items belonging to user1
        //assertEquals(0, user1.numberOfOfferedItems());
        assertEquals(1, registrationClient.numberOfOfferedItems(user1));
        
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         */
         
         
        assertEquals(1, registrationClient.numberOfOfferedItems(item1.getSeller()));


        User user2 = registrationClient.getUser(email);
        assertEquals(1, registrationClient.numberOfOfferedItems(user2));
        Item item2 = auctionClient.offerItem(user2, cat, omsch2);
        assertEquals(2, registrationClient.numberOfOfferedItems(user2));

        User user3 = registrationClient.getUser(email);
        assertEquals(2, registrationClient.numberOfOfferedItems(user3));

        User userWithItem = item2.getSeller();
        assertEquals(2, registrationClient.numberOfOfferedItems(userWithItem));
        //assertEquals(3, userWithItem.numberOfOfferedItems());
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         */
        
        
        //assertNotSame(user3, userWithItem);
        assertEquals(user3, userWithItem);

    }

    @Test
//    @Ignore
    public void getItemsFromSeller() {
        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

//        Category cat = new Category("cat2");
        Category cat = registrationClient.createCategory("cat2");

        User user10 = registrationClient.registerUser(email);
        Item item10 = auctionClient.offerItem(user10, cat, omsch1);
        Iterator<Item> it = (Iterator)user10.getOfferedItems();
        // testing umber of items of java object
        assertTrue(it.hasNext());
        
        // now testing number of items for same user fetched from db.
        User user11 = registrationClient.getUser(email);
        Iterator<Item> it11 = (Iterator)user11.getOfferedItems();
        assertTrue(it11.hasNext());
        it11.next();
        assertFalse(it11.hasNext());

        // Explain difference in above two tests for te iterator of 'same' user

        
        
        User user20 = registrationClient.getUser(email);
        Item item20 = auctionClient.offerItem(user20, cat, omsch2);
        Iterator<Item> it20 = (Iterator)user20.getOfferedItems();
        assertTrue(it20.hasNext());
        it20.next();
        assertTrue(it20.hasNext());


        User user30 = item20.getSeller();
        Iterator<Item> it30 = (Iterator)user30.getOfferedItems();
        assertTrue(it30.hasNext());
        it30.next();
        assertTrue(it30.hasNext());

    }
}
