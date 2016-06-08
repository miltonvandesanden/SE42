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
            java.lang.Long arg0 = id;
            // TODO process result here
            return port.getItem(arg0);
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
            java.lang.String arg0 = description;
            // TODO process result here
            return port.findItemByDescription(arg0);
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
            auction.web.Item arg0 = item;
            // TODO process result here
            return port.revokeItem(arg0);
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
            auction.web.Item arg0 = item;
            auction.web.User arg1 = user;
            auction.web.Money arg2 = money;
            // TODO process result here
            return port.newBid(arg0, arg1, arg2);
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
            auction.web.User arg0 = user;
            auction.web.Category arg1 = category;
            java.lang.String arg2 = description;
            // TODO process result here
            return port.offerItem(arg0, arg1, arg2);
//            System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public Money createMoney(Long amount, String currency)
    {
        
        try
        {
            // Call Web Service Operation
            auction.web.AuctionService service = new auction.web.AuctionService();
            auction.web.Auction port = service.getAuctionPort();
            // TODO initialize WS operation arguments here
            java.lang.Long arg0 = amount;
            java.lang.String arg1 = currency;
            // TODO process result here
            return port.createMoney(arg0, arg1);
//            System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }

    }
    
    
    
    
}
