package edu.ucsb.cs56.projects.utilities.cryptography;

import javax.swing.JFrame;

/**
   A class to implement the WelcomeWindow that is called by CryptographyGUI
   @author Keith Long
   @version Project CS56, F16, 11/07/16
 */

public class WelcomeWindow
{
    JFrame frame;


  
    frame = new JFrame();
		frame.setSize(680,680);
		frame.setTitle("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
    frame.setVisible(true);
}
