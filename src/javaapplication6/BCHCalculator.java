/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.util.Arrays;

/**
 *
 * @author Jake
 */
public class BCHCalculator extends javax.swing.JFrame {

    /**
     * Creates new form BCHCalculator
     */
    BCH bch = new BCH();
    public BCHCalculator() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Status...");

        jButton1.setText("Check");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        switch (jTextField1.getText().length()) {
            case 10:
                if(!jTextField1.getText().contains("[1-9]"))//make sure only numbers are input
                {
                    //Run quick three error check to see if any number d1-d10 is 10
                    if(quickthreeerrorcheck(s.calculate(jTextField1.getText())))
                    {
                        jLabel1.setText("At least three errors occured.");
                        return;
                    }
                    //Only pass the syndromes because nothing else is needed past this point
                    errorCheck(Arrays.copyOfRange(s.calculate(jTextField1.getText()),10,14));
                }
                else
                    jLabel1.setText("Invalid input. Only include 10 digits from 1-9.");
                break;
            case 6:
                //if the number input is of length 6, try to encode it using BCH 10,6
                jTextField1.setText(bch.output(jTextField1.getText()));
                break;
            default:
                jLabel1.setText("Invalid length. Only include 6 or 10 digits.");
                break;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    boolean quickthreeerrorcheck(int[] narray)
    {
        //If any of the values from d1-d10 are 10, the number has more than 2
        //total errors
        for(int i = 0; i < 10; i++)
            if(narray[i] == 10)
                return true;
        return false;
    }
    
    void errorCheck(int[] sarray)
    {
        //no error
        if(sarray[0] == sarray[1] && sarray[1] == sarray[2] && sarray[2] == sarray[3] && sarray[3] == 0)
        {
            jLabel1.setText("No errors");
            System.out.println("No errors");
            return;
        }
        //single error correction starts
        int P = (sarray[1] * sarray[1] - sarray[0] * sarray[2]) % 11;
        int Q = (sarray[0] * sarray[3] - sarray[1] * sarray[2]) % 11;
        int R = (sarray[2] * sarray[2] - sarray[1] * sarray[3]) % 11;
        //NOTE: This is to make sure the numbers actually have % applied.
        //Java implementation of % returns the remainder rather than applying
        //mod x to the number
        //Implement this with n = n < 0 ? n + m : n; with n being the number 
        //and m being the number to mod by
        P = P < 0 ? P + 11 : P;
        Q = Q < 0 ? Q + 11 : Q;
        R = R < 0 ? R + 11 : R;
        //if P=Q=R=0 then 1 error occured, fix the error and display in text box
        if(P == Q && Q == R && R == 0)
        {
        System.out.println(String.format("S1: %d S2: %d S3: %d S4: %d", sarray[0], sarray[1], sarray[2], sarray[3]));
            setText(sarray[1]/sarray[0],sarray[0]);
            
            jLabel1.setText("Single error. Position " + sarray[1] / sarray[0] + ". Magnitude " + sarray[0]);
            return;
        }
        //double error correction starts
        //NOTE: Solve these parts here and reference them when they are needed
        //to save computing them twice
        int sqrtn = (Q * Q - 4*P*R) % 11;
        sqrtn = sqrtn < 0 ? sqrtn + 11 : sqrtn;
        int inv = inverse((2 * P), 11);    
        //if the sqrttable doesnt have the value of sqrtn, at least 3 errors occured
        if(sqrtn == 0 || sqrttable[sqrtn - 1] == 0)
        {
            jLabel1.setText("At least three errors occured. No sqrt.");
            return;
        }
        //calculate indexes of two errors and their magnitudes
        int I = ((-Q + sqrttable[sqrtn - 1]) * inv) % 11;
        int J = ((-Q - sqrttable[sqrtn - 1]) * inv) % 11;
        I = I < 0 ? I + 11 : I;
        J = J < 0 ? J + 11 : J;
        int B = ((I * sarray[0] - sarray[1]) / (I - J)) % 11;
        int A = (sarray[0] - B) % 11;
        System.out.println(B + " " + A);
        B = B < 0 ? B + 11 : B;
        A = A < 0 ? A + 11 : A;
        System.out.println(String.format("P: %d Q: %d R: %d", P, Q, R));
        System.out.println(String.format("I:%d J:%d A:%d B:%d", I, J, A, B));
        System.out.println(String.format("S1: %d S2: %d S3: %d S4: %d", sarray[0], sarray[1], sarray[2], sarray[3]));
        if(I == 0 || J == 0) //if the index is 0, at least 3 errors occured
        {
            jLabel1.setText("At least three errors occured.");
            return;
        }
        jLabel1.setText(String.format("Double errors. Positions %d and %d. Magnitudes %d and %d", I, J, A, B));
        setText(I, J, A, B);
    }
    
    //Repair corrupted code with 2 errors by calling the 1 error repair twice
    void setText(int i, int j, int a, int b)
    {
        setText(i, a);
        setText(j, b);
    }
    
    //Repair corrupted code with 1 error
    void setText(int i, int a)
    {
        int[] narray = new int[jTextField1.getText().length() + 4];
        for(int x = 0; x < jTextField1.getText().length(); x++)
            narray[x] = Character.getNumericValue(jTextField1.getText().charAt(x));
        String fixed = "";
        narray[i - 1] -= a;
        narray[i - 1] %= 11;
        narray[i - 1] = narray[i - 1] < 0 ? narray[i - 1] + 11 : narray[i - 1];
        for(int x = 0; x < 10; x++)
            fixed += narray[x];
        jTextField1.setText(fixed);
    }
    
    //Find the inverse of a number
    int inverse(int x, int m)
    {
        x = x%m;                    //ensure x is lowest possible mod.
        for(int i = 1; i < m; i++)  //check each n below the mod to find an 
            if((x*i) % m == 1)      //inverse if it exists.
                return i;           //if the mod of x * i is 1, return i.
        return Integer.MAX_VALUE;   //this is hacky, if it doesnt have inverse,
                                    //return max value of an int so it's obvious
    }
    
    Syndromes s = new Syndromes();
    
    //Table of possible square root values, if 0 will be ignored
    int[] sqrttable = {1,0,5,2,4,0,0,0,3,0};
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BCHCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BCHCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BCHCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BCHCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BCHCalculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
