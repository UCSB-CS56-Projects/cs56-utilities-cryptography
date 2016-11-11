package edu.ucsb.cs56.projects.utilities.cryptography;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JCheckBox;

public class WelcomeWindow {		
	JFrame frame;
	JCheckBox checkBox;
	
    	public void go(){
		frame = new JFrame();
		frame.setSize(680,680);
		frame.setTitle("Welcome");
		frame.setLocationRelativeTo(null) ;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		checkBox = new JCheckBox("Do Not Show This Welcome Window Again");		
		frame.getContentPane().add(BorderLayout.CENTER, checkBox);
		checkBox.addActionListener(new CheckListener());
		frame.setVisible(true);	
	}
	// inside class that implements the ActionListener class to all the checkBox to give output file.
	class CheckListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			File file = new File("WelcomeWin.txt"); 
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

