/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.util.Arrays;
import java.math.*;

/**
 *
 * @author Jake
 */
public class BCHCalculator extends javax.swing.JFrame {

    /**
     * Creates new form BCHCalculator
     */
    public BCHCalculator() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
        int[] sarray = new int[4];
        if(jTextField1.getText().length() == 10)
            if(!jTextField1.getText().contains("[1-9]"))
            {
                sarray = Arrays.copyOfRange(s.calculate(jTextField1.getText()),10,14);
                if(quickthreeerrorcheck(s.calculate(jTextField1.getText())))
                {
                    jLabel1.setText("At least three errors occured.");
                    return;
                }
                errorCheck(sarray);
            }
            else
                jLabel1.setText("Invalid input. Only include 10 digits from 1-9.");
        else
            jLabel1.setText("Invalid length. Only include 10 digits.");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    boolean quickthreeerrorcheck(int[] narray)
    {
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
        //single error
        int P = (sarray[1] * sarray[1] - sarray[0] * sarray[2]) % 11;
        int Q = (sarray[0] * sarray[3] - sarray[1] * sarray[2]) % 11;
        int R = (sarray[2] * sarray[2] - sarray[1] * sarray[3]) % 11;
        P = P < 0 ? P + 11 : P;
        Q = Q < 0 ? Q + 11 : Q;
        R = R < 0 ? R + 11 : R;
        System.out.println(String.format("P:%d Q:%d R:%d",P,Q,R));
        System.out.println(String.format("S1:%d S2:%d S3:%d S4:%d", sarray[0], sarray[1], sarray[2], sarray[3]));
        if(P == Q && Q == R && R == 0)
        {
            jLabel1.setText("Single error. Position " + sarray[1] / sarray[0] + ". Magnitude " + sarray[0]);
            System.out.println("Single error");
            System.out.println("a: " + sarray[0] + " i: " + sarray[1]/sarray[0]);
            return;
        }
        //double error
        int sqrtn = (Q * Q - 4*P*R) % 11;
        sqrtn = sqrtn < 0 ? sqrtn + 11 : sqrtn;
        if(sqrtn == 0 || sqrttable[sqrtn - 1] == 0)
        {
            jLabel1.setText("At least three errors occured.");
            return;
        }
        System.out.println("tag1 " + (2*P)%11);
        System.out.println("tag2 " + ((-Q + sqrttable[sqrtn - 1]) % 11));
        System.out.println("tag3 " + (double)-6/9);
        int I = (((-Q + sqrttable[sqrtn - 1]) % 11) / ((2 * P) % 11)) % 11;
        int J = (((-Q - sqrttable[sqrtn - 1]) % 11) / ((2 * P) % 11)) % 11;
        System.out.println(String.format("I:%d J:%d", I, J));
        int B = (((I * sarray[0] - sarray[1]) % 11) / ((I - J) % 11)) % 11;
        int A = (sarray[0] - B) % 11;
        System.out.println(String.format("I:%d J:%d A:%d B:%d", I, J, A, B));
        if(I == 0 || J == 0)
        {
            jLabel1.setText("At least three errors occured.");
            return;
        }
        jLabel1.setText(String.format("Double errors. Positions %d and %d. Magnitudes %d and %d", 
                I, J, A, B));
        
        int[] narray = new int[jTextField1.getText().length() + 4];
        for(int i = 0; i < jTextField1.getText().length(); i++)
            narray[i] = Character.getNumericValue(jTextField1.getText().charAt(i));
        narray[I - 1] += A;
        narray[J - 1] += B;
        String fixed = "";
        for(int i : narray)
            fixed += Character.getNumericValue(i);
        jTextField1.setText(fixed);
    }
    
    Syndromes s = new Syndromes();
    
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
