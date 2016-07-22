package edu.ucsb.cs56.projects.utilities.cryptography;

/**
   A class to implement the Shift Cipher.
   The class can encrypt a plaintext work and decrypt a ciphertext word.
   @author Miranda Aperghis
   @author Callum Steele
   @version Project CS56, S13, 05/29/2013
 */
public class ShiftCipher {
    private int cipherKey;

    /**
       Default no-arg constructor.
    */
    public ShiftCipher(){}

    /**
       One-arg constructor.
       @param cipherKey an integer that is used to encrypt the plaintext
    */
    public ShiftCipher(int cipherKey){
	if(cipherKey<0 || cipherKey>26) throw new IllegalArgumentException();
	this.cipherKey = cipherKey;
    }

    /**
       Getter for the cipher key
       @return cipher key integer
    */
    public int getCipherKey(){
	return this.cipherKey;
    }

    /** 
	Setter for the cipher key
	@param cipherKey an integer is used to encrypt the plaintext
    */
    public void setCipherKey(int cipherKey){
	if(cipherKey<0 || cipherKey>26) throw new IllegalArgumentException();
	this.cipherKey = cipherKey;
    }

    /**
       Encryption algorithm for the shift cipher.
       Takes a word and then shifts the characters by a given number of places through the alphabet, defined by the cipherKey.
       @param word a plaintext word that is to be encrypted
       @return the ciphertext (the encrypted plaintext)
    */
    public String encrypt  (String word) {
	if(word == null||word.length()==0) throw new IllegalArgumentException();
	String result = "";
	String wordLower = word.toLowerCase();
	for(int i=0; i<wordLower.length(); i++){
	    if(wordLower.charAt(i)<97 || wordLower.charAt(i)>122)
		throw new IllegalArgumentException();
	    int k = (((wordLower.charAt(i)-97)+this.cipherKey)%26)+97;
	    result += Character.toString((char)k);
	}
	return result;
    }

    /**
       Decryption algorithm for the shift cipher.
       Takes a word and then shift the characters by a given number of places the opposite way through the alphabet, defined by the cipherKey.
       @param word a ciphertext word that is to be decrypted
       @return the plaintext (the decrypted ciphertext)
    */
    public String decrypt(String word){
	if(word == null) throw new IllegalArgumentException();
	String result = "";
	String wordLower = word.toLowerCase();
	for(int i=0; i<wordLower.length(); i++){
	    if(wordLower.charAt(i)<97 || wordLower.charAt(i)>122)
		throw new IllegalArgumentException();
	    int k = (wordLower.charAt(i)-97)-this.cipherKey;
	    if(k<0) k+=26;
	    k += 97;
	    result += Character.toString((char)k);
	}
	return result;
    }
    
	


	public void generateKey() {
		cipherKey=(int)(Math.random()*26);
		
	}
}
