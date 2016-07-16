
package edu.ucsb.cs56.projects.utilities.cryptography;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JTextArea;

/**
   A class to implement the Cryptography GUI.
   @author Callum Steele
   @author Miranda Aperghis
   @author Ryan Peffers
   @version Project CS56, W15, 3/10/2015
*/

public class CryptographyGUI
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
	//Terry2
    ArrayList<String> storedKey=new ArrayList<String>();
    ArrayList<String> storedOutput=new ArrayList<String>();
    ArrayList<String> storedMethod=new ArrayList<String>();
    JFrame frame;
    JButton shift, vigenere, affine, bifid, all, current, mode, info, shiftRandom, vigenereRandom, affineRandom, bifidRandom;
    JPanel buttonPanel, cipherButtonPanel, modeButtonInfoPanel, textFieldPanel, inputTextPanel;
    JPanel outputTextPanel, inputKeyTextPanel,inputOutputPanel, cipherBoxPanel01, cipherBoxPanel02;
    JPanel cipherBoxPanel03, cipherBoxPanel04,cipherBoxPanel05, labelAreaPanel, keyAreaPanel, exeAreaPanel, keyGeneratorPanel;
    JTextField keyInput;
    JTextArea inputArea,outputArea,ShKeyInput,AfKeyInput,ViKeyInput,BiKeyInput,AllKeyInput;
    JLabel input,output,inputText, inputKeyText, outputText, ShLabel, AfLabel, ViLabel, BiLabel,AllLabel;
     
    boolean encryptMode = true;

    /** Calls the function to create the GUI.
	@param args Default arguments sent to main.
     */
    public static void main (String[] args) {
	    CryptographyGUI cryptoGUI = new CryptographyGUI();

	    cryptoGUI.go();
    }
    
    
    
/**    
*function to read the file
*/
	//Terry2
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
    public void go () {
        
        // initialise Cipher objects
        shiftCipher = new ShiftCipher();
        affineCipher = new AffineCipher();
        vigenereCipher = new VigenereCipher();
        bifidCipher = new BifidCipher();

        // setup overall frame options
        frame = new JFrame();
        frame.setSize(1220,680);
        frame.setTitle("Cryptography Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // create panel containing label and textArea for plaintext INPUT
        inputTextPanel = new JPanel();
        inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.Y_AXIS));
        inputText = new JLabel();
        inputText.setText("Plaintext: ");
        inputArea = new JTextArea("",25,20);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        inputTextPanel.add(inputText);
        inputTextPanel.add(new JScrollPane(inputArea));
        
        
        //Terry create random key generator panel, add buttons
        keyGeneratorPanel=new JPanel();
        keyGeneratorPanel.setLayout(new BoxLayout(keyGeneratorPanel, BoxLayout.Y_AXIS));
        shiftRandom=new JButton("Shift keyGen&encrypt");
        affineRandom=new JButton("Affine keyGen7encrypt");
        vigenereRandom=new JButton("Vig keyGen&encrypt");
        bifidRandom=new JButton("Biffid keyGen&encrypt");
        keyGeneratorPanel.add(shiftRandom);
        keyGeneratorPanel.add(affineRandom);
        keyGeneratorPanel.add(vigenereRandom);
        keyGeneratorPanel.add(bifidRandom);
        
        
     // create panel containing label and textArea for plaintext OUTPUT
        outputTextPanel = new JPanel();
        outputTextPanel.setLayout(new BoxLayout(outputTextPanel, BoxLayout.Y_AXIS));
        outputText = new JLabel();
        outputText.setText("Output: ");
        outputArea = new JTextArea("",25,20);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        outputTextPanel.add(outputText);
        outputTextPanel.add(new JScrollPane(outputArea));

        // create panel containing label and textfield for key input
        inputKeyTextPanel = new JPanel();
        inputKeyTextPanel.setLayout(new BoxLayout(inputKeyTextPanel, BoxLayout.X_AXIS));
        inputKeyText = new JLabel();
        inputKeyText.setText("Key(s): ");
        inputKeyTextPanel.add(inputKeyText);
        
        //need to be divided into three vertical columns as below
        labelAreaPanel = new JPanel();
        labelAreaPanel.setLayout(new BoxLayout(labelAreaPanel, BoxLayout.Y_AXIS));
        keyAreaPanel = new JPanel();
        keyAreaPanel.setLayout(new BoxLayout(keyAreaPanel, BoxLayout.Y_AXIS));
        exeAreaPanel = new JPanel();
        exeAreaPanel.setLayout(new BoxLayout(exeAreaPanel, BoxLayout.Y_AXIS));

        // create panel for cipher buttons boxes
        cipherBoxPanel01 = new JPanel();//Shift Cipher
        cipherBoxPanel01.setLayout(new BoxLayout(cipherBoxPanel01, BoxLayout.X_AXIS));
        ShLabel = new JLabel();
        ShLabel.setText("Shift Cipher key: ");
        ShKeyInput = new JTextArea(1,10);
        ShKeyInput.setLineWrap(true);
        ShKeyInput.setWrapStyleWord(true);
        //ShKeyInput.setRows(1);
        labelAreaPanel.add(ShLabel);
        keyAreaPanel.add(new JScrollPane(ShKeyInput));
        
        cipherBoxPanel02 = new JPanel();//Affine Cipher
        cipherBoxPanel02.setLayout(new BoxLayout(cipherBoxPanel02, BoxLayout.X_AXIS));
        AfLabel = new JLabel();
        AfLabel.setText("Affine Cipher key: ");
        AfKeyInput = new JTextArea(1,10);
        AfKeyInput.setLineWrap(true);
        AfKeyInput.setWrapStyleWord(true);
        //AfKeyInput.setRows(1);
        labelAreaPanel.add(AfLabel);
        keyAreaPanel.add(new JScrollPane(AfKeyInput));
        
        cipherBoxPanel03 = new JPanel();//Vigenere Cipher
        cipherBoxPanel03.setLayout(new BoxLayout(cipherBoxPanel03, BoxLayout.X_AXIS));
        ViLabel = new JLabel();
        ViLabel.setText("Vigenere Cipher key: ");
        ViKeyInput = new JTextArea("",5,10);
        ViKeyInput.setLineWrap(true);
        ViKeyInput.setWrapStyleWord(true);
        labelAreaPanel.add(ViLabel);
        keyAreaPanel.add(new JScrollPane(ViKeyInput));
        
        cipherBoxPanel04 = new JPanel();//Bifid Cipher
        cipherBoxPanel04.setLayout(new BoxLayout(cipherBoxPanel04, BoxLayout.X_AXIS));
        BiLabel = new JLabel();
        BiLabel.setText("Bifid Cipher key: ");
        BiKeyInput = new JTextArea("",5,10);
        BiKeyInput.setLineWrap(true);
        BiKeyInput.setWrapStyleWord(true);
        labelAreaPanel.add(BiLabel);
        keyAreaPanel.add(new JScrollPane(BiKeyInput));

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // create cipher buttons and add listeners
        
        shift = new JButton("Execute SHIFT");
        shift.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ShKeyInput.getText().equals(null)||ShKeyInput.getText().length()==0){
			    	for (int j=0;j<storedKey.size();++j){
			    		if(storedMethod.get(j).equals("shift")&&storedOutput.get(j).equals(inputArea.getText())){
			    			shiftKey=Integer.parseInt(storedKey.get(j));
			    			shiftCipher.setCipherKey(shiftKey);
			    			ShKeyInput.setText(Integer.toString(shiftKey));
			    			
			    		}
			    	}	
		    	}
            	
            	
                // get text from plaintext text field
                plainText = inputArea.getText();
		inputs = plainText.split("\\s+");

                // get key from key text field and set it as key in cipher object
                try {
                shiftKey = Integer.parseInt(ShKeyInput.getText());
                shiftCipher.setCipherKey(shiftKey);
		cipherText = "";
                // checks if encrypting or decrypting
		for(int i = 0; i < inputs.length; i++) {
		    if (encryptMode)
			cipherText += shiftCipher.encrypt(inputs[i]);
		    else{
		    	
		    	
			cipherText += shiftCipher.decrypt(inputs[i]);}
		    if(i < inputs.length - 1)
			cipherText += " ";
		
		}
		
		
//Terry
		try {
			PrintWriter out = new PrintWriter(new FileWriter("output.txt",true));
	            out.println("shift,"+ShKeyInput.getText()+","+cipherText);
		    out.close();
		}
		catch (Exception ex){
		    ex.printStackTrace();
		}
		// puts result in the output label
                outputArea.setText(cipherText);
		last = 0;

                } catch (Exception ex) {
                // create popup
                messagePopUp("Incorrect input for Shift Cipher.\nPlaintext is " +
                        "a string and can't have non-alphabetic " +
                        "characters.\nKey should contain a single integer within " +
                        "the range 0 to 25.\n", "Shift Cipher Input Error");
                }
            }
            });
            
            
        affine = new JButton("Execute AFFINE");
        affine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
		    	if((AfKeyInput.getText().equals(null)||AfKeyInput.getText().length()==0)&&!encryptMode){
		    	for (int j=0;j<storedKey.size();++j){
		    		if(storedMethod.get(j).equals("affine")&&storedOutput.get(j).equals(inputArea.getText())){
		    			keyA=Integer.parseInt(storedKey.get(j).split(" ")[0]);
		    			keyB=Integer.parseInt(storedKey.get(j).split(" ")[1]);
		    			affineCipher.setKeyA(keyA);
		    			affineCipher.setKeyB(keyB);
		    			AfKeyInput.setText(Integer.toString(keyA)+" "+keyB);
		    		}
		    	}	
		    
		    }
                // get text from plaintext text field
                plainText = inputArea.getText();
	        inputs = plainText.split("\\s+");
		                // gets keys from key text field and sets as the keys in cipher object
                try {
                key = AfKeyInput.getText();
                keyA = Integer.parseInt(key.substring(0, key.indexOf(' ')));
                keyB = Integer.parseInt(key.substring(key.indexOf(' ') + 1));
                affineCipher.setKeyA(keyA);
                affineCipher.setKeyB(keyB);
		cipherText = "";
		
                // checks if encrypting or decrypting
		for(int i = 0; i < inputs.length; i++) {		
		    if (encryptMode)
			cipherText += affineCipher.encrypt(inputs[i]);
		    else {

		    
			cipherText += affineCipher.decrypt(inputs[i]);
		    if(i < inputs.length - 1)
			cipherText += " ";
		}
		    }
         //Terry       
		try {
			PrintWriter out = new PrintWriter(new FileWriter("output.txt", true));
	
			out.println("affine,"+AfKeyInput.getText()+","+cipherText);

		    out.close();
		}
		catch (Exception ex){
		    ex.printStackTrace();
		}
		// puts result in the output label
                outputArea.setText(cipherText);
		last = 1;
		
                } catch (Exception ex) {
                // create popup
                messagePopUp("Incorrect input for Affine Cipher.\nPlaintext " +
                        "input is a String without non-alphabetic characters.\nKey takes 2 integers " +
                        "(a and b) separated by a single space the first \ninteger " +
                        "being within the range 0 to 25 and the second greater than 0.\n" +
                        "If Decrypting, the second integer must not be a coprime with 26",
			     "Affine Cipher Input Error");
                }
            }
            });
        
        
        //Terry2
        vigenere = new JButton("Execute VIGENERE");
        vigenere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get text from plaintext text field
                plainText = inputArea.getText();
	        inputs = plainText.split("\\s+");
		
                // gets key from key text field and sets as the key in cipher object
                try {
               
		
		
		
    if((ViKeyInput.getText().equals(null)||ViKeyInput.getText().length()==0)&&!encryptMode){
	    	for (int j=0;j<storedKey.size();++j){
	    		if(storedMethod.get(j).equals("vigenere")&&storedOutput.get(j).equals(inputArea.getText())){
	    			key=storedKey.get(j);
	    			vigenereCipher.setCipherKey(key);
	    			ViKeyInput.setText(key);
	    			
	    		}
    	
	    	}
}
    	 key = ViKeyInput.getText();
         vigenereCipher.setCipherKey(key);
	cipherText = "";
	    	
		
		// checks if encrypting or decrypting
		for(int i = 0; i < inputs.length; i++) {		
		    if (encryptMode)
			cipherText += vigenereCipher.encrypt(inputs[i]);
		    else{
}
		    	cipherText += vigenereCipher.decrypt(inputs[i]);
			    if(i < inputs.length - 1)
				cipherText += " ";
		    	}
		
		

		try {
			  PrintWriter out = new PrintWriter(new FileWriter("output.txt", true));
		
			  out.println("vigenere,"+ViKeyInput.getText()+","+cipherText);
		    out.close();

		}
		catch (Exception ex){
		    ex.printStackTrace();
		}
		
                // puts result in the output label
                outputArea.setText(cipherText);
		last = 2;
		
                } catch (Exception ex) {
                // create popup
                messagePopUp("Incorrect input for Vigenere Cipher.\nPlaintext " +
                        "is a String with only letters.\nKey is a String with " +
                        "only letters.\nNumbers will cause an exception " +
                        "to be thrown", "Vigenere Cipher Input Error");
                }
            }
        }
            );
        
        
        //Terry2
        bifid = new JButton("Execute BIFID");
        bifid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)           
            
            {
	if((BiKeyInput.getText().equals(null)||BiKeyInput.getText().length()==0)&&!encryptMode){
				    
			    	for (int j=0;j<storedKey.size();++j){
			    		
			    		
			    		if(storedMethod.get(j).equals("bifid")&&storedOutput.get(j).equals(inputArea.getText())){
			    			key=storedKey.get(j);
			    			bifidCipher.setCipherKey(key);
			    			BiKeyInput.setText(key);

			    		}
		    	
		    	
			
			    	}
			    	}
            	
            	
                // get text from plaintext text field
            
                plainText = inputArea.getText();
	        inputs = plainText.split("\\s+");
		
                // get key from key text field and set it as key in cipher object
                try {
                	
                key = BiKeyInput.getText();
		bifidCipher.setCipherKey(key);
		cipherText = "";
		
		
		

		// checks if encrypting or decrypting
		for(int i = 0; i < inputs.length; i++) {		
		    if (encryptMode){
			cipherText += bifidCipher.encrypt(inputs[i]);}
		    else{
		    
		    		BiKeyInput.setText(key);
			    	cipherText += bifidCipher.decrypt(inputs[i]);
				    if(i < inputs.length - 1)
					cipherText += " ";
		}
		    }
		//Terry
		try {

			PrintWriter out = new PrintWriter(new FileWriter("output.txt", true));
			 
	         
	            out.println("bifid,"+BiKeyInput.getText()+","+cipherText);

		    out.close();
		}
		catch (Exception ex){
		    ex.printStackTrace();
		}

		// Puts result in the output label
		outputArea.setText(cipherText);
		last = 3;
		
                } catch (Exception ex) {
                    // create popup
                    messagePopUp("Incorrect input for Bifid Cipher.\nPlaintext " +
                            "input can be any String so long as there are no numbers.\nKey " +
                            "input can be any String with at least one uppercase letter " +
			    "as well as no numbers.\nAll non-alphabetic characters will " +
			    "be deleted from the String.",
			    "Bifid Cipher Input Error");
                }
            }	
	    });
        
        cipherBoxPanel05 = new JPanel();
        cipherBoxPanel05.setLayout(new BoxLayout(cipherBoxPanel05, BoxLayout.X_AXIS));
        AllLabel = new JLabel();
        AllLabel.setText("All Cipher keys: ");
        AllKeyInput = new JTextArea("",3,30);
        AllKeyInput.setLineWrap(true);
        AllKeyInput.setWrapStyleWord(true);
        cipherBoxPanel05.add(AllLabel);
        cipherBoxPanel05.add(new JScrollPane(AllKeyInput));
        
        
   
        
        
        
        
        
        
        //Terry
        
        //make the affine key generator
        //if in Decryption Mode throw exception
        affineRandom.addActionListener(new ActionListener() {
        	public  void checkMode() throws Exception {
        		if(!encryptMode) {messagePopUp("In Decryption Mode now, swith to Encryption mode "
        				+ "to generate random keys.","Generate Keys Mode Error");
        		throw new Exception();}
        		}
        	
        	
            public void actionPerformed(ActionEvent e) {
                // get text from plaintext text field
                plainText = inputArea.getText();
		inputs = plainText.split("\\s+");

                
                
                affineCipher.generateKey();
                affineKeyA = affineCipher.getKeyA();
                affineKeyB = affineCipher.getKeyB();
                cipherText = "";
         
		
		try{
		//check if in decryption mode
			checkMode();
		
		
			
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
		
		// put the random key to the key bar
			AfKeyInput.setText(affineKeyA+" "+affineKeyB);
		
		

                
       }   
            
        }
            );
        
        //Terry
        //generate bifid random key generator
         //if in Decryption Mode throw exception
        bifidRandom.addActionListener(new ActionListener() {
        	public  void checkMode() throws Exception {
        		if(!encryptMode) {messagePopUp("In Decryption Mode now, swith to Encryption mode "
        				+ "to generate random keys.","Generate Keys Mode Error");
        		throw new Exception();}
        		}
        	
        	
           public void actionPerformed(ActionEvent e) {
                // get text from plaintext text field
                plainText = inputArea.getText();
		inputs = plainText.split("\\s+");
                
                
                bifidCipher.generateKey();
                key = bifidCipher.getCipherKey();
                cipherText = "";
         
		
		try{
		//check if in decryption mode
			checkMode();
		
			

			
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
		
		
		
		// put the random key to the key bar
				BiKeyInput.setText(key);
                
               
                
           }
        }
            );
        
        
        
        
        
        //Terry
        //make the random shift key generator
        //if in Decryption Mode throw exception
        shiftRandom.addActionListener(new ActionListener() {
        	public  void checkMode() throws Exception {
        		if(!encryptMode) {messagePopUp("In Decryption Mode now, swith to Encryption mode "
        				+ "to generate random keys.","Generate Keys Mode Error");
        		throw new Exception();}
        		}
        	
        	
           public void actionPerformed(ActionEvent e) {
                // get text from plaintext text field
                plainText = inputArea.getText();
		inputs = plainText.split("\\s+");
                
                
                shiftCipher.generateKey();
                shiftKey = shiftCipher.getCipherKey();
                cipherText = "";
         
		
		try{
		//check if in decryption mode
			checkMode();
			


			
		}catch(Exception ex){
			ex.printStackTrace();
			return;
			
		}
		
		
		

		// put the random key to the key bar
				ShKeyInput.setText(Integer.toString(shiftKey));
		
                
            }
        }
            );
   
        
        
 //Terry       
 //make the vigenere cipher key gnerator
//if in Decryption Mode throw exception
vigenereRandom.addActionListener(new ActionListener() {
	public  void checkMode() throws Exception {
		if(!encryptMode) {messagePopUp("In Decryption Mode now, swith to Encryption mode "
				+ "to generate random keys.","Generate Keys Mode Error");
		throw new Exception();}
		}
	
	
    public void actionPerformed(ActionEvent e) {
        // get text from plaintext text field
        plainText = inputArea.getText();
inputs = plainText.split("\\s+");

        
        
        	
        vigenereCipher.generateKey();
        key = vigenereCipher.getCipherKey();
        cipherText = "";
 

try{
//check if in decryption mode
	checkMode();

	
}catch(Exception ex){
	ex.printStackTrace();
	return;
}



//put the random key to the key bar
	ViKeyInput.setText(key);




    
    }
        
    }
    );

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

	all = new JButton("Execute All Ciphers");
	all.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		    
		// get text from plaintest test field
		plainText = inputArea.getText();
	        inputs = plainText.split("\\s+");
		// if in decryption, stores all texts for each string
		if(!(encryptMode)) {
		        decryptInputs = plainText.split(",");
			for(int x = 0; x < decryptInputs.length; x++)
			    System.out.println(decryptInputs[x]);
			
			shiftInputs = decryptInputs[0].split("\\s+");
			affineInputs = decryptInputs[1].split("\\s+");
			vigenereInputs = decryptInputs[2].split("\\s+");
			bifidInputs = decryptInputs[3].split("\\s+");			
	        }
		    
		try {
		    
		    // get keys for each cypher type
		    key = AllKeyInput.getText();
		    String[] keyInputs = key.split(",");
		    for(int x = 0; x < keyInputs.length; x++)
			System.out.println(keyInputs[x]);
		    int shiftKey = Integer.parseInt(keyInputs[0]);
		    shiftCipher.setCipherKey(shiftKey);
		    affineKeyA = Integer.parseInt(keyInputs[1].substring(0,
						  keyInputs[1].indexOf(' ')));
		    affineKeyB = Integer.parseInt(keyInputs[1].substring
						  (keyInputs[1].indexOf(' ') + 1));
		    affineCipher.setKeyA(affineKeyA);
		    affineCipher.setKeyB(affineKeyB);
		    vigenereCipher.setCipherKey(keyInputs[2]);
		    bifidCipher.setCipherKey(keyInputs[3]);
		    cipherText = "";
		    //Adds all encryption/decryption to output string
		    if(encryptMode) {
			cipherText = "Shift: ";
			for(int a = 0; a < inputs.length; a++) {
			    cipherText += shiftCipher.encrypt(inputs[a]);
			    cipherText += " ";
			}
			cipherText += newLine;
			cipherText += newLine;
			cipherText += "Affine: ";
			for(int b = 0; b < inputs.length; b++) {
			    cipherText += affineCipher.encrypt(inputs[b]);
			    cipherText += " ";
			}
			cipherText += newLine;
			cipherText += newLine;
			cipherText += "Vigenere: ";
			for(int c = 0; c < inputs.length; c++) {
			    cipherText += vigenereCipher.encrypt(inputs[c]);
			    cipherText += " ";
			}
			cipherText += newLine;
			cipherText += newLine;
			cipherText += "Bifid: ";
			for(int d = 0; d < inputs.length; d++) {
			    cipherText += bifidCipher.encrypt(inputs[d]);
			    cipherText += " ";
			}
		    }
		    else {
			cipherText = "Shift: ";
			for(int a = 0; a < shiftInputs.length; a++) {
			    cipherText += shiftCipher.decrypt(shiftInputs[a]);
			    cipherText += " ";
			}
			cipherText += newLine;
			cipherText += newLine;
			cipherText += "Affine: ";
			for(int b = 0; b < affineInputs.length; b++) {
			    cipherText += affineCipher.decrypt(affineInputs[b]);
			    cipherText += " ";
			}
			cipherText += newLine;
			cipherText += newLine;
			cipherText += "Vigenere: ";
			for(int c = 0; c < vigenereInputs.length; c++) {
			    cipherText += vigenereCipher.decrypt(vigenereInputs[c]);
			    cipherText += " ";
			}
			cipherText += newLine;
			cipherText += newLine;
			cipherText += "Bifid: ";
			for(int d = 0; d < shiftInputs.length; d++) {
			    cipherText += bifidCipher.decrypt(bifidInputs[d]);
			    cipherText += " ";
			}
		    }
		    //TERRY
		    try {
			PrintWriter out = new PrintWriter(new FileWriter("output.txt",true));
			out.println("output: "+cipherText+" keys: "+keyInput.getText());

			out.close();
		    }
		    catch (Exception ex){
			ex.printStackTrace();
		    }
		    System.out.println(cipherText);
                    // puts result in the output label
                    outputArea.setText(cipherText);
		    
                } catch (Exception ex) {
                    // create popup
                    messagePopUp("Wrong keys, please see info", "All Cipher Input Error");
                }
            }
        });		    

	current = new JButton("Current");
	current.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	        // checks if encryption or decryption mode and switches
                if (encryptMode) {
                encryptMode = false;
                } else {
                encryptMode = true;
                }
		outputArea.setText("");
		inputArea.setText("");
		ShKeyInput.setText("");
		AfKeyInput.setText("");
		ViKeyInput.setText("");
		BiKeyInput.setText("");
		String currentCipher = "";
		try {
		    inputs = cipherText.split("\\s+");
		    if(last == 0) {
			shiftCipher.setCipherKey(shiftKey);
		    }
		    if(last == 1) {
			affineCipher.setKeyA(keyA);
			affineCipher.setKeyB(keyB);
		    }
		    if(last == 2)
			vigenereCipher.setCipherKey(key);
		    if(last == 3)
		    bifidCipher.setCipherKey(key);
		    //Adds all encryption/decryption to output string

		    if(last == 0) {
			for(int i = 0; i < inputs.length; i++) {
			    if (encryptMode)
				currentCipher += shiftCipher.encrypt(inputs[i]);
			    else
				currentCipher += shiftCipher.decrypt(inputs[i]);
			    if(i < inputs.length - 1)
				currentCipher += " ";
			}
		    }
		    if(last == 1) {
		        for(int i = 0; i < inputs.length; i++) {		
			    if (encryptMode)
				currentCipher += affineCipher.encrypt(inputs[i]);
			    else
				currentCipher += affineCipher.decrypt(inputs[i]);
			    if(i < inputs.length - 1)
				currentCipher += " ";
			}
		    }
		    if(last == 2) {
			for(int i = 0; i < inputs.length; i++) {		
			    if (encryptMode)
				currentCipher += vigenereCipher.encrypt(inputs[i]);
			    else
				currentCipher += vigenereCipher.decrypt(inputs[i]);
			    if(i < inputs.length - 1)
				currentCipher += " ";
			}
		    }
		    if(last == 3) {
		        for(int i = 0; i < inputs.length; i++) {		
			    if (encryptMode)
				currentCipher += bifidCipher.encrypt(inputs[i]);
			    else
				currentCipher += bifidCipher.decrypt(inputs[i]);
			    if(i < inputs.length - 1)
				currentCipher += " ";
			}
		    }
		    try {
			PrintWriter out = new PrintWriter("output.txt");
		    
			for(int i = 0; i < inputs.length; i++) {
			    out.print(inputs[i]);
			    out.print(" ");
			}
			out.println();
			out.println(keyInput.getText());
			out.println(cipherText);
			out.close();
		    }
		    catch (Exception ex){
			ex.printStackTrace();
		    }
		    
                    // puts result in the output label
		    
                    outputArea.setText(currentCipher);
		    if (encryptMode) {
			encryptMode = false;
		    } else {
			encryptMode = true;
		    }
                } catch (Exception ex) {
                    // create popup
                    messagePopUp("Please do not change input fields to decrypt " +
				 " or encrypt current cipher text.", "Curent Cipher Error");
                }
	   }
	   });
	
		exeAreaPanel.add(shift);
		exeAreaPanel.add(affine);
		exeAreaPanel.add(vigenere);
		exeAreaPanel.add(bifid);
		inputKeyTextPanel.add(labelAreaPanel);
		inputKeyTextPanel.add(keyAreaPanel);
		//Terry
		inputKeyTextPanel.add(keyGeneratorPanel);
		inputKeyTextPanel.add(exeAreaPanel);
		
		
	

        // adds cipher buttons to overall cipher button panel
		cipherBoxPanel05.add(all);
		cipherBoxPanel05.add(current);

        // creates panel for mode button
        //modeButtonInfoPanel = new JPanel();


        // create mode button and adds listener
        mode = new JButton("Switch to decryption");
        mode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // checks if encryption or decryption mode
                if (encryptMode) {
                encryptMode = false;
               //Terry2
                readFile();
                mode.setText("Switch to encryption");
                } else {
                encryptMode = true;
                mode.setText("Switch to decryption");
                }
            }
            });

        // create info button and adds listener
        info = new JButton("Info");
        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // checks if encryption or decryption mode
                messagePopUp("Format of Input for GUI:\n\nGeneral:\n    Spaces " +
                        "can be used for plaintext input fields, but only certain keys " +
			"utilize spaces. \nCapital letters for plaintext can be used " +
			"but will be translated to lowercase \nprior " +
                        "to any cryptographic operation being performed on them.\n\nShift " +
                        "Cipher:\n    Key text field should contain a single integer " +
                        "within the range of 0 to 25 inclusive.\n\nAffine Cipher:\n    Key " +
                        "text field should contain two integers seperated by a single " +
                        "space, \nwith the first integer being with the range of 0 to 25 " +
                        "\ninclusive and the second integer being greater than 0. \nIf decrypting, " +
                        "the additional condition that the first integer must \nnot be a " +
                        "coprime with 26 also holds.\n\nVigenere Cipher:\n    Key text field " +
                        "should contain a string of characters.\n\nBifid Cipher:\n    The " +
                        "plaintext for the Bifid Cipher can contain spaces but no numbers \nand " +
                        "it will convert all characters to uppercase. " +
                        "Key text field should contain\n a string of characters with at least " +
			"one uppercase letter.\n\n All Ciphers:\n    Keys should follow the format " +
			"of the respective cyphers. \nAll four keys must be used and there " +
                        "cannot be any spaces \nwithin the key string except " +
			"for the affine cypher key.\nCurrent: Do not change or input new strings " +
			"into the input or key text fields. \nThe program will clear the textfields " +
			"and will encrypt or decrypt the last encrypted string."+
                        "\nWhen decrypting, separate the plaintext inputs " +
			"using \ncommas with no spaces before or after the comma."
			     ,"Info message");
            }
        });

        // adds mode button to mode panel
        cipherBoxPanel05.add(mode);
        cipherBoxPanel05.add(info);

        // creates overall button panel and adds cipher/mode button panels
        //buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //buttonPanel.add(cipherButtonPanel);
        //buttonPanel.add(modeButtonInfoPanel);

        // creates output label
        //output = new JLabel();

        // adds components to overall frame
        frame.getContentPane().add(BorderLayout.WEST, inputTextPanel);
        frame.getContentPane().add(BorderLayout.EAST, outputTextPanel);
        frame.getContentPane().add(BorderLayout.CENTER, inputKeyTextPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, cipherBoxPanel05);

        //frame.getContentPane().add(BorderLayout.NORTH, textFieldPanel);
        //frame.getContentPane().add(BorderLayout.SOUTH, output);

        // sets frame visible
        frame.setVisible(true);
    
    }
        
        
    public void messagePopUp(String message, String title) {
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}

