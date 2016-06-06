/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorws_client_application;

import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.NegativeNumberException;
import webservice.NegativeNumberException_Exception;

/**
 *
 * @author Stefan
 */
public class CalculatorWS_Client_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 3;
        int j = 4;
        try
        {
            System.out.println(add(i, j)); 
        }
        catch (NegativeNumberException_Exception ex) 
        {
            Logger.getLogger(CalculatorWS_Client_Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static int add(int arg0, int arg1) throws NegativeNumberException_Exception {
        webservice.WebCalculatorService service = new webservice.WebCalculatorService();
        webservice.WebCalculator port = service.getWebCalculatorPort();
        return port.add(arg0, arg1);
    }
    
}
