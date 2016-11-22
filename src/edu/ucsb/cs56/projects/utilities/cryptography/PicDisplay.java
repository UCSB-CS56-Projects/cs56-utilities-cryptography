package edu.ucsb.cs56.projects.utilities.cryptography;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PicDisplay{
      JFrame frame;
      JLabel imgLabel;
    
    public void go(){
      frame = new JFrame();
      frame.setSize(1000,900);
      frame.setTitle("");
      frame.setLocationRelativeTo(null) ;
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      imgLabel = new JLabel(new ImageIcon("lib/GUI.jpg"));
      frame.getContentPane().add(imgLabel);      
      frame.setVisible(true);
    }
}


