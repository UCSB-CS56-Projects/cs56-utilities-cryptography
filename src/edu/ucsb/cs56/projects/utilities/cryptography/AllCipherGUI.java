package edu.ucsb.cs56.projects.utilities.cryptography;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import javax.swing.JTextArea;

/**
   A class to implement the all cipher GUI.
	@author Zhiliang Xie (Terry)
	@author Zhancheng Qian
   @version Project CS56, M16, 07/26/2016
 */

public class AllCipherGUI
{	
	ShiftCipher shiftCipher = null;
	AffineCipher affineCipher = null;
	VigenereCipher vigenereCipher = null;
	BifidCipher bifidCipher = null;

	String plainText = null;
	String cipherText = null;
	String newLine = System.getProperty("line.separator");
	int shiftKey, keyA, keyB, affineKeyA, affineKeyB, last;
	String key;
	String[] inputs;
	String[] decryptInputs;
	String[] shiftInputs;
	String[] affineInputs;
	String[] vigenereInputs;
	String[] bifidInputs;

	ArrayList<String> storedKey=new ArrayList<String>();
	ArrayList<String> storedOutput=new ArrayList<String>();
	ArrayList<String> storedMethod=new ArrayList<String>();

	JFrame frame;
	JButton  all, current, mode, info, copy, clean, allRandom;
	JPanel buttonPanel, cipherButtonPanel, modeButtonInfoPanel, textFieldPanel, inputTextPanel;
	JPanel outputTextPanel, inputKeyTextPanel,inputOutputPanel, cipherBoxPanel01, cipherBoxPanel02;
	JPanel cipherBoxPanel03, cipherBoxPanel04,cipherBoxPanel05, EnandDnPanel;
	JPanel keygenAreaPanel, labelAreaPanel, keyAreaPanel, exeAreaPanel, keyAreaPanel_New, exeAreaPanel_New;
	JTextField keyInput;
	JTextArea inputArea,outputArea,AllKeyInput;
	JLabel input,output,inputText, inputKeyText, outputText, AllLabel;

	CryptographyGUI GUI;
	//JTextArea:
	//inputArea is the leftmost input box which sits in WEST (boxLayout)
	//outputArea is the rightmost output box which sits in EAST (boxLayout)
	//ShKeyInput,AfKeyInput,ViKeyInput,BiKeyInput,AllKeyInput are input boxes for keys

	//JPanel:
	//inputTextPanel holds inputArea
	//outputTextPanel holds outputArea

	//inputKeyTextPanel holds four sub JPanels which are labelAreaPanel, keyAreaPanel, keygenAreaPanel, exeAreaPanel
	//labelAreaPanel holds ShLabel, AfLabel, ViLabel, BiLabel,AllLabel
	//keyAreaPanel holds ShKeyInput,AfKeyInput,ViKeyInput,BiKeyInput,AllKeyInput
	//keygenAreaPanel holds all keygen buttons
	//exeAreaPanel holds all execution buttons

	public AllCipherGUI(){}
	public AllCipherGUI(CryptographyGUI GUI){
		this.GUI=GUI;
	}

	boolean encryptMode = true;

	/**    
	 *function to read the file
	 */
	public void readFile(){
		// Open the file
		try{
			FileInputStream fstream = new FileInputStream("output.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				String[] buffer=strLine.split(",");
				storedMethod.add(buffer[0]);
				storedKey.add(buffer[1]);
				storedOutput.add(buffer[2]);
			}
			//Close the input stream
			br.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/** Function that populates and creates the GUI.
	 */
	public void createFrame () {

		copy = new JButton ("Copy");
		copy.addActionListener(Copy->new GUIActionMethod(GUI,this).Copy());

		clean = new JButton ("Clean");
		clean.addActionListener(Clean->new GUIActionMethod(GUI,this).Clean_02());

		// initialise Cipher objects
		shiftCipher = new ShiftCipher();
		affineCipher = new AffineCipher();
		vigenereCipher = new VigenereCipher();
		bifidCipher = new BifidCipher();

		// setup overall frame options
		frame = new JFrame();
		frame.setSize(1020,420);
		frame.setTitle("All Cipher Interface");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// create panel containing label and textArea for plaintext INPUT
		inputTextPanel = new JPanel();
		inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.Y_AXIS));
		inputText = new JLabel();
		inputText.setText("Plain Text: ");
		inputArea = new JTextArea("",15,20);
		inputArea.setLineWrap(true);
		inputArea.setWrapStyleWord(true);

		inputTextPanel.add(inputText);
		inputTextPanel.add(new JScrollPane(inputArea));

		// create panel containing label and textArea for plaintext OUTPUT
		outputTextPanel = new JPanel();
		outputTextPanel.setLayout(new BoxLayout(outputTextPanel, BoxLayout.Y_AXIS));
		outputText = new JLabel();
		outputText.setText("Ciphered Text: ");
		outputArea = new JTextArea("",15,20);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);

		outputTextPanel.add(outputText);
		outputTextPanel.add(new JScrollPane(outputArea));

		// create panel holds labelAreaPanel, keyAreaPanel, keygenAreaPanel, exeAreaPanel
		inputKeyTextPanel = new JPanel();
		inputKeyTextPanel.setLayout(new BoxLayout(inputKeyTextPanel, BoxLayout.X_AXIS));
		inputKeyText = new JLabel();

		//create the labelAreaPanel and config
		labelAreaPanel = new JPanel();
		labelAreaPanel.setLayout(new GridBagLayout());

		//create the keyAreaPanel and config
		keyAreaPanel = new JPanel();
		keyAreaPanel.setLayout(new GridBagLayout());

		//create the keyAreaPanel_New and config
		keyAreaPanel_New = new JPanel();
		keyAreaPanel_New.setLayout(new BoxLayout(keyAreaPanel_New, BoxLayout.Y_AXIS));

		//create the keygenAreaPanel and config
		keygenAreaPanel = new JPanel();
		keygenAreaPanel.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();

		//create the exeAreaPanel and config 
		exeAreaPanel = new JPanel();
		exeAreaPanel.setLayout(new GridBagLayout());

		//create the exeAreaPanel and config 
		exeAreaPanel_New = new JPanel();
		exeAreaPanel_New.setLayout(new BoxLayout(exeAreaPanel_New, BoxLayout.Y_AXIS));

		//create random key generator panel, add buttons
		keygenAreaPanel=new JPanel();
		keygenAreaPanel.setLayout(new GridBagLayout());
		allRandom = new JButton("keyGen");
		keygenAreaPanel.add(allRandom,e);

		//generate all cipher random key generator
		//if in Decryption Mode throw exception
		//lambda fucntion for keyGen of allciphers 
		allRandom.addActionListener(AllRandom->new GUIActionMethod(GUI,this).AllGenKey());

		//cipherBoxPanel05 actually holds buttons like current, switch, info
		cipherBoxPanel05 = new JPanel();
		cipherBoxPanel05.setLayout(new BoxLayout(cipherBoxPanel05, BoxLayout.X_AXIS));
		//put label and key input of All Cipher in the right place
		AllLabel = new JLabel();
		AllLabel.setText("All Cipher keys: ");
		AllKeyInput = new JTextArea("",7,25);
		AllKeyInput.setLineWrap(true);
		AllKeyInput.setWrapStyleWord(true);
		keyAreaPanel_New.add(AllLabel);
		keyAreaPanel_New.add(new JScrollPane(AllKeyInput));
		//lambda function for all Ciphers
		all = new JButton("Execute");
		all.addActionListener((ExAll) -> new GUIActionMethod(GUI,this).ExecuteAll());

		exeAreaPanel_New.add(allRandom);
		exeAreaPanel_New.add(all);

		// create info button and adds listener
		info = new JButton("Info");
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// checks if encryption or decryption mode
				messagePopUp("Format of Input for GUI:\n\nGeneral:\n    Spaces " +
						"can be used for plaintext input fields, but only certain keys " +
						"utilize spaces. \nCapital letters for plaintext can be used " +
						"but will be translated to lowercase \nprior " +
						"to any cryptographic operation being performed on them.\n\n All Ciphers:\n    Keys should follow the format " +
						"of the respective cyphers. \nAll four keys must be used and there " +
						"cannot be any spaces \nwithin the key string except " +
						"for the affine cypher key.\n\n" + "Copy:\nCopies the output text into clipboard, use ctrl + v to paste.\n\n" 
						+"Clean:\nHit clean to clean all the text fields.\n\n"
						,"Info message");
			}
		});

		exeAreaPanel_New.add(info);
		exeAreaPanel_New.add(copy);
		exeAreaPanel_New.add(clean);

		inputKeyTextPanel.add(keyAreaPanel_New);
		inputKeyTextPanel.add(exeAreaPanel_New);

		// adds components to overall frame
		frame.getContentPane().add(BorderLayout.WEST, inputTextPanel);
		frame.getContentPane().add(BorderLayout.EAST, outputTextPanel);
		frame.getContentPane().add(BorderLayout.CENTER, inputKeyTextPanel);

		// sets frame visible
		frame.setLocationRelativeTo(null); //center the spawn location
		frame.setVisible(true);
	}

	public void messagePopUp(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
}