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
public class D6Gen4 {
    public void calc(String input)
    {
        int[] narray = new int[input.length() + 4];
        
        for(int i = 0; i < input.length(); i++)
            narray[i] = Character.getNumericValue(input.charAt(i));
        
        narray[6] = (4 * narray[0] + 10 * narray[1] + 9 * narray[2] + 2 * narray[3] + narray[4] + 7 * narray[5]) % 11;
        narray[7] = (7 * narray[0] + 8 * narray[1] + 7 * narray[2] + narray[3] + 9 * narray[4] + 6 * narray[5]) % 11;
        narray[8] = (9 * narray[0] + narray[1] + 7 * narray[2] + 8 * narray[3] + 7 * narray[4] + 7 * narray[5]) % 11;
        narray[9] = (narray[0] + 2 * narray[1] + 9 * narray[2] + 10 * narray[3] + 4 * narray[4] + narray[5]) % 11;
        
        for(int i = 6; i < narray.length; i++)
            if(narray[i] > 9)
            {
                System.out.println("Unusable number");
                return;
            }
        
        for(int n : narray)
            System.out.print(n);
        System.out.println();
    }
}
    
