package edu.ucsb.cs56.projects.utilities.cryptography;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class WelcomeWindow {		
	JFrame frame;
	JCheckBox checkBox;
	JLabel welcomeLabel, infoLabel;
	JButton welcomeButton, infoButton;
	JPanel checkPanel, textPanel, infoPanel;
	
    	public void go(){
		frame = new JFrame();
		frame.setSize(680,680);
		frame.setTitle("Welcome");
		frame.setLocationRelativeTo(null) ;
		//DISPOSE closes just this frame not all associated with CryptoGui
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		checkPanel = new JPanel();
		textPanel = new JPanel();
		welcomeLabel = new JLabel();
		infoButton = new JButton("<html>Input<br>Info</html>");

		checkBox = new JCheckBox("Do Not Show This Welcome Window Again");
		checkPanel.add(checkBox);		
		textPanel.add(welcomeLabel);
		frame.getContentPane().add(BorderLayout.EAST, infoButton);
		frame.getContentPane().add(BorderLayout.CENTER, textPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, checkPanel);
		checkBox.addActionListener(new CheckListener());
		
		
		
		frame.setVisible(true);	
	}
	// inside class that implements the ActionListener class to all the checkBox to give output file.
	class CheckListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			File file = new File("WelcomeWin.txt"); 
			//tests to see if the checkbox is selected then decides to either create
			// or delete the welcomeWindow.txt file
			if (checkBox.isSelected()){
				//Create the file
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//Write Content
				FileWriter writer;
				try {
					writer = new FileWriter(file);
					writer.write("Checked");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else 
			file.delete();
		}
	}
}

