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
	import javax.swing.JScrollPane;
	import javax.swing.JLabel;
	import javax.swing.JCheckBox;
	import javax.swing.JButton;

	import java.awt.Graphics;
	import java.awt.image.BufferedImage;
	import javax.swing.ImageIcon;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import javax.imageio.ImageIO;

public class WelcomeWindow {		
	JFrame frame;
	JCheckBox checkBox;
	JLabel welcomeLabel, infoLabel;
	JButton welcomeButton, infoButton;
	JPanel checkPanel, textPanel, infoPanel;
	
	/* uncomment for testing purposes
	public static void main (String[] args){
		welcomeWindow test = new welcomeWindow();
		test.go();
	}*/
	
    	public void go(){
		frame = new JFrame();
		frame.setSize(700,800);
		frame.setTitle("Welcome");
		frame.setLocationRelativeTo(null) ;
		//DISPOSE closes just this frame not all associated with CryptoGui
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		checkPanel = new JPanel();
		textPanel = new JPanel();
		welcomeLabel = new JLabel();
		infoButton = new JButton("<html>Input<br>Info<br><font size=+2>&gt;</font></html>");
		welcomeButton = new JButton("<html><br><font size=+2> &lt; </font></html>");
		
		infoButton.addActionListener(new infoButtonListener());
		welcomeButton.addActionListener(new welcomeButtonListener());
		welcomeLabel.setText("<html><b><u><font size=+2>Welcome to this Cryptography "
				+ "Cipher </font></u></b><font size=+1><br> &nbsp This consists of 4 ciphers"
				+ "<font size=-1><br><br>  &emsp -Shift Cipher: Takes an integer a as the key. "
				+ "Encrypts the plaintext <br> &emsp&emsp&emsp by shifting the characters along "
				+ "by adding the key to each of the <br> &emsp&emsp&emsp characters and taking "
				+ "modulus 26 <br><br>&emsp  -Affine Cipher: Takes 2 integers (a and b) as the key. "
				+ "Encrypts the <br> &emsp&emsp&emsp plaintext by multiplying the character"
				+ "values by a and then <br> &emsp&emsp&emsp adding b, taking modulus 26<br> "
				+ "<br>&emsp -Vigenere Cipher: Takes a String s as the key. Encrypts the<br>"
				+ "&emsp&emsp&emsp plaintext by applying the Shift Cipher to each character <br>"
				+ "&emsp&emsp&emsp in the plaintext using the associated letter in the key as the key."
				+ "<br> <br>&emsp -Bifid Cipher: Takes a String s as the key. Encrypts the plaintext <br>"
				+ "&emsp&emsp&emsp by applying the Bifid Cipher to each character using a 25 letter<br> "
				+ "&emsp&emsp&emsp 'key square' example of encryption using this 25 letter 'key square'"
				+ "<font size=+1><br><br> Try it yourself!</font></html>");

		checkBox = new JCheckBox("Do Not Show This Welcome Window Again");
		checkPanel.add(checkBox);		
		textPanel.add(welcomeLabel);
		frame.getContentPane().add(BorderLayout.EAST, infoButton);
		frame.getContentPane().add(BorderLayout.CENTER, textPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, checkPanel);
		checkBox.addActionListener(new CheckListener());
		
		JLabel imgLabel = new JLabel(new ImageIcon("lib/enc.jpg"));
		frame.getContentPane().add(BorderLayout.SOUTH,imgLabel);
		/*BufferedImage myPicture = ImageIO.read(new File("lib/enc.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		frame.getContentPane().add(picLabel);
		*/
		
		frame.setVisible(true);	
	}
	class infoButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			infoLabel = new JLabel();
			
			infoLabel.setText("<html><b><u><font size=+2> Format of Input for GUI:</font></u></b>"
					+ "<br>Spaces can be used for plaintext input fields, but only "
					+ "certain keys utilize spaces. <br>Capital letters for plaintext can be used "
					+ "but will be translated to lowercase <br>prior to any cryptographic operation "
					+ "being performed on them.<br><br>Shift Cipher: <br>   Key text field should "
					+ "contain a single integer within the range of 0 to 25 inclusive.<br><br>Affine "
					+ "Cipher:<br>    Key text field should contain two integers seperated by a single "
					+ "space, <br>with the first integer being with the range of 0 to 25 "
					+ "<br>inclusive and the second integer being greater than 0. <br>If decrypting, "
					+ "the additional condition that the first integer must <br> be a"
					+ "coprime with 26.<br><br>Vigenere Cipher:<br>   Key text field "
					+ "should contain a string of characters.<br><br>Bifid Cipher:<br>   The"
					+ "plaintext for the Bifid Cipher can contain spaces but no numbers <br>and"
					+ " it will convert all characters to uppercase. Key text field should contain<br> a string of characters with at least"
					+ "one uppercase letter.<br><br> All Ciphers:<br>    Keys should follow the format "
					+ "of the respective cyphers. <br>All four keys must be used and there"
					+ "cannot be any spaces <br>within the key string except"
					+ "for the affine cypher key.<br><br>Current: Do not change or input new strings"
					+ "into the input or key text fields. <br>The program will clear the textfields"
					+ "and will encrypt or decrypt the last encrypted string"
					+ "<br>When decrypting, separate the plaintext inputs "
					+ "using <br>commas with no spaces before or after the comma.<br><br> "
					+ "Copy:<br>Copies the output text into clipboard, use ctrl + v to paste.<br><br> "
					+ "Clean:<br> Hit clean to clean all the text fields.<br> </html>");
			textPanel.remove(welcomeLabel);
			textPanel.add(new JScrollPane(infoLabel));
			textPanel.add(infoLabel);
			
			frame.getContentPane().remove(infoButton);
			frame.getContentPane().add(BorderLayout.WEST, welcomeButton);
			frame.getContentPane().revalidate();
		}
	}
	class welcomeButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			textPanel.add(welcomeLabel);
			infoLabel.setText("");

			frame.getContentPane().remove(welcomeButton);
			frame.getContentPane().revalidate();
			frame.getContentPane().add(BorderLayout.EAST, infoButton);
			frame.getContentPane().add(BorderLayout.CENTER, textPanel);
		}
	}	
	// inside class that implements the ActionListener class to all the checkBox to give output file.
	class CheckListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			File file = new File("welcomeWin.txt"); 
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


