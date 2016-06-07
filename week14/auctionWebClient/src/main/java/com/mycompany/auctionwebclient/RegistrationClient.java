/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.auctionwebclient;

import auction.web.Category;
import auction.web.User;
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
}
