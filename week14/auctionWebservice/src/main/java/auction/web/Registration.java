/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.web;

import auction.domain.Category;
import auction.domain.User;
import auction.service.RegistrationMgr;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Stefan
 */
@WebService
public class Registration
{
    private RegistrationMgr registrationMgr = new RegistrationMgr();
    
    public User registerUser(String email)
    {
        return registrationMgr.registerUser(email);
    }
    
    public User getUser(String email)
    {
        return registrationMgr.getUser(email);
    }
    
    public List<User> getUsers() {
        return registrationMgr.getUsers();
    }
    
    public Category createCategory(String description)
    {
        return registrationMgr.createCategory(description);
    }
    
    public int numberOfOfferedItems(User user)
    {
        return user.numberOfOfferedItems();
    }
}
