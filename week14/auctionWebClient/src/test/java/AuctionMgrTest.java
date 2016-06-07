

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import auction.web.Auction;
import auction.web.Category;
import auction.web.Item;
import auction.web.Registration;
import auction.web.User;
import com.mycompany.auctionwebclient.AuctionClient;
import com.mycompany.auctionwebclient.RegistrationClient;
import java.util.Vector;

public class AuctionMgrTest {

//    private Auction auction;
//    private Registration registration;
    
    private AuctionClient auctionClient;
    private RegistrationClient registrationClient;

    @Before
    public void setUp() throws Exception {
//        registration = new Registration();
//        auction = new AuctionMgr();
//        sellerMgr = new SellerMgr();
        auctionClient = new AuctionClient();
        registrationClient = new RegistrationClient();
    }
    
    
    @Test
    public void getItem()
    {
        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = registrationClient.registerUser(email);
        Category cat = registrationClient.createCategory("cat2");
        Item item1 = auctionClient.offerItem(seller1, cat, omsch);
        Item item2 = auctionClient.getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        User seller3 = registrationMgr.registerUser(email3);
        User seller4 = registrationMgr.registerUser(email4);
        Category cat = registrationMgr.createCategory("cat3");
        Item item1 = sellerMgr.offerItem(seller3, cat, omsch);
        Item item2 = sellerMgr.offerItem(seller4, cat, omsch);

        Vector res = (Vector) auctionMgr.findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = (Vector) auctionMgr.findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {

        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = registrationMgr.registerUser(email);
        User buyer = registrationMgr.registerUser(emailb);
        User buyer2 = registrationMgr.registerUser(emailb2);
        // eerste bod
        Category cat = registrationMgr.createCategory("cat9");
        Item item1 = sellerMgr.offerItem(seller, cat, omsch);
       
        Bid new1 = auctionMgr.newBid(item1, buyer, new Money(10L, "eur"));
        assertEquals(emailb, new1.getBuyer().getEmail());

        // lager bod
        Bid new2 = auctionMgr.newBid(item1, buyer2, new Money(9L, "eur"));
        assertNull(new2);

        // hoger bod
        Bid new3 = auctionMgr.newBid(item1, buyer2, new Money(11, "eur"));
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }
}
