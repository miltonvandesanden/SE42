/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapcalculatortest;

import javax.jws.WebService;

/**
 *
 * @author Stefan
 */
@WebService
public class CalculatorManager
{
    private AddManager addManager = new AddManager();
    private MinusManager minusManager = new MinusManager();
    
    public int add(int i, int j)
    {
        return addManager.add(i, j);
    }
    
    public int minus(int i, int j)
    {
        return minusManager.minus(i, j);
    }
}
