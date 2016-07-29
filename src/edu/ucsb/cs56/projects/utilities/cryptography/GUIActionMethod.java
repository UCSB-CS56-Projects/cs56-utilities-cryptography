package edu.ucsb.cs56.projects.utilities.cryptography;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;


/**
A class holding methods for action listener, will be called in actionPerform of every action listener.
	@author Zhiliang Xie (Terry)
	@author Zhancheng Qian
@version Project CS56, M16, 07/26/2016
*/

public class GUIActionMethod {
	//enable to change the state of the original GUI
	CryptographyGUI GUI; 
	//enable to change the state of the original GUI
	AllCipherGUI allGUI; 

	public GUIActionMethod(CryptographyGUI GUI){
		this.GUI=GUI;
	}

	public GUIActionMethod(CryptographyGUI GUI, AllCipherGUI allGUI){
		this.GUI=GUI;
		this.allGUI=allGUI;
	}
	
	
	//  Check whether in decryption mode or encryption mode	
	public  void checkMode() throws Exception {
		//if in Decryption Mode throw exception
		if(!GUI.encryptMode) {
			GUI.messagePopUp("In Decryption Mode now, swith to Encryption mode "
					+ "to generate random keys.","Generate Keys Mode Error");
			throw new Exception();}
	}

	
	// encrypt/decrypt using shift cipher	 
	public void ExecuteShift(){
		if(GUI.ShKeyInput.getText().equals(null)||GUI.ShKeyInput.getText().length()==0){
			for (int j=0;j<GUI.storedKey.size();++j){
				if(GUI.storedMethod.get(j).equals("shift")&&GUI.storedOutput.get(j).equals(GUI.inputArea.getText())){
					GUI.shiftKey=Integer.parseInt(GUI.storedKey.get(j));
					GUI.shiftCipher.setCipherKey(GUI.shiftKey);
					GUI.ShKeyInput.setText(Integer.toString(GUI.shiftKey));
				}
			}	
		}

		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");

		// get key from key text field and set it as key in cipher object
		try {
			GUI.shiftKey = Integer.parseInt(GUI.ShKeyInput.getText());
			GUI.shiftCipher.setCipherKey(GUI.shiftKey);
			GUI.cipherText = "";
			// checks if encrypting or decrypting
			for(int i = 0; i < GUI.inputs.length; i++) {
				if (GUI.encryptMode)
					GUI.cipherText += GUI.shiftCipher.encrypt(GUI.inputs[i]);
				else{
					GUI.cipherText += GUI.shiftCipher.decrypt(GUI.inputs[i]);}
				if(i < GUI.inputs.length - 1){
					GUI.cipherText += " ";}
			}

			//Write the file to directory
			try {
				if(GUI.addressText.getText().length()>0){
					File saveLocation = new File(GUI.addressText.getText());
					if(!saveLocation.exists()){
						saveLocation.mkdir();
					}
					File myFile = new File(GUI.addressText.getText(), GUI.fileNameText.getText());
					PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile,true));
					textFileWriter.println("shift,"+GUI.ShKeyInput.getText()+","+GUI.cipherText);
					textFileWriter.close();

				}else{PrintWriter out = new PrintWriter(new FileWriter(GUI.fileNameText.getText(),true));
				out.println("shift,"+GUI.ShKeyInput.getText()+","+GUI.cipherText);
				out.close();}
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			// puts result in the output label
			GUI.outputArea.setText(GUI.cipherText);
			//  GUI.cipherText=this.GUI.cipherText;
			GUI.last = 0;

		} catch (Exception ex) {
			// create popup
			GUI.messagePopUp("Incorrect input for Shift Cipher.\nPlaintext is " +
					"a string and can't have non-alphabetic " +
					"characters.\nKey should contain a single integer within " +
					"the range 0 to 25.\n", "Shift Cipher Input Error");}
	}

	
	//encrypt/decrypt using affiner cipher
	public void ExecuteAffine(){
		if((GUI.AfKeyInput.getText().equals(null)||GUI.AfKeyInput.getText().length()==0)&&!GUI.encryptMode){
			for (int j=0;j<GUI.storedKey.size();++j){
				if(GUI.storedMethod.get(j).equals("affine")&&GUI.storedOutput.get(j).equals(GUI.inputArea.getText())){
					GUI.keyA=Integer.parseInt(GUI.storedKey.get(j).split(" ")[0]);
					GUI.keyB=Integer.parseInt(GUI.storedKey.get(j).split(" ")[1]);
					GUI.affineCipher.setKeyA(GUI.keyA);
					GUI.affineCipher.setKeyB(GUI.keyB);
					GUI.AfKeyInput.setText(Integer.toString(GUI.keyA)+" "+GUI.keyB);
				}
			}	
		}

		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		// gets keys from key text field and sets as the keys in cipher object
		try {
			GUI.key = GUI.AfKeyInput.getText();
			GUI.keyA = Integer.parseInt(GUI.key.substring(0, GUI.key.indexOf(' ')));
			GUI.keyB = Integer.parseInt(GUI.key.substring(GUI.key.indexOf(' ') + 1));
			GUI.affineCipher.setKeyA(GUI.keyA);
			GUI.affineCipher.setKeyB(GUI.keyB);
			GUI.cipherText = "";

			// checks if encrypting or decrypting
			for(int i = 0; i < GUI.inputs.length; i++) {		
				if (GUI.encryptMode)
					GUI.cipherText += GUI.affineCipher.encrypt(GUI.inputs[i]);
				else {
					GUI.cipherText += GUI.affineCipher.decrypt(GUI.inputs[i]);}
				if(i < GUI.inputs.length - 1)
					GUI.cipherText += " ";
			}
			//Write the file     

			//Write the file to directory
			try {
				if(GUI.addressText.getText().length()>0){
					File saveLocation = new File(GUI.addressText.getText());
					if(!saveLocation.exists()){
						saveLocation.mkdir();
					}
					File myFile = new File(GUI.addressText.getText(), GUI.fileNameText.getText());
					PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile,true));
					textFileWriter.println("affine,"+GUI.AfKeyInput.getText()+","+GUI.cipherText);
					textFileWriter.close();
				}else{PrintWriter out = new PrintWriter(new FileWriter(GUI.fileNameText.getText(),true));
				out.println("affine,"+GUI.AfKeyInput.getText()+","+GUI.cipherText);
				out.close();}
			}
			catch (Exception ex){
				ex.printStackTrace();
			}

			// puts result in the output label
			GUI.outputArea.setText(GUI.cipherText);
			//   GUI.cipherText=this.cipherText;

			GUI.last=1;
		} catch (Exception ex) {
			// create popup
			GUI.messagePopUp("Incorrect input for Affine Cipher.\nPlaintext " +
					"input is a String without non-alphabetic characters.\nKey takes 2 integers " +
					"(a and b) separated by a single space the first \ninteger " +
					"being within the range 0 to 25 and the second greater than 0.\n" +
					"If Decrypting, the second integer must not be a coprime with 26",
					"Affine Cipher Input Error");
		}
	}

	//encrypt/decrypt using vigenere cipher
	public void ExecuteVigenere(){
		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		// gets key from key text field and sets as the key in cipher object
		try {
			if((GUI.ViKeyInput.getText().equals(null)||GUI.ViKeyInput.getText().length()==0)&&!GUI.encryptMode){
				for (int j=0;j<GUI.storedKey.size();++j){
					if(GUI.storedMethod.get(j).equals("vigenere")&&GUI.storedOutput.get(j).equals(GUI.inputArea.getText())){
						GUI.key=GUI.storedKey.get(j);
						GUI.vigenereCipher.setCipherKey(GUI.key);
						GUI.ViKeyInput.setText(GUI.key);
					}
				}
			}
			GUI.key = GUI.ViKeyInput.getText();
			GUI.vigenereCipher.setCipherKey(GUI.key);
			GUI.cipherText = "";

			// checks if encrypting or decrypting
			for(int i = 0; i < GUI.inputs.length; i++) {	
				if (GUI.encryptMode){
					GUI.cipherText += GUI.vigenereCipher.encrypt(GUI.inputs[i]);
				}
				else{
					GUI.cipherText += GUI.vigenereCipher.decrypt(GUI.inputs[i]);}
				if(i < GUI.inputs.length - 1)
					GUI.cipherText += " ";
			}
			
			//Write the file to directory
			try {
				if(GUI.addressText.getText().length()>0){

					File saveLocation = new File(GUI.addressText.getText());
					if(!saveLocation.exists()){
						saveLocation.mkdir();
					}
					File myFile = new File(GUI.addressText.getText(), GUI.fileNameText.getText());
					PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile,true));
					textFileWriter.println("vigenere,"+GUI.ViKeyInput.getText()+","+GUI.cipherText);
					textFileWriter.close();
				}else{PrintWriter out = new PrintWriter(new FileWriter(GUI.fileNameText.getText(),true));
				out.println("vigenere,"+GUI.ViKeyInput.getText()+","+GUI.cipherText);
				out.close();}
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			// puts result in the output label
			GUI.outputArea.setText(GUI.cipherText);
			// GUI.cipherText=this.cipherText;
			GUI.last = 2;
			GUI.last=2;
		} catch (Exception ex) {
			// create popup
			GUI.messagePopUp("Incorrect input for Vigenere Cipher.\nPlaintext " +
					"is a String with only letters.\nKey is a String with " +
					"only letters.\nNumbers will cause an exception " +
					"to be thrown", "Vigenere Cipher Input Error");}
	}

	
	//encrypt/decrypt using bifid cipher	 
	public void ExecuteBifid(){
		if((GUI.BiKeyInput.getText().equals(null)||GUI.BiKeyInput.getText().length()==0)&&!GUI.encryptMode){
			for (int j=0;j<GUI.storedKey.size();++j){
				if(GUI.storedMethod.get(j).equals("bifid")&&GUI.storedOutput.get(j).equals(GUI.inputArea.getText())){
					GUI.key=GUI.storedKey.get(j);
					GUI.bifidCipher.setCipherKey(GUI.key);
					GUI.BiKeyInput.setText(GUI.key);
				}
			}
		}

		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		// get key from key text field and set it as key in cipher object
		try {
			GUI.key = GUI.BiKeyInput.getText();
			GUI.bifidCipher.setCipherKey(GUI.key);
			GUI.cipherText = "";
			// checks if encrypting or decrypting
			for(int i = 0; i < GUI.inputs.length; i++) {		
				if (GUI.encryptMode){
					GUI.cipherText += GUI.bifidCipher.encrypt(GUI.inputs[i]);}
				else{
					GUI.BiKeyInput.setText(GUI.key);
					GUI.cipherText += GUI.bifidCipher.decrypt(GUI.inputs[i]);}
				if(i < GUI.inputs.length - 1)
					GUI.cipherText += " ";
			}

			//Write the file to directory
			try {
				if(GUI.addressText.getText().length()>0){
					File saveLocation = new File(GUI.addressText.getText());
					if(!saveLocation.exists()){
						saveLocation.mkdir();
					}
					File myFile = new File(GUI.addressText.getText(), GUI.fileNameText.getText());
					PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile,true));
					textFileWriter.println("bifid,"+GUI.BiKeyInput.getText()+","+GUI.cipherText);
					textFileWriter.close();

				}else{PrintWriter out = new PrintWriter(new FileWriter(GUI.fileNameText.getText(),true));
				out.println("bifid,"+GUI.BiKeyInput.getText()+","+GUI.cipherText);
				out.close();}
			}
			catch (Exception ex){
				ex.printStackTrace();
			}

			// Puts result in the output label
			GUI.outputArea.setText(GUI.cipherText);
			//GUI.cipherText=this.cipherText;
			GUI.last = 3;
		} catch (Exception ex) {
			// create popup
			GUI.messagePopUp("Incorrect input for Bifid Cipher.\nPlaintext " +
					"input can be any String so long as there are no numbers.\nKey " +
					"input can be any String with at least one uppercase letter " +
					"as well as no numbers.\nAll non-alphabetic characters will " +
					"be deleted from the String.",
					"Bifid Cipher Input Error");
		}
	}

	
	 // generate random key of affine cipher	 
	public void AffineGenKey(){
		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		GUI.affineCipher.generateKey();
		GUI.affineKeyA = GUI.affineCipher.getKeyA();
		GUI.affineKeyB = GUI.affineCipher.getKeyB();
		GUI.cipherText = "";
		try{
			//check if in decryption mode
			checkMode();
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}

		// put the random key to the key bar
		GUI.AfKeyInput.setText(GUI.affineKeyA+" "+GUI.affineKeyB);
	}

	
	 // generate random key of bifid cipher	 
	public void BifidGenKey(){
		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		GUI.bifidCipher.generateKey();
		GUI.key = GUI.bifidCipher.getCipherKey();
		GUI.cipherText = "";
		try{
			//check if in decryption mode
			checkMode();
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
		// put the random key to the key bar
		GUI.BiKeyInput.setText(GUI.key);
	}

	
	 //generate random key of shift cipher	 
	public void ShiftGenKey(){
		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		GUI.shiftCipher.generateKey();
		GUI.shiftKey = GUI.shiftCipher.getCipherKey();
		GUI.cipherText = "";
		try{
			//check if in decryption mode
			checkMode();
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
		
		// put the random key to the key bar
		GUI.ShKeyInput.setText(Integer.toString(GUI.shiftKey));
	}

	
	 //generate random key of vigenere cipher	 
	public void VigenereGenKey(){
		// get text from plaintext text field
		GUI.plainText = GUI.inputArea.getText();
		GUI.inputs = GUI.plainText.split("\\s+");
		GUI.vigenereCipher.generateKey();
		GUI.key = GUI.vigenereCipher.getCipherKey();
		GUI.cipherText = "";
		try{
			//check if in decryption mode
			checkMode();
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
		//put the random key to the key bar
		GUI.ViKeyInput.setText(GUI.key);
	}

	
	 //generate random key of all ciphers	 
	public void AllGenKey(){
		try{
			//check if in decryption mode
			checkMode();
		}catch(Exception ex){
			ex.printStackTrace();
			return;
		}
		// get text from plaintext text field
		allGUI.plainText = allGUI.inputArea.getText();
		allGUI.inputs = allGUI.plainText.split("\\s+");

		allGUI.key = "";
		allGUI.shiftCipher.generateKey();
		allGUI.key+=allGUI.shiftCipher.getCipherKey();
		allGUI.key += ",";

		allGUI.affineCipher.generateKey();
		allGUI.key += allGUI.affineCipher.getKeyA();
		allGUI.key += " ";
		allGUI.key += allGUI.affineCipher.getKeyB();
		allGUI.key += ",";

		allGUI.vigenereCipher.generateKey();
		allGUI.key +=allGUI.vigenereCipher.getCipherKey();
		allGUI. key += ",";

		allGUI.bifidCipher.generateKey();
		allGUI.key += allGUI.bifidCipher.getCipherKey();
		allGUI.cipherText = "";
		//put the random key to the key bar
		allGUI.AllKeyInput.setText(allGUI.key);
	}

	
	
	 // encrypt/decrypt using all ciphers
	public void ExecuteAll(){
		
		// get text from plaintest test field
		allGUI.plainText = allGUI.inputArea.getText();
		allGUI.inputs = allGUI.plainText.split("\\s+");
		// if in decryption, stores all texts for each string
		try{
			if(!(GUI.encryptMode)) {
				allGUI.decryptInputs = allGUI.plainText.split(",");
				for(int x = 0; x < allGUI.decryptInputs.length; x++)
					System.out.println(allGUI.decryptInputs[x]);
				allGUI.shiftInputs = allGUI.decryptInputs[0].split("\\s+");
				allGUI.affineInputs = allGUI.decryptInputs[1].split("\\s+");
				allGUI.vigenereInputs = allGUI.decryptInputs[2].split("\\s+");
				allGUI.bifidInputs = allGUI.decryptInputs[3].split("\\s+");			
			}
		}catch(Exception ex){
			allGUI.messagePopUp("Wrong text format, please see info", "All Cipher Input Error");
			return;
		}
		try {
			// get keys for each cypher type
			allGUI.key = allGUI.AllKeyInput.getText();
			String[] keyInputs = allGUI.key.split(",");
			for(int x = 0; x < keyInputs.length; x++)
				System.out.println(keyInputs[x]);
			int shiftKey = Integer.parseInt(keyInputs[0]);
			allGUI.shiftCipher.setCipherKey(shiftKey);
			allGUI.affineKeyA = Integer.parseInt(keyInputs[1].substring(0,
					keyInputs[1].indexOf(' ')));
			allGUI.affineKeyB = Integer.parseInt(keyInputs[1].substring
					(keyInputs[1].indexOf(' ') + 1));
			allGUI.affineCipher.setKeyA(allGUI.affineKeyA);
			allGUI.affineCipher.setKeyB(allGUI.affineKeyB);
			allGUI.vigenereCipher.setCipherKey(keyInputs[2]);
			allGUI.bifidCipher.setCipherKey(keyInputs[3]);
			allGUI.cipherText = "";
			//Adds all encryption/decryption to output string
			if(GUI.encryptMode) {
				allGUI.cipherText = "Shift: ";
				for(int a = 0; a < allGUI.inputs.length; a++) {
					allGUI.cipherText += allGUI.shiftCipher.encrypt(allGUI.inputs[a]);
					allGUI.cipherText += " ";
				}
				//cipherText += newLine;
				allGUI.cipherText += allGUI.newLine;
				allGUI.cipherText += "Affine: ";
				for(int b = 0; b <allGUI. inputs.length; b++) {
					allGUI. cipherText += allGUI.affineCipher.encrypt(allGUI.inputs[b]);
					allGUI.cipherText += " ";
				}
				allGUI.cipherText += allGUI.newLine;
				allGUI.cipherText += "Vigenere: ";
				for(int c = 0; c < allGUI.inputs.length; c++) {
					allGUI.cipherText += allGUI.vigenereCipher.encrypt(allGUI.inputs[c]);
					allGUI. cipherText += " ";
				}
				allGUI.cipherText += allGUI.newLine;
				allGUI.cipherText += "Bifid: ";
				for(int d = 0; d < allGUI.inputs.length; d++) {
					allGUI.cipherText += allGUI.bifidCipher.encrypt(allGUI.inputs[d]);
					allGUI.cipherText += " ";
				}
			}
			else {
				allGUI.cipherText = "Shift: ";
				for(int a = 0; a <allGUI. shiftInputs.length; a++) {
					allGUI. cipherText += allGUI.shiftCipher.decrypt(allGUI.shiftInputs[a]);
					allGUI.cipherText += " ";
				}
				allGUI.cipherText += allGUI.newLine;
				//cipherText += newLine;
				allGUI.cipherText += "Affine: ";
				for(int b = 0; b < allGUI.affineInputs.length; b++) {
					allGUI.cipherText += allGUI.affineCipher.decrypt(allGUI.affineInputs[b]);
					allGUI.cipherText += " ";
				}
				allGUI.cipherText += allGUI.newLine;
				//cipherText += newLine;
				allGUI.cipherText += "Vigenere: ";
				for(int c = 0; c < allGUI.vigenereInputs.length; c++) {
					allGUI.cipherText += allGUI.vigenereCipher.decrypt(allGUI.vigenereInputs[c]);
					allGUI.cipherText += " ";
				}
				allGUI.cipherText += allGUI.newLine;
				//cipherText += newLine;
				allGUI.cipherText += "Bifid: ";
				for(int d = 0; d < allGUI.shiftInputs.length; d++) {
					allGUI.cipherText += allGUI.bifidCipher.decrypt(allGUI.bifidInputs[d]);
					allGUI.cipherText += " ";
				}
			}

			//Write the file to directory
			try {
				if(GUI.addressText.getText().length()>0){
					File saveLocation = new File(GUI.addressText.getText());
					if(!saveLocation.exists()){
						saveLocation.mkdir();
					}
					File myFile = new File(GUI.addressText.getText(), GUI.fileNameText.getText());
					PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile,true));
					textFileWriter.println(allGUI.cipherText);
					textFileWriter.close();
				}else{PrintWriter out = new PrintWriter(new FileWriter(GUI.fileNameText.getText(),true));
				out.println(allGUI.cipherText);
				out.close();}
				allGUI.outputArea.setText(allGUI.cipherText);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			// create popup
			allGUI.messagePopUp("Wrong keys or Text format, please see info", "All Cipher Input Error");
		}
	}

	
	 //present current text	 
	public void ExecuteCurrent(){
		// checks if encryption or decryption mode and switches
		if (GUI.encryptMode) {
			GUI.encryptMode = false;
		} else {
			GUI.encryptMode = true;
		}
		GUI.outputArea.setText("");
		GUI.inputArea.setText("");
		GUI.ShKeyInput.setText("");
		GUI.AfKeyInput.setText("");
		GUI.ViKeyInput.setText("");
		GUI.BiKeyInput.setText("");
		String currentCipher = "";
		try {
			GUI.inputs = GUI.cipherText.split("\\s+");
			if(GUI.last == 0) {
				GUI.shiftCipher.setCipherKey(GUI.shiftKey);
			}
			if(GUI.last == 1) {
				GUI.affineCipher.setKeyA(GUI.keyA);
				GUI.affineCipher.setKeyB(GUI.keyB);
			}
			if(GUI.last == 2){
				GUI.vigenereCipher.setCipherKey(GUI.key);}
			if(GUI.last == 3){
				GUI.bifidCipher.setCipherKey(GUI.key);}
			//Adds all encryption/decryption to output string
			if(GUI.last == 0) {
				for(int i = 0; i < GUI.inputs.length; i++) {
					if (GUI.encryptMode)
						currentCipher += GUI.shiftCipher.encrypt(GUI.inputs[i]);
					else
						currentCipher += GUI.shiftCipher.decrypt(GUI.inputs[i]);
					if(i < GUI.inputs.length - 1)
						currentCipher += " ";
				}
			}
			if(GUI.last == 1) {
				for(int i = 0; i < GUI.inputs.length; i++) {		
					if (GUI.encryptMode)
						currentCipher += GUI.affineCipher.encrypt(GUI.inputs[i]);
					else
						currentCipher += GUI.affineCipher.decrypt(GUI.inputs[i]);
					if(i < GUI.inputs.length - 1)
						currentCipher += " ";
				}
			}
			if(GUI.last == 2) {
				for(int i = 0; i < GUI.inputs.length; i++) {		
					if (GUI.encryptMode)
						currentCipher += GUI.vigenereCipher.encrypt(GUI.inputs[i]);
					else
						currentCipher += GUI.vigenereCipher.decrypt(GUI.inputs[i]);
					if(i < GUI.inputs.length - 1)
						currentCipher += " ";
				}
			}
			if(GUI.last == 3) {
				for(int i = 0; i < GUI.inputs.length; i++) {		
					if (GUI.encryptMode)
						currentCipher += GUI.bifidCipher.encrypt(GUI.inputs[i]);
					else
						currentCipher +=GUI. bifidCipher.decrypt(GUI.inputs[i]);
					if(i < GUI.inputs.length - 1)
						currentCipher += " ";
				}
			}
			try {
				PrintWriter out = new PrintWriter("output.txt");

				for(int i = 0; i < GUI.inputs.length; i++) {
					out.print(GUI.inputs[i]);
					out.print(" ");
				}
				out.println();
				out.println(GUI.keyInput.getText());
				out.println(GUI.cipherText);
				out.close();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}

			// puts result in the output label

			GUI.outputArea.setText(currentCipher);
			if (GUI.encryptMode) {
				GUI.encryptMode = false;
			} else {
				GUI.encryptMode = true;
			}
		} catch (Exception ex) {
			// create popup

			GUI.messagePopUp("Please do not change input fields to decrypt " +
					" or encrypt current cipher text.", "Curent Cipher Error");
		}

	}

	
	 // prompt the information by clicking info button	 
	public void SendInfo(){
		GUI.messagePopUp("Format of Input for GUI:\n\nGeneral:\n    Spaces " +
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
				"for the affine cypher key.\n\nCurrent: Do not change or input new strings " +
				"into the input or key text fields. \nThe program will clear the textfields " +
				"and will encrypt or decrypt the last encrypted string."+
				"\nWhen decrypting, separate the plaintext inputs " +
				"using \ncommas with no spaces before or after the comma.\n\n"
				+ "Copy:\nCopies the output text into clipboard, use ctrl + v to paste.\n\n" 
				+"Clean:\nHit clean to clean all the text fields.\n\n"
				,"Info message");
	}
	
	
	 //switch to encrypt mode
	 
	public void SwitchToEncrypt(){
		GUI.encryptMode = true;//switch to encryptmode
		GUI.inputText.setText("Plain Text: ");
		GUI.outputText.setText("Ciphered Text: ");
	}

	
	// switch to decrypt mode	 
	public void SwitchToDecrypt(){
		GUI.encryptMode = false;//switch to decryptmode
		GUI.inputText.setText("Ciphered Text: ");
		GUI.outputText.setText("Plain Text: ");

	}

	
	// copy the output text	 
	public void Copy(){
		StringSelection stringSelection = new StringSelection(GUI.outputArea.getText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

	
	// clean the corresponding textarea	 
	public void Clean_01(){
		GUI.inputArea.setText("");
		GUI.outputArea.setText("");
		GUI.ShKeyInput.setText("");
		GUI.AfKeyInput.setText("");
		GUI.ViKeyInput.setText("");
		GUI.BiKeyInput.setText("");
	}

	
	// clean the corresponding textarea	 
	public void Clean_02(){
		allGUI.inputArea.setText("");
		allGUI.outputArea.setText("");
		allGUI.AllKeyInput.setText("");
	}

	
	  //prompt the GUI when clicking all cipher button
	public void MakeAllCipherGUI(){
		AllCipherGUI allcipherGUI = new AllCipherGUI(GUI);
		allcipherGUI.createFrame();

	}

}
