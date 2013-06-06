package edu.ucsb.cs56.projects.utilities.cryptography;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

/**
   A class to implement the Cryptography GUI.
   @author Callum Steele
   @author Miranda Aperghis
   @version Project CS56, S13, 06/05/2013
*/

public class CryptographyGUI
{
    
    ShiftCipher shiftCipher = null;
    AffineCipher affineCipher = null;
    VigenereCipher vigenereCipher = null;
    String plainText = null;
    String cipherText = null;
    int keyA, keyB;
    String key;

    JFrame frame;
    JButton shift, vigenere, affine, mode;
    JPanel buttonPanel, cipherButtonPanel, modeButtonPanel, textFieldPanel, inputTextPanel, inputKeyTextPanel;
    JTextField input, keyInput;
    JLabel output, inputText, inputKeyText;

    boolean encryptMode = true;

    /** Calls the function to create the GUI.
	@param args Default arguments sent to main.
     */
    public static void main (String[] args) {
	CryptographyGUI cryptoGUI = new CryptographyGUI();

	cryptoGUI.go();
    }

    /** Function that populates and creates the GUI.
     */
    public void go () {
	// initialise Cipher objects
	shiftCipher = new ShiftCipher();
	affineCipher = new AffineCipher();
	vigenereCipher = new VigenereCipher();

	// setup overall frame options
	frame = new JFrame();
	frame.setSize(400,180);
	frame.setTitle("Cryptography Interface");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);

	// create panel for text fields
	textFieldPanel = new JPanel();
	textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));

	// create panel containing label and textfield for plaintext input
	inputTextPanel = new JPanel();
	inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.X_AXIS));
	inputText = new JLabel();
	inputText.setText("Plaintext: ");
	input = new JTextField();
	inputTextPanel.add(inputText);
	inputTextPanel.add(input);

	// create panel containing label and textfield for key input
	inputKeyTextPanel = new JPanel();
	inputKeyTextPanel.setLayout(new BoxLayout(inputKeyTextPanel, BoxLayout.X_AXIS));
	inputKeyText = new JLabel();
	inputKeyText.setText("Key(s): ");
	keyInput = new JTextField();
	inputKeyTextPanel.add(inputKeyText);
	inputKeyTextPanel.add(keyInput);

	// add panels to overall text field panel
	textFieldPanel.add(inputTextPanel);
	textFieldPanel.add(inputKeyTextPanel);

	// create panel for cipher buttons
	cipherButtonPanel = new JPanel();
	cipherButtonPanel.setLayout(new BoxLayout(cipherButtonPanel, BoxLayout.X_AXIS));

	// create cipher buttons and add listeners
        shift = new JButton("Shift Cipher");
	shift.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // get text from plaintext text field
		    plainText = input.getText();

		    // get key from key text field and set it as key in cipher object
		    try {
			keyA = Integer.parseInt(keyInput.getText());
			shiftCipher.setCipherKey(keyA);
		    
			// checks if encrypting or decrypting
			if (encryptMode)
			    cipherText = shiftCipher.encrypt(plainText);
			else
			    cipherText = shiftCipher.decrypt(plainText);
			
			// puts result in the output label
			output.setText(cipherText);
		    } catch (Exception ex) {
			// create popup
			messagePopUp("Incorrect input for Shift Cipher. Refer to README for correct input format.", "Shift Cipher Input Error");
		    }
		}
	    });
	affine = new JButton("Affine Cipher");
	affine.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // get text from plaintext text field
		    plainText = input.getText();

		    // gets keys from key text field and sets as the keys in cipher object
		    try {
			key = keyInput.getText();
			keyA = Integer.parseInt(key.substring(0, key.indexOf(' ')));
			keyB = Integer.parseInt(key.substring(key.indexOf(' ') + 1));
			affineCipher.setKeyA(keyA);
			affineCipher.setKeyB(keyB);
		    
			// checks if encrypting or decrypting
			if (encryptMode)
			    cipherText = affineCipher.encrypt(plainText);
			else 
			    cipherText = affineCipher.decrypt(plainText);
		    
			// puts result in the output label
			output.setText(cipherText);
		    } catch (Exception ex) {
			// create popup
			messagePopUp("Incorrect input for Affine Cipher. Refer to README for correct input format.", "Affine Cipher Input Error");
		    }
		}
	    });
	vigenere = new JButton("Vigenere Cipher");
	vigenere.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // get text from plaintext text field
		    plainText = input.getText();

		    // gets key from key text field and sets as the key in cipher object
		    try {
			key = keyInput.getText();
			vigenereCipher.setCipherKey(key);
			
			// checks if encrypting or decrypting
			if (encryptMode)
			    cipherText = vigenereCipher.encrypt(plainText);
			else
			    cipherText = vigenereCipher.decrypt(plainText);
			
			// puts result in the output label
			output.setText(cipherText);
		    } catch (Exception ex) {
			// create popup
			messagePopUp("Incorrect input for Vigenere Cipher. Refer to README for correct input format.", "Vigenere Cipher Input Error");
		    }
		}
	    });

	// adds cipher buttons to overall cipher button panel
	cipherButtonPanel.add(shift);
	cipherButtonPanel.add(affine);
	cipherButtonPanel.add(vigenere);

	// creates panel for mode button
	modeButtonPanel = new JPanel();

	// create mode button and adds listener
	mode = new JButton("Switch to decryption");
	mode.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    // checks if encryption or decryption mode
		    if (encryptMode) {
			encryptMode = false;
			mode.setText("Switch to encryption");
		    } else {
			encryptMode = true;
			mode.setText("Switch to decryption");
		    }
		}
	    });

	// adds mode button to mode panel
	modeButtonPanel.add(mode);

	// creates overall button panel and adds cipher/mode button panels
	buttonPanel = new JPanel();
	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	buttonPanel.add(cipherButtonPanel);
	buttonPanel.add(modeButtonPanel);

	// creates output label
	output = new JLabel();

	// adds components to overall frame
	frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
	frame.getContentPane().add(BorderLayout.NORTH, textFieldPanel);
	frame.getContentPane().add(BorderLayout.SOUTH, output);

	// sets frame visible
	frame.setVisible(true);
    }

    public void messagePopUp(String message, String title) {
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
