package com.lcpg.app;
import com.lcpg.app.frame.LoginFrame;
import javax.swing.UIManager;

public class Cliente {

      public static void main(String args[]) {
        
        //troca layout telas  
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
                
            }
        });
    }
    
}
