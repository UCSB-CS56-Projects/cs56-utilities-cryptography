package edu.ucsb.cs56.projects.utilities.cryptography;

/**
   A class to implement the RSA Cipher.
   @author Mario Infante
   @version Project CS56, F16, 11/19/2016
*/
public class RSACipher {
    private int rsaKeyA;
    private int rsaKeyB;
    private int publicE;
    private int privateD;

    /**
       Default no-arg constructor.
    */
    public RSACipher(){}

    /**
       Two-arg constructor.
       @param rsaKeyA an integer that is used to encrypt the plaintext
       @param rsaKeyB an integer that is used to encrypt the plaintext
    */
    public RSACipher(int rsaKeyA, int rsaKeyB){
	this.rsaKeyA = rsaKeyA;
	this.rsaKeyB = rsaKeyB;
    }

    /**
       Getter for the cipher key A
       @return cipher key integer
    */
    public int getRsaKeyA(){
	return this.rsaKeyA;
    }

    /**
       Getter for the cipher key B
       @return cipher key integer
    */
    public int getRsaKeyB(){
	return this.rsaKeyB;
    }

    /**
        Setter for the cipher key A
        @param keyA an integer is used to encrypt the plaintext
    */
    public void setRsaKeyA(int rsakeyA){
	this.rsaKeyA = rsaKeyA;
    }

    /**
        Setter for the cipher key B
        @param keyB an integer is used to encrypt the plaintext
    */
    public void setRsaKeyB(int rsakeyB){
	this.rsaKeyB = rsaKeyB;
    }

    /**
       Encryption algorithm for the affine cipher.
       Each character is multiplied by keyA and then keyB is added, mod 26 to ge
t the new encrypted character.
       @param word a plaintext word that is to be encrypted
       @return the ciphertext (the encrypted plaintext)
    */
    public String encrypt(String word){
	int modulus = rsaKeyA * rsaKeyB;
	int totient = (rsaKeyA - 1)*(rsaKeyB - 1);
	publicE = calculateExponent(totient);
	privateD = 1 + ((int)Math.random()*10)*totient;
  
	if(word == null||word.length()==0) throw new IllegalArgumentException();
	String result = "";
	String wordLower = word.toLowerCase();

	for(int i=0; i<wordLower.length(); i++){
	    if(wordLower.charAt(i)<97 || wordLower.charAt(i)>122)
		throw new IllegalArgumentException();
	    int a = (wordLower.charAt(i)-97);
	    int b = (int)Math.pow(a,publicE);
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
	for(int i=0; i<wordLower.length(); i++){
	    if(wordLower.charAt(i)<97 || wordLower.charAt(i)>122)
		throw new IllegalArgumentException();
	    int a = (wordLower.charAt(i)-97);
	    int b = (int)Math.pow(a,privateD);
	    int k = (b%26)+97;
	    result += Character.toString((char)k);
	}
	return result;
    }


    // keyA range from 1-26
    //Generate random keyA and KeyB, KeyA is coprime with 26
    public void generateKey() {
	while(true){
	    rsaKeyA = (int)(Math.random()*20000 + 26);
	    if(isPrime(rsaKeyA)){
		break;
	    }
	}
	while(true){
	    rsaKeyB = (int)(Math.random()* 20000 + 26);
	    if(isPrime(rsaKeyB)&& rsaKeyB != rsaKeyA){
		break;
	    }
	}
        
    }

    public boolean isPrime(int n) {
	int i;
	for(i=2;i<=Math.sqrt(n);i++){
	    if(n%i==0){
		return false;
	    }
	}
	return true;
    }

    public int calculateExponent(int totient){
	int exponent=1;
	while(exponent<totient){
	    if(MathUtil.coPrime(exponent,totient) == true){
		return exponent;
	    }
	    exponent++;
	}
	return 1;
    }
}
