package edu.ucsb.cs56.projects.utilities.cryptography;

/**
   A class to implement the Vigenere Cipher.
   @author Callum Steele and Miranda Aperghis
   @version Project cs56, S13
 */

public class VigenereCipher {
    private String cipherKey = null;

    /**
       Default constructor.
    */
    public VigenereCipher() {}

    /**
       Constructor that takes the cipher key as an argument.
       @param cipherKey The String to use when encrypting plaintext.
     */
    public VigenereCipher(String cipherKey) {
	if (cipherKey == null || cipherKey.length() == 0) throw new IllegalArgumentException();
	this.cipherKey = cipherKey.toLowerCase();
    }

    /**
       Sets the cipher key to the given String.
       @param cipherKey The String to use when encrypting plaintext.
     */
    public void setCipherKey(String cipherKey) {
	if (cipherKey == null || cipherKey.length() == 0) throw new IllegalArgumentException();
	for (int i = 0; i < cipherKey.length(); i++) {
	    if(cipherKey.charAt(i)<97 || cipherKey.charAt(i)>122)
		throw new IllegalArgumentException();
	}
	this.cipherKey = cipherKey.toLowerCase();
    }

    /**
       Gets the cipher key.
     */
    public String getCipherKey() {
	return this.cipherKey;
    }

    /**
       Encrypts the given plaintext using the Vigenere Cipher. This is done by matching up the cipher key with the plaintext and applying the Shift Cipher to each letter in the plaintext, using the corresponding cipher key letter as the shift.
       @param input The String that represents the plaintext to be encrypted.
     */
    public String encrypt(String input) {
	if (input == null || input.length() == 0) throw new IllegalArgumentException();
	String plaintext = input.toLowerCase();
	String cipherText = "";
	ShiftCipher s = new ShiftCipher();
	
	for (int i = 0; i < plaintext.length(); i++) {
	    // sets the cipher key for the Shift Cipher
	    s.setCipherKey(cipherKey.charAt(i % cipherKey.length()) - 97);
	    // applies the Shift Cipher to the next character
	    cipherText = cipherText + s.encrypt(plaintext.substring(i, i+1));
	}
	
	return cipherText;
    }

    /**
       Decrypts the given ciphertext using the Vigenere Cipher. This is done by encrypting the ciphertext using the inverse of the key.
       @param input The string that represents the ciphertext to be decrypted.
    */
    public String decrypt(String input) {
	if (input == null || input.length() == 0) throw new IllegalArgumentException();
	String ciphertext = input.toLowerCase();
	String inverseKey = "";

	for (int i = 0; i < cipherKey.length(); i++) {
	    inverseKey += (char)(((26 - (cipherKey.charAt(i) - 97)) % 26) + 97);
	}

	String originalKey = getCipherKey();
	setCipherKey(inverseKey);
	String plaintext = encrypt(ciphertext);
	setCipherKey(originalKey);
	
	return plaintext;
    }
    
	

//Generate a random ten-letters String.
	public void generateKey() {
		final char[] letters=new char [] {'A','B','C','D','E','F',
				'G','H','I','J','K','L','M','N','O','P','Q','R',
				'S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g',
			'h','i','j','k','l','m','n','o','p','q','r','s','t'
			,'u','v','w','x','y','z'};
		cipherKey=Character.toString(letters[(int)(Math.random()*26)]);
	for(int i=0;i<9;++i){
		cipherKey+=letters[(int)(Math.random()*52)];
		}
	cipherKey=cipherKey.toLowerCase();
	
	}
	
	
	
}
