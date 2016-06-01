package com.lcpg.app;

import com.lcpg.app.frame.JFrameServidor;
import com.lcpg.app.service.ServidorService;

public class Servidor {
   public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameServidor().setVisible(true);
            }
        });
    }
}
