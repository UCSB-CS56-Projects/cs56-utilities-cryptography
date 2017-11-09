package edu.ucsb.cs56.projects.utilities.cryptography;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *    A class to implement the RSA Cipher
 *    @author Arielle Robles
 *    @author Kaushik Mahorker
 *    @version CS56 F17 UCSB
 *    
 */

public class RSACipher  {

    final static int keySize = 1024;
    
    private PublicKey pubKey;
    private PrivateKey privKey;
    private KeyPair keyPair;
    
    /**
     * Default no-arg constructor.
     */

    
    
    public RSACipher(){};
    
    /**
     * Constructor that takes in the two keys as strings from the GUI, privateKey first, then publicKey
     */
    public RSACipher(String privateKey, String publicKey){
	//get string public key and private key from the GUI 
	//turn the strings back into public key and privat key objects
	//initialize the instance variables of new RSACipher object to hold these keys
	
	this.pubKey = parsePublicKey(publicKey);
	this.privKey = parsePrivateKey(privateKey);

	//need to turn this into a method toKey
	//take in a string, turn it into a public or private key
	
	byte[] byteKeyPub = Base64.decode(publicKey.getBytes(), Base64.DEFAULT);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKeyPub);
        KeyFactory kf = KeyFactory.getInstance("RSA");
	this.pubKey = kf.generatePublic(X509publicKey);


	byte[] byteKeyPriv = Base64.decode(privateKey.getBytes(), Base64.DEFAULT);
        X509EncodedKeySpec X509privateKey = new X509EncodedKeySpec(byteKeyPriv);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.pubKey = kf.generatePrivate(X509privateKey);
	kf.generatePublic(X509privateKey);

    }

    
    //generates a new pair of public and private keys
    public void KeyPair generateKey(){
	KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	keyPairGen.initialize(keySize);
	this.keyPair = keyPairGen.genKeyPair(); //returns a keyPair object with a public and private RSA key
	this.pubKey = keyPair.getPublic();
	this.privKey = keyPair.getPrivate();
	
    }

    //getter for the private key
    public static PrivateKey getPrivateKey(){
	return this.privKey;
    }

    //getter for public key
    public static PublicKey getPublicKey(){
	return this.pubKey;
    }


    //encrypt: uses the objects private key to encrypt the string from GUI input
    public static byte[] encrypt(PrivateKey privateKey, String message){
	byte[] encrCipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
    }
    
    //decrypt the ciphered text from the GUI using the object's public key
    //changing to bytes allows us to use Cipher class. 
    public static byte[] decrypt(PublicKey publicKey, byte[] encryptedText){
	Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
	
    }


    public static PublicKey stringToKey(String key){
	//turns a string into a key object
	//input from GUI is a string, need to turn it into a public or private key object
	
        byte[] byteKey = Base64.decode(key.getBytes(), Base64.DEFAULT);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return kf.generatePublic(X509publicKey);
    
    }


    //toString should allow us to output the public and private keys
    //to the GUI keygen textbox.
    //publicKey and privateKey are stored as PublicKey and PrivateKey object
    //turn them into string to be output when called by ExecuteCipherGUI
    public String toString(){
    }

    //toKey should take the string from the GUI keygen textbox
    //and turn it back into a PublicKey and PrivateKey object
    //to be used to decrypt and encrypt


	
