/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pkg2;

import java.awt.Color;

/**
 *
 * @author kha
 */
public class NavFrame extends javax.swing.JFrame {

    protected char[] pieceCoordinate = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private long minTimeBlack = 1000000;
    private long maxTimeBlack = 0;
    private long sumTimeBlack = 0;
    private int noMoveBlack = 0;
    private long avrTimeBlack;

    private long minTimeWhite = 1000000;
    private long maxTimeWhite = 0;
    private long sumTimeWhite = 0;
    private int noMoveWhite = 0;
    private long avrTimeWhite;

    public void showInfor2(String piece, int src, int des, int alliance, long time) {
        String s = String.format("%-20s%-10s%-6s%-6s\n", exchange(piece, alliance), convert(src), convert(des), time * 1.0 / 1000);
//                jTextArea1.append("\n" +exchange(piece,alliance) +"\t"+ src+"\t" + des);
        if (alliance == 1) {
            if (time <= minTimeBlack) {
                minTimeBlack = time;
            }
            if (time >= maxTimeBlack) {
                maxTimeBlack = time;
            }
            sumTimeBlack += time;
            noMoveBlack += 1;

            avrTimeBlack = sumTimeBlack / noMoveBlack;

            bmit.setText("");
            String d = "" + minTimeBlack * 1.0 / 1000;
            bmit.setText(d);

            bmat.setText("");
            d = "" + maxTimeBlack * 1.0 / 1000;
            bmat.setText(d);

            bat.setText("");
            d = "" + avrTimeBlack * 1.0 / 1000;
            bat.setText(d);
        }

        if (alliance == 0) {
            if (time <= minTimeWhite) {
                minTimeWhite = time;
            }
            if (time >= maxTimeWhite) {
                maxTimeWhite = time;
            }
            sumTimeWhite += time;
            noMoveWhite += 1;

            avrTimeWhite = sumTimeWhite / noMoveWhite;

            wmit.setText("");
            String d = "" + minTimeWhite * 1.0 / 1000;
            wmit.setText(d);

            wmat.setText("");
            d = "" + maxTimeWhite * 1.0 / 1000;
            wmat.setText(d);

            wat.setText("");
            d = "" + avrTimeWhite * 1.0 / 1000;
            wat.setText(d);
        }

        jTextArea1.insert(s, 52);

    }

    String convert(int number) {
        String s = "";

        return s + pieceCoordinate[number % 8] + (8 - number / 8);
    }

    private String exchange(String name, int alliance) {
        String pieceName = "";
        if (alliance == 0) {
            switch (name) {
                case "K":
                    pieceName = "WHITE KING  ";
                    break;
                case "R":
                    pieceName = "WHITE ROOK  ";
                    break;
                case "P":
                    pieceName = "WHITE PAWN  ";
                    break;
                case "N":
                    pieceName = "WHITE KNIGHT";
                    break;
                case "Q":
                    pieceName = "WHITE QUEEN ";
                    break;
                case "B":
                    pieceName = "WHITE BISHOP";
                    break;

            }
        }
        if (alliance == 1) {
            switch (name) {
                case "K":
                    pieceName = "BLACK KING  ";
                    break;
                case "R":
                    pieceName = "BLACK ROOK  ";
                    break;
                case "P":
                    pieceName = "BLACK PAWN  ";
                    break;
                case "N":
                    pieceName = "BLACK KNIGHT";
                    break;
                case "Q":
                    pieceName = "BLACK QUEEN ";
                    break;
                case "B":
                    pieceName = "BLACK BISHOP";
                    break;

            }
        }
        return pieceName;
    }

//    public static ImagePanel ip = new ImagePanel();
//    public static void rePaint(){
//        
//    }
    /**
     * Creates new form NavFrame
     *
     */
    public NavFrame() {
        initComponents();
        this.setLocation(850, 100);
        this.setVisible(true);
        this.setResizable(false);
        String s = String.format("%-28s%-10s%-7s%-5s\n", "Piece", "From", "To", "Time");
        jTextArea1.setText(s);

        StringBuffer s1 = new StringBuffer();

        this.setLayout(null);
        this.jTextArea1.setVisible(true);

        if (Table.mode == 1) {
            jLabel3.setText("Human VS Computer");
            wl.setText("Human");
            switch (Table.blacklevel) {
                case 2:
                    bl.setText("2");
                    break;
                case 3:
                    bl.setText("3");
                    break;
                case 4:
                    bl.setText("4");
                    break;
                case 5:
                    bl.setText("5");
                    break;
                case 6:
                    bl.setText("6");
                    break;
            }
        }
        if (Table.mode == 0) {
            jLabel3.setText("Computer VS Computer");
            switch (Table.blacklevel) {
                case 2:
                    bl.setText("2");
                    break;
                case 3:
                    bl.setText("3");
                    break;
                case 4:
                    bl.setText("4");
                    break;
                case 5:
                    bl.setText("5");
                    break;
                case 6:
                    bl.setText("6");
                    break;
            }

            switch (Table.whitelevel) {
                case 2:
                    wl.setText("2");
                    break;
                case 3:
                    wl.setText("3");
                    break;
                case 4:
                    wl.setText("4");
                    break;
                case 5:
                    wl.setText("5");
                    break;
                case 6:
                    wl.setText("6");
                    break;
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bmit = new javax.swing.JLabel();
        wmat = new javax.swing.JLabel();
        bmat = new javax.swing.JLabel();
        bat = new javax.swing.JLabel();
        wmit = new javax.swing.JLabel();
        wat = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bl = new javax.swing.JLabel();
        wl = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(290, 500));

        jButton1.setText("Undo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("New Game");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Redo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(15);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("MODE : ");

        jLabel3.setText("jLabel3");

        jLabel4.setText("MinTime");

        jLabel5.setText("MaxTime");

        jLabel6.setText("AvrTime");

        jLabel7.setText("Black :");

        jLabel9.setText("White : ");

        bmit.setText("0");

        wmat.setText("0");

        bmat.setText("0");

        bat.setText("0");

        wmit.setText("0");

        wat.setText("0");

        jLabel10.setText("Level");

        bl.setText("jLabel11");

        wl.setText("jLabel12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(wl)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wmit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bmit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bmat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wmat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(22, 22, 22))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(bmit)
                    .addComponent(bmat)
                    .addComponent(bat)
                    .addComponent(bl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(wl))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(wat)
                        .addComponent(wmat)
                        .addComponent(wmit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new MainFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        MainFrame.table.undoTable();

        MainFrame.table.validate();
        MainFrame.table.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MainFrame.table.redoTable();

        MainFrame.table.validate();
        MainFrame.table.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(NavFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NavFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NavFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NavFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NavFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bat;
    private javax.swing.JLabel bl;
    private javax.swing.JLabel bmat;
    private javax.swing.JLabel bmit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel wat;
    private javax.swing.JLabel wl;
    private javax.swing.JLabel wmat;
    private javax.swing.JLabel wmit;
    // End of variables declaration//GEN-END:variables

    private void setLocale(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
