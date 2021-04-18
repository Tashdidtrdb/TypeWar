package typewar;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.DriverManager;  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class play_server extends javax.swing.JFrame {
    
    private String[] words;
    private String message;
    private int ind, len, port, count;
    boolean running;
    ServerSocket server;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    public play_server() {
        running = true;
        message = "temp";
        len = 194206;
        count = 0;
        port = 5347; 
        words = new String[200005];
        
        //server side
        try{
            server = new ServerSocket(port);
            socket = server.accept();
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            words = (String[]) in.readObject();
            for(int i=0;i<10;i++) System.out.println(words[i]);
            System.out.println(len);
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
        initComponents();
        my_health.setValue(100);
        opponent_health.setValue(100);
        word.setText(words[0]);
        ind = 0;
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                while(running){
                    try{
                        message = in.readUTF();
                        System.out.println(message);
                        if(message.equals("strike")){
                            my_health.setValue(my_health.getValue() - 30);
                            if(my_health.getValue() <= 0){
                                JOptionPane lose = new JOptionPane("Type faster next time");
                                    JDialog dialog = lose.createDialog(null, "");
                                    dialog.setAlwaysOnTop(true);
                                    dialog.setVisible(true);
                                    while(lose.getValue() == JOptionPane.UNINITIALIZED_VALUE){
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(play_client.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    System.exit(0);
                            }
                        }
                        else if(message.equals("exit")){
                            JOptionPane connection_lost = new JOptionPane("Something went wrong", 
                                    JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = connection_lost.createDialog(null, "Error");
                            dialog.pack();
                            dialog.setModal(false);
                            dialog.setVisible(true);
                            dialog.setAlwaysOnTop(true);
                            
                            while(connection_lost.getValue() == JOptionPane.UNINITIALIZED_VALUE){
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(play_client.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            System.exit(0);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                        running = false;
                        e.printStackTrace();
                        JOptionPane connection_lost = new JOptionPane("Something went wrong", 
                                    JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = connection_lost.createDialog(null, "Error");
                            dialog.pack();
                            dialog.setModal(false);
                            dialog.setVisible(true);
                            dialog.setAlwaysOnTop(true);
                            
                            while(connection_lost.getValue() == JOptionPane.UNINITIALIZED_VALUE){
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(play_client.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            System.exit(0);
                    }
                }
                
                try {
                    socket.close();
                    server.close();
                } catch (IOException ex) {
                    Logger.getLogger(play_client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        t.start();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        type_area = new javax.swing.JTextField();
        my_health = new javax.swing.JProgressBar();
        opponent_health = new javax.swing.JProgressBar();
        word = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        my_health.setString("100");

        opponent_health.setString("100");

        word.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        word.setForeground(new java.awt.Color(255, 255, 255));
        word.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        word.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(type_area))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opponent_health, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(my_health, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 439, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(word, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(my_health, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opponent_health, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
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
                System.out.println(count);
                if(count==5){
                    try{
                        //out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeUTF("strike");
                        out.flush();
                        opponent_health.setValue(opponent_health.getValue() - 30);
                        if(opponent_health.getValue() <= 0){
                            JOptionPane win = new JOptionPane("Congratulations!!");
                            JDialog dialog = win.createDialog(null, "");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                            
                            while(win.getValue() == JOptionPane.UNINITIALIZED_VALUE){
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(play_client.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            System.exit(0);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    count = 0;
                }
                
                if(ind >= len){
                    ind=0;
                    shuffle(words);
                }
                word.setText(words[ind]);
                type_area.setText(null);
            } 
        }
    }//GEN-LAST:event_type_areaKeyPressed
    
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
            java.util.logging.Logger.getLogger(play_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(play_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(play_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(play_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new play_server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar my_health;
    private javax.swing.JProgressBar opponent_health;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField type_area;
    private javax.swing.JLabel word;
    // End of variables declaration//GEN-END:variables
}
