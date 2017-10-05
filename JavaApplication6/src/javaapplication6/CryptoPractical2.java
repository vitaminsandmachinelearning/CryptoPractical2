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

    public static void main(String[] args) {
        D6Gen4 d6 = new D6Gen4();
        D10Gen4 d10 = new D10Gen4();
        
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
        
        for (String D6Test1 : D6Test) 
            d6.calc(D6Test1);
        
        for (String D10Test1 : D10Test) 
            d10.calc(D10Test1);
    }
}
