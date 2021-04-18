package typewar;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class single_player extends javax.swing.JFrame {
    
    private String[] words;
    private int ind, len, count;
    private Integer time_left;
    
    public single_player() {
        time_left = 59;
        len = 0;
        count = 0;
        words = new String[200005];
        get_words();
        shuffle(words);
        
         try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
         
        initComponents();
        word.setText(words[0]);
        ind = 0;
        
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                while(time_left >= 0){
                    time.setText(time_left.toString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(single_player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    time_left--; 
                }
                
                JOptionPane speed = new JOptionPane("Your typing speed is " + count + " words per minute");
                JDialog dialog = speed.createDialog(null, "");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                while(speed.getValue() == JOptionPane.UNINITIALIZED_VALUE){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                            Logger.getLogger(play_client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.exit(0);
            }
        });
        
        t.start();
    }

    Connection connect(){
        String url = "jdbc:sqlite:C:\\Users\\tridi\\Documents\\typewardb\\dictionary.db";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);   
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    void get_words(){
        String sql = "Select * from words";
        
        try{
            Connection conn = connect();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            
            while(res.next()){
                String s = res.getString("word");
                if(s.length() >= 3) words[len++] = s; 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    void shuffle(String[] w){
        Random rnd = ThreadLocalRandom.current();
        for(int i=len;i>=0;i--){
            int index = rnd.nextInt(i+1);
            String s = w[index];
            w[index] = w[i];
            w[i] = s;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        type_area = new javax.swing.JTextField();
        word = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(0, 0, 0));
        panel.setPreferredSize(new java.awt.Dimension(1000, 800));

        type_area.setBackground(new java.awt.Color(0, 0, 0));
        type_area.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        type_area.setForeground(new java.awt.Color(255, 255, 255));
        type_area.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        type_area.setBorder(null);
        type_area.setOpaque(false);
        type_area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                type_areaKeyPressed(evt);
            }
        });

        word.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        word.setForeground(new java.awt.Color(255, 255, 255));
        word.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        word.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        time.setBackground(new java.awt.Color(0, 0, 0));
        time.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(type_area, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addComponent(word, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229)
                .addComponent(word, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addComponent(type_area, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void type_areaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_type_areaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            String text = type_area.getText();
            text = text.trim();
            if(text.equals(words[ind])){
                ind++;
                count++;
                if(ind >= len){
                    ind=0;
                    shuffle(words);
                }
                word.setText(words[ind]);
                type_area.setText(null);
            }
        }
    }//GEN-LAST:event_type_areaKeyPressed

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
            java.util.logging.Logger.getLogger(single_player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(single_player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(single_player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(single_player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new single_player().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private javax.swing.JLabel time;
    private javax.swing.JTextField type_area;
    private javax.swing.JLabel word;
    // End of variables declaration//GEN-END:variables
}
