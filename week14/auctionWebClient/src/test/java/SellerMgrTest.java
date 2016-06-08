

import auction.web.Category;
import auction.web.Item;
import auction.web.Money;
import auction.web.User;
import com.mycompany.auctionwebclient.AuctionClient;
import com.mycompany.auctionwebclient.RegistrationClient;
import static org.junit.Assert.*;

//import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;

//import auction.domain.Category;
//import auction.domain.Item;
//import auction.domain.User;

public class SellerMgrTest
{
//    private AuctionMgr auctionMgr;
//    private RegistrationMgr registrationMgr;
//    private SellerMgr sellerMgr;
    private AuctionClient auctionClient;
    private RegistrationClient registrationClient;


    @Before
    public void setUp() throws Exception {
//        registrationMgr = new RegistrationMgr();
//        auctionMgr = new AuctionMgr();
//        sellerMgr = new SellerMgr();

        auctionClient = new AuctionClient();
        registrationClient = new RegistrationClient();
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registrationClient.registerUser("xx@nl");
//        Category cat = new Category("cat1");
        Category cat = registrationClient.createCategory("cat1");
        Item item1 = auctionClient.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";
        
    
        User seller = registrationClient.registerUser("sel@nl");
        User buyer = registrationClient.registerUser("buy@nl");
//        Category cat = new Category("cat1");
        Category cat = registrationClient.createCategory("cat1");
        
            // revoke before bidding
        Item item1 = auctionClient.offerItem(seller, cat, omsch);
        boolean res = auctionClient.revokeItem(item1);
        assertTrue(res);
        int count = auctionClient.findItemByDescription(omsch).size();
        assertEquals(0, count);
        
            // revoke after bid has been made
        Item item2 = auctionClient.offerItem(seller, cat, omsch2);
        auctionClient.newBid(item2, buyer, auctionClient.createMoney(100L, "Euro"));
        boolean res2 = auctionClient.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionClient.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
        
        
        
        
    }

}
