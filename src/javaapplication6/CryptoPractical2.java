/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class CryptoPractical2 {

    public static void testPractical2(String[] args) {
        BCH bch = new BCH();
        Syndromes d10 = new Syndromes();
        
        String[] D6Test = {
            "000001",
            "000002",
            "000010",
            "000011"
        };
        String[] D10Test = {
            "0000118435",
            "8899880747"
        };
        
        int[][] BCHoutput = new int[4][];
        int[][] synoutput = new int[2][];
        
        for (int i = 0; i < 4; i++)
            BCHoutput[i] = bch.encode(D6Test[i]);
        
        for (int i = 0; i < 2; i++)
            synoutput[i] = d10.calculate(D10Test[i]);
        
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 10; j++)
                System.out.print(BCHoutput[i][j]);
            System.out.println();
        }
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 14; j++)
                System.out.print(synoutput[i][j]);
            System.out.println();
        }
    }
}
