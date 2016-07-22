package edu.ucsb.cs56.projects.utilities.cryptography;

/**
   A class to implement the Affine Cipher.
   @author Miranda Aperghis
   @author Callum Steele
   @version Project CS56, S13, 05/29/2013
 */
public class AffineCipher {
    private int keyA;
    private int keyB;

    /**
       Default no-arg constructor.
    */
    public AffineCipher(){}

    /**
       Two-arg constructor.
       @param keyA an integer that is used to encrypt the plaintext 
       @param keyB an integer that is used to encrypt the plaintext
    */
    public AffineCipher(int keyA, int keyB){
	this.keyA = keyA;
	this.keyB = keyB;
    }

    /**
       Getter for the cipher key A
       @return cipher key integer
    */
    public int getKeyA(){
	return this.keyA;
    }

    /**
       Getter for the cipher key B
       @return cipher key integer
    */
    public int getKeyB(){
	return this.keyB;
    }

    /** 
	Setter for the cipher key A
	@param keyA an integer is used to encrypt the plaintext
    */
    public void setKeyA(int keyA){
	this.keyA = keyA;
    }

    /** 
	Setter for the cipher key B
	@param keyB an integer is used to encrypt the plaintext
    */
    public void setKeyB(int keyB){
	this.keyB = keyB;
    }

    /**
       Encryption algorithm for the affine cipher.
       Each character is multiplied by keyA and then keyB is added, mod 26 to get the new encrypted character.
       @param word a plaintext word that is to be encrypted
       @return the ciphertext (the encrypted plaintext)
    */
    public String encrypt(String word){
	if(word == null||word.length()==0) throw new IllegalArgumentException();
	String result = "";
	String wordLower = word.toLowerCase();
	
	for(int i=0; i<wordLower.length(); i++){
	    if(wordLower.charAt(i)<97 || wordLower.charAt(i)>122)
		throw new IllegalArgumentException();
	    int a = (wordLower.charAt(i)-97)*this.keyA;
	    int b = a+this.keyB;
	    int k = (b%26)+97;
	    result += Character.toString((char)k);
	}
	return result;
    }

    /**
       Decryption algorithm for the affine cipher.
       @param word a ciphertext word the is to be decrypted
       @return the plaintext (the decrypted ciphertext)
    */
    public String decrypt(String word){
	String result = "";
	String wordLower = word.toLowerCase();
	if(MathUtil.coPrime(this.keyA, 26)==false)
	    throw new IllegalArgumentException();
	for(int i=0; i<wordLower.length(); i++){
	    if(wordLower.charAt(i)<97 || wordLower.charAt(i)>122)
		throw new IllegalArgumentException();
	    int a = (wordLower.charAt(i)-97)-this.keyB;
	    if(a<0)a+=26;
	    int b = a*MathUtil.modInverse(this.keyA);
	    int k = (b%26)+97;
	    result += Character.toString((char)k);
	}
	return result;
    }

	
//Terry2
	//Generate random keyA and KeyB, KeyB is not coprime with 26
	public void generateKey() {
		keyA=(int)(Math.random()*26);
		keyB=(int)(Math.random()*100+1);
		while(!MathUtil.coPrime(keyB, 26)){
			keyB=(int)(Math.random()*100+1);
		}
	}
	
	
	
	

}
