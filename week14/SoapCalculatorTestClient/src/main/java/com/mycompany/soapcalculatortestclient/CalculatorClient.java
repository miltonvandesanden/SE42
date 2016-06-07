/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapcalculatortestclient;

/**
 *
 * @author Stefan
 */
public class CalculatorClient
{
    public static void main(String[] args)
    {
    }
    
    public int minus()
    {
        try
        {
            // Call Web Service Operation
            com.mycompany.soapcalculatortest.CalculatorManagerService service = new com.mycompany.soapcalculatortest.CalculatorManagerService();
            com.mycompany.soapcalculatortest.CalculatorManager port = service.getCalculatorManagerPort();
            // TODO initialize WS operation arguments here
            int arg0 = 7;
            int arg1 = 3;
            // TODO process result here
            return port.add(arg0, arg1);
            //System.out.println("Result = "+result);
        }
        catch (Exception ex)
        {
            return 0;
            // TODO handle custom exceptions here
        }
    }
    
    

    
}

