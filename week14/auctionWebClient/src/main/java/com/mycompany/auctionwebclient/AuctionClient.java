/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.auctionwebclient;

import auction.web.Bid;
import auction.web.Category;
import auction.web.Item;
import auction.web.Money;
import auction.web.User;
import java.util.List;
import javax.xml.ws.WebServiceClient;

/**
 *
 * @author Stefan
 */
@WebServiceClient
public class AuctionClient
{    
    public Item getItem(Long id)
    {
        try
        {
            // Call Web Service Operation
            auction.web.AuctionService service = new auction.web.AuctionService();
            auction.web.Auction port = service.getAuctionPort();
            // TODO initialize WS operation arguments here
            //java.lang.Long arg0 = Long.valueOf(0L);
            // TODO process result here
            return port.getItem(id);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public List<Item> findItemByDescription(String description)
    {
        try
        {
            // Call Web Service Operation
            auction.web.AuctionService service = new auction.web.AuctionService();
            auction.web.Auction port = service.getAuctionPort();
            // TODO initialize WS operation arguments here
            //java.lang.String arg0 = "";
            // TODO process result here
            return port.findItemByDescription(description);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public boolean revokeItem(Item item)
    {   
        try
        {
            // Call Web Service Operation
            auction.web.AuctionService service = new auction.web.AuctionService();
            auction.web.Auction port = service.getAuctionPort();
            // TODO initialize WS operation arguments here
            //auction.web.Item arg0 = new auction.web.Item();
            // TODO process result here
            return port.revokeItem(item);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return false;
            // TODO handle custom exceptions here
        }
    }
    
    public Bid newBid(Item item, User user, Money money)
    {
        try
        {
            // Call Web Service Operation
            auction.web.AuctionService service = new auction.web.AuctionService();
            auction.web.Auction port = service.getAuctionPort();
            // TODO initialize WS operation arguments here
//            auction.web.Item arg0 = new auction.web.Item();
//            auction.web.User arg1 = new auction.web.User();
//            auction.web.Money arg2 = new auction.web.Money();
            // TODO process result here
            return port.newBid(item, user, money);
//            System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public Item offerItem(User user, Category category, String description)
    {
        try
        {
            // Call Web Service Operation
            auction.web.AuctionService service = new auction.web.AuctionService();
            auction.web.Auction port = service.getAuctionPort();
            // TODO initialize WS operation arguments here
//            auction.web.User arg0 = new auction.web.User();
//            auction.web.Category arg1 = new auction.web.Category();
//            java.lang.String arg2 = "";
            // TODO process result here
            return port.offerItem(user, category, description);
//            System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    
    
    
}
