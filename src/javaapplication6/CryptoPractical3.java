/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author jm2-radford
 */
public class CryptoPractical3 {
    String[] toCheck;
    Syndromes syn = new Syndromes();

    public CryptoPractical3()
    {
        for(String s : toCheck)
            syn.calculate(s);
    }
}
