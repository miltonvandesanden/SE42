/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.auctionwebclient;

import auction.web.Category;
import auction.web.Item;
import auction.web.User;
import java.util.Iterator;
import javax.xml.ws.WebServiceClient;

/**
 *
 * @author Stefan
 */
@WebServiceClient
public class RegistrationClient
{
    public User registerUser(String email)
    {
        try
        {
            // Call Web Service Operation
            auction.web.RegistrationService service = new auction.web.RegistrationService();
            auction.web.Registration port = service.getRegistrationPort();
            // TODO initialize WS operation arguments here
            java.lang.String arg0 = email;
            // TODO process result here
            return port.registerUser(arg0);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public Category createCategory(String description)
    {
        try
        {
            // Call Web Service Operation
            auction.web.RegistrationService service = new auction.web.RegistrationService();
            auction.web.Registration port = service.getRegistrationPort();
            // TODO initialize WS operation arguments here
            java.lang.String arg0 = description;
            // TODO process result here
            return port.createCategory(arg0);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public User getUser(String email)
    {
        try
        {
            // Call Web Service Operation
            auction.web.RegistrationService service = new auction.web.RegistrationService();
            auction.web.Registration port = service.getRegistrationPort();
            // TODO initialize WS operation arguments here
            java.lang.String arg0 = email;
            // TODO process result here
            return port.getUser(arg0);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public Iterator<User> getUsers()
    {
        
        try
        {
            // Call Web Service Operation
            auction.web.RegistrationService service = new auction.web.RegistrationService();
            auction.web.Registration port = service.getRegistrationPort();
            // TODO process result here
            return (Iterator<User>)port.getUsers();
//            System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return null;
            // TODO handle custom exceptions here
        }
    }
    
    public int numberOfOfferedItems(User user)
    {
        try
        {
            // Call Web Service Operation
            auction.web.RegistrationService service = new auction.web.RegistrationService();
            auction.web.Registration port = service.getRegistrationPort();
            // TODO initialize WS operation arguments here
            auction.web.User arg0 = user;
            // TODO process result here
            return port.numberOfOfferedItems(arg0);
//            System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return 0;
            // TODO handle custom exceptions here
        }
    }
}
