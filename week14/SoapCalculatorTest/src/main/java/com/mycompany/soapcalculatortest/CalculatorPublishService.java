/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapcalculatortest;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Stefan
 */
public class CalculatorPublishService
{
    private static final String url = "http://localhost:8080/WebCalculator";
    
    public static void main(String[] args)
    {
        Endpoint.publish(url, new CalculatorManager());
    }
}
