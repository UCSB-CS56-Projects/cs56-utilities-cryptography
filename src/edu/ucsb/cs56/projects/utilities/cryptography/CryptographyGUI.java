package edu.ucsb.cs56.projects.utilities.cryptography;

import com.jtattoo.plaf.*;
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
import javax.swing.UIManager;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.filechooser.*;


/**
   A class to implement the Cryptography GUI.
   @author Callum Steele
   @author Miranda Aperghis
   @author Ryan Peffers
   @author Zhiliang Xie (Terry)
   @author Zhancheng Qian
   @version Project CS56, M16, 07/26/2016
 */

public class CryptographyGUI {
    
    ShiftCipher shiftCipher = null;
    AffineCipher affineCipher = null;
    VigenereCipher vigenereCipher = null;
    BifidCipher bifidCipher = null;

    String plainText = null;
    String cipherText = null;
    String newLine = System.getProperty("line.separator");
    int shiftKey, keyA, keyB, affineKeyA, affineKeyB, last, rsaKeyA, rsaKeyB;
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
    JButton shift, vigenere, affine, bifid, current, mode, info, copy, clean, allNewWindow, rsaWindow, save, readFile;
    JButton shiftRandom, affineRandom, vigenereRandom, bifidRandom, allRandom, rsaRandom;
    JPanel cipherButtonPanel, modeButtonInfoPanel, textFieldPanel, inputTextPanel;
    JPanel outputTextPanel, inputKeyTextPanel,inputOutputPanel, cipherBoxPanel01, cipherBoxPanel02, savePanel;
    JPanel cipherBoxPanel03, cipherBoxPanel04,cipherBoxPanel05, EnandDnPanel;
    JPanel keygenAreaPanel, labelAreaPanel, keyAreaPanel, exeAreaPanel;
    JTextField keyInput;
    JTextArea inputArea,outputArea,ShKeyInput,AfKeyInput,ViKeyInput,BiKeyInput,AllKeyInput, RsaKeyInput;
    JLabel input,output,inputText, inputKeyText, outputText, ShLabel, AfLabel, ViLabel, BiLabel,AllLabel,RsaLabel;
    JLabel address,fileName;
    JTextArea addressText,fileNameText;
    File inputFile;

    //JTextArea:
    /*
      inputArea is the leftmost input box which sits in WEST (boxLayout)
      outputArea is the rightmost output box which sits in EAST (boxLayout)
      ShKeyInput,AfKeyInput,ViKeyInput,BiKeyInput,AllKeyInput are input boxes for keys
      addressText is the box under intput boxes, hold the address of outout file to be saved
      fileName text is the box under addressText, hold the name of output file
    */
    //JPanel:
    //inputTextPanel holds inputArea
    //outputTextPanel holds outputArea
    
    //inputKeyTextPanel holds four sub JPanels which are labelAreaPanel, keyAreaPanel, keygenAreaPanel, exeAreaPanel
    //labelAreaPanel holds ShLabel, AfLabel, ViLabel, BiLabel,AllLabel
    //keyAreaPanel holds ShKeyInput,AfKeyInput,ViKeyInput,BiKeyInput,AllKeyInput
    //keygenAreaPanel holds all keygen buttons
    //exeAreaPanel holds all execution buttons

    boolean encryptMode = true;
    
    /** Calls the function to create the GUI.
	@param args Default arguments sent to main.
    */
    public static void main (String[] args) {	
	    try {
		    // select Look and Feel
		    UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		}
		catch (Exception ex) {
		    ex.printStackTrace();
		}
	CryptographyGUI cryptoGUI = new CryptographyGUI();
	cryptoGUI.go();
		
	//checks to see if a welcomeWindow flag file exists. If the file exists, then the user had previously
	//opted out of having a welcome window appear via a checkBox.
	File f = new File("welcomeWin.txt");
	if(!f.exists()) { 
	    WelcomeWindow welcWin = new WelcomeWindow();
	    welcWin.go();
	}
    }

    /** Function that populates and creates the GUI.
     */
    public void go () {

	JRadioButton modeButton01 = new JRadioButton("ENCRYPT");
	modeButton01.setActionCommand("ENCRYPT");
	modeButton01.setSelected(true);
	
	JRadioButton modeButton02 = new JRadioButton("DECRYPT");
	modeButton02.setActionCommand("DECRYPT");
	
	ButtonGroup group = new ButtonGroup();
	group.add(modeButton01);
	group.add(modeButton02);

	//lambda function for action listener of button1
	modeButton01.addActionListener(Button1->new GUIActionMethod(this).SwitchToEncrypt());

	//lambda function for action listener of button2
	modeButton02.addActionListener(Button2->new GUIActionMethod(this).SwitchToDecrypt());

	//lambda function for copy
	copy = new JButton ("Copy");
	copy.addActionListener(Copy->new GUIActionMethod(this).Copy());
	
	//lamda function for clean
	clean = new JButton ("Clean");
	clean.addActionListener(Clean->new GUIActionMethod(this).Clean_01());

	//initialise Cipher objects
	shiftCipher = new ShiftCipher();
	affineCipher = new AffineCipher();
	vigenereCipher = new VigenereCipher();
	bifidCipher = new BifidCipher();

	//setup overall frame options
	frame = new JFrame();
	frame.setSize(1280,680);
	frame.setTitle("Cryptography Interface");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);

	//create panel containing label and textArea for plaintext INPUT
	inputTextPanel = new JPanel();
	inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.Y_AXIS));
	inputText = new JLabel();
	inputText.setText("Plain Text: ");
	inputArea = new JTextArea("",33,20);
	inputArea.setLineWrap(true);
	inputArea.setWrapStyleWord(true);
	inputTextPanel.add(inputText);
	inputTextPanel.add(new JScrollPane(inputArea));

	//add address and file name label and  text area
	address=new JLabel("Save Address:(e.g. /Documents)");
	fileName=new JLabel("Save Name:");
	addressText=new JTextArea("",3,20);
	addressText.setLineWrap(true);
	addressText.setWrapStyleWord(true);
	fileNameText=new JTextArea("output.txt",1,20);
	fileNameText.setLineWrap(true);
	fileNameText.setWrapStyleWord(true);

	//adding the address and fileName widget to the panel
	inputTextPanel.add(address);   
	inputTextPanel.add(new JScrollPane(addressText));
	inputTextPanel.add(fileName);
	inputTextPanel.add(new JScrollPane(fileNameText));

	// create panel containing label and textArea for plaintext OUTPUT
	outputTextPanel = new JPanel();
	outputTextPanel.setLayout(new BoxLayout(outputTextPanel, BoxLayout.Y_AXIS));
	outputText = new JLabel();
	outputText.setText("Ciphered Text: ");
	outputArea = new JTextArea("",25,20);
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
	GridBagConstraints a = new GridBagConstraints();

	//create the keyAreaPanel and config
	keyAreaPanel = new JPanel();
	keyAreaPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	//create the keygenAreaPanel and config
	keygenAreaPanel = new JPanel();
	keygenAreaPanel.setLayout(new GridBagLayout());
	GridBagConstraints e = new GridBagConstraints();

	//create the exeAreaPanel and config 
	exeAreaPanel = new JPanel();
	exeAreaPanel.setLayout(new GridBagLayout());
	GridBagConstraints b = new GridBagConstraints();

	//create the savePanel and config
	savePanel = new JPanel();
	savePanel.setLayout(new GridBagLayout());
	GridBagConstraints s = new GridBagConstraints();

	
	//put label, key input of Shift Cipher in the right place
	ShLabel = new JLabel();
	ShLabel.setText("Shift Cipher key: ");
	ShKeyInput = new JTextArea(2,15);
	a.fill = GridBagConstraints.HORIZONTAL;
	a.weightx = 0;
	a.weighty = 0.6;
	a.gridx = 0;
	a.gridy = 0;
	labelAreaPanel.add(ShLabel, a);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0;
	c.weighty = 2;
	c.gridx = 0;
	c.gridy = 0;
	keyAreaPanel.add(new JScrollPane(ShKeyInput), c);

	//put label, key input of Affine Cipher in the right place
	AfLabel = new JLabel();
	AfLabel.setText("Affine Cipher key: ");
	AfKeyInput = new JTextArea(2,15);
	a.fill = GridBagConstraints.HORIZONTAL;
	a.weightx = 0;
	a.weighty = 0.5;
	a.gridx = 0;
	a.gridy = 1;
	labelAreaPanel.add(AfLabel, a);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0;
	c.weighty = 2;
	c.gridx = 0;
	c.gridy = 1;
	keyAreaPanel.add(new JScrollPane(AfKeyInput), c);
	
	//put label, key input of Vigenere Cipher in the right place
	ViLabel = new JLabel();
	ViLabel.setText("Vigenere Cipher key: ");
	ViKeyInput = new JTextArea("",10,20);
	ViKeyInput.setLineWrap(true);
	ViKeyInput.setWrapStyleWord(true);
	a.fill = GridBagConstraints.HORIZONTAL;
	a.weightx = 0;
	a.weighty = 2;
	a.gridx = 0;
	a.gridy = 2;
	labelAreaPanel.add(ViLabel, a);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0;
	c.weighty = 2;
	c.gridx = 0;
	c.gridy = 2;
	keyAreaPanel.add(new JScrollPane(ViKeyInput), c);

	//put label, key input of Bifid Cipher in the right place
	BiLabel = new JLabel();
	BiLabel.setText("Bifid Cipher key: ");
	BiKeyInput = new JTextArea("",10,20);
	BiKeyInput.setLineWrap(true);
	BiKeyInput.setWrapStyleWord(true);
	a.fill = GridBagConstraints.HORIZONTAL;
	a.weightx = 0;
	a.weighty = 2;
	a.gridx = 0;
	a.gridy = 3;
	labelAreaPanel.add(BiLabel,a);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0;
	c.weighty = 2;
	c.gridx = 0;
	c.gridy = 3;
	keyAreaPanel.add(new JScrollPane(BiKeyInput), c);
	
	//Create Save Button and add listener
	save = new JButton("Choose Folder");
	save.addActionListener((ExSave) -> new GUIActionMethod(this).SaveLocationGUI());

	//create read input file button and add listener
	readFile = new JButton("Choose Input File");
	readFile.addActionListener((ReadFile) -> new GUIActionMethod(this).ReadFile());

	//create All Cipher button and add listener
	allNewWindow = new JButton("All Cipher");
	allNewWindow.addActionListener((AllCipherGUI) -> new GUIActionMethod(this).MakeAllCipherGUI());

	//create RSA button and add listener
	rsaWindow = new JButton("RSA Window");
	rsaWindow.addActionListener((RSAWindow) -> new GUIActionMethod(this).MakeRSACipherGUI());

	//create cipher buttons and add listeners
	shift = new JButton("Execute SHIFT");
	shift.addActionListener((ExShift) -> new GUIActionMethod(this).ExecuteShift());

	affine = new JButton("Execute AFFINE");
	affine.addActionListener((ExAffine) -> new GUIActionMethod(this).ExecuteAffine());

	vigenere = new JButton("Execute VIGENERE");
	vigenere.addActionListener((ExVi)->new GUIActionMethod(this).ExecuteVigenere());

	bifid = new JButton("Execute BIFID");
	bifid.addActionListener((ExBi)->new GUIActionMethod(this).ExecuteBifid());

	//create random key generator panel, add buttons
	keygenAreaPanel=new JPanel();
	keygenAreaPanel.setLayout(new GridBagLayout());

	shiftRandom=new JButton("keyGen");
	affineRandom=new JButton("keyGen");
	vigenereRandom=new JButton("keyGen");
	bifidRandom=new JButton("keyGen");
	allRandom = new JButton("keyGen");
	rsaRandom = new JButton("keyGen");
	
	e.weightx = 0;
	e.weighty = 0.6;
	e.gridx = 0;
	e.gridy = 0;
	keygenAreaPanel.add(shiftRandom,e);

	e.fill = GridBagConstraints.HORIZONTAL;
	e.weightx = 0;
	e.weighty = 0.5;
	e.gridx = 0;
	e.gridy = 1;
	keygenAreaPanel.add(affineRandom,e);

	e.fill = GridBagConstraints.HORIZONTAL;
	e.weightx = 0;
	e.weighty = 2;
	e.gridx = 0;
	e.gridy = 2;
	keygenAreaPanel.add(vigenereRandom,e);

	e.fill = GridBagConstraints.HORIZONTAL;
	e.weightx = 0;
	e.weighty = 2.5;
	e.gridx = 0;
	e.gridy = 3;
	keygenAreaPanel.add(bifidRandom,e);

	e.fill = GridBagConstraints.HORIZONTAL;
	e.weightx = 0;
	e.weighty = 1;
	e.gridx = 0;
	e.gridy = 4;
	
	s.fill = GridBagConstraints.HORIZONTAL;
	s.weightx = 0;
	s.weighty = 0.6;
	s.gridx = 0;
	s.gridy = 0;
	savePanel.add(save, s);

	s.fill = GridBagConstraints.HORIZONTAL;
	s.weightx = 0;
	s.weighty = 0.5;
	s.gridx = 0;
	s.gridy = 1;
	savePanel.add(readFile,s);

	//make cipher key gen buttons		      
	affineRandom.addActionListener((AfRam)->new GUIActionMethod(this).AffineGenKey());

	bifidRandom.addActionListener((BiRam) -> new GUIActionMethod(this).BifidGenKey());
	
	shiftRandom.addActionListener((ShRam) ->new GUIActionMethod(this).ShiftGenKey());

	vigenereRandom.addActionListener((ViRam) -> new GUIActionMethod(this).VigenereGenKey());

	allRandom.addActionListener((AlRam) ->new GUIActionMethod(this).AllGenKey());

	rsaRandom.addActionListener((RsaRam) -> new GUIActionMethod(this).RSAGenKey());

	//cipherBoxPanel05 actually holds buttons like current, switch, info
	cipherBoxPanel05 = new JPanel();
	cipherBoxPanel05.setLayout(new BoxLayout(cipherBoxPanel05, BoxLayout.X_AXIS));
	
	current = new JButton("Current");
	//lambda function for current
	current.addActionListener((ExCurrent) -> new GUIActionMethod(this).ExecuteCurrent());

	//put all buttons in exeAreaPanel
	b.fill = GridBagConstraints.HORIZONTAL;
	b.weightx = 0;
	b.weighty = 0.6;
	b.gridx = 0;
	b.gridy = 0;
	exeAreaPanel.add(shift,b);

	b.fill = GridBagConstraints.HORIZONTAL;
	b.weightx = 0;
	b.weighty = 0.5;
	b.gridx = 0;
	b.gridy = 1;
	exeAreaPanel.add(affine,b);

	b.fill = GridBagConstraints.HORIZONTAL;
	b.weightx = 0;
	b.weighty = 2;
	b.gridx = 0;
	b.gridy = 2;
	exeAreaPanel.add(vigenere,b);

	b.fill = GridBagConstraints.HORIZONTAL;
	b.weightx = 0;
	b.weighty = 2.5;
	b.gridx = 0;
	b.gridy = 3;
	exeAreaPanel.add(bifid,b);
	cipherBoxPanel05.add(current);

	// create info button and adds listener
	//make lambda expression or Info button
	info = new JButton("Info");
	info.addActionListener((Info)->new GUIActionMethod(this).SendInfo());
	
	// adds function buttons to cipherBoxPanel05
	EnandDnPanel = new JPanel();
	EnandDnPanel.setLayout(new BoxLayout(EnandDnPanel, BoxLayout.X_AXIS));
	EnandDnPanel.add(modeButton01);
	EnandDnPanel.add(modeButton02);
	EnandDnPanel.add(allNewWindow);

	cipherBoxPanel05.add(info);
	cipherBoxPanel05.add(copy);
	cipherBoxPanel05.add(clean);
	cipherBoxPanel05.add(rsaWindow);

	//add save button to input text panel
	inputTextPanel.add(savePanel);

	GridBagConstraints m = new GridBagConstraints();
	m.fill = GridBagConstraints.HORIZONTAL;
	m.weightx = 0;
	m.weighty = 0;
	m.gridx = 0;
	m.gridy = 6;

	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0;
	c.weighty = 0;
	c.gridx = 0;
	c.gridy = 5;
		
	// adds cipherBoxPanel05 panel to the end of keyAre Panel
	keyAreaPanel.add(EnandDnPanel,c);
	keyAreaPanel.add(cipherBoxPanel05,m);
	
	// adds all Panels to inputKeyTextPanel
	inputKeyTextPanel.add(labelAreaPanel);
	inputKeyTextPanel.add(keyAreaPanel);
	inputKeyTextPanel.add(keygenAreaPanel);
	inputKeyTextPanel.add(exeAreaPanel);
	
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


