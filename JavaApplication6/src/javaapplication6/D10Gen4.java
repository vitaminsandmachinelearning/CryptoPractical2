/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.util.Scanner;

/**
 *
 * @author jm2-radford
 */
public class D10Gen4 {
    public void calc(String input)
    {
        int[] narray = new int[input.length() + 4];
        
        for(int i = 0; i < input.length(); i++)
            narray[i] = Character.getNumericValue(input.charAt(i));
        
        narray[10] = (narray[0] + narray[1] + narray[2] + narray[3] + narray[4] + narray[5] + narray[6] + narray[7] + narray[8] + narray[9]) % 11;
        narray[11] = (narray[0] + narray[1] * 2 + narray[2] * 3 + narray[3] * 4 + narray[4] * 5 + narray[5] * 6 + narray[6] * 7 + narray[7] * 8 + narray[8] * 9 + narray[9] * 10) % 11;
        narray[12] = (narray[0] + narray[1] * 4 + narray[2] * 9 + narray[3] * 5 + narray[4] * 3 + narray[5] * 3 + narray[6] * 5 + narray[7] * 9 + narray[8] * 4 + narray[9]) % 11;
        narray[13] = (narray[0] + narray[1] * 8 + narray[2] * 5 + narray[3] * 9 + narray[4] * 4 + narray[5] * 7 + narray[6] * 2 + narray[7] * 6 + narray[8] * 3 + narray[9] * 10) % 11;
    
        for(int i = 10; i < narray.length; i++)
            System.out.print(narray[i]);
        System.out.println();
    }
    
}
