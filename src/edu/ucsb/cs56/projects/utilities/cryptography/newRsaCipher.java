// package edu.ucsb.cs56.projects.utilities.cryptography;

// import java.security.KeyPair;
// import java.security.KeyPairGenerator;
// import java.security.KeyFactory;
// import java.security.spec.X509EncodedKeySpec;
// import java.security.spec.PKCS8EncodedKeySpec;
// import java.security.NoSuchAlgorithmException;
// import java.security.spec.InvalidKeySpecException;
// import java.security.PrivateKey;
// import java.security.PublicKey;
// import javax.crypto.Cipher;
// import java.util.Base64;

// /**
//  *    A class to implement the RSA Cipher
//  *    @author Arielle Robles
//  *    @author Kaushik Mahorker
//  *    @version CS56 F17 UCSB
//  *
//  */

// public class RSACipher  {

//     final static int keySize = 1024;

//     //for convenient input/output to GUI
//     private String publicKeyString;
//     private String privateKeyString;

//     //created so we may use the Crypto library
//     //used to generate PublicKey and PrivateKey objects
//     //then set the String publicKey and privateKey
//     private PublicKey publicKeyObject;
//     private PrivateKey privateKeyObject;



//     /**
//      * Default no-arg constructor.
//      */
//      public RSACipher(){};

//      /**
//      *Two-arg Constructor
//      @param publicKey string of the RSA PublicKey
//      @param privateKey string of the RSA privateKey
//      */
//     public RSACipher(String publicKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException{
//        this.publicKeyString = publicKey;
//        this.privateKeyString = privateKey;
//        this.setPublicKeyObject(publicKey);
//        this.setPrivateKeyObject(privateKey);
//      }

//      public void generateKey() throws NoSuchAlgorithmException{
//        //generate a public and private key object
//        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//        kpg.initialize(keySize);
//        KeyPair keyPair = kpg.generateKeyPair();

//        //initialize the keys as objects
//        publicKeyObject = keyPair.getPublic();
//        privateKeyObject = keyPair.getPrivate();

//        //turn the objects into bytes
//        byte[] publicBytes = publicKeyObject.getEncoded();
//        byte[] privateBytes = privateKeyObject.getEncoded();

//       //initialize the keys as strings using their object bytes
//        publicKeyString = Base64.getEncoder().encodeToString(publicBytes);
//        privateKeyString = Base64.getEncoder().encodeToString(privateBytes);
//      }


//      //return the string representation of PublicKey object's bytes
//      //for the GUI to display
//      public String getPublicKey(){
//        return this.publicKeyString;
//      }


//      //return string representation of PublicKey object's bytes
//      //for GUI to display
//      public String getPrivateKey(){
//        return this.privateKeyString;
//      }


//      //intake the string representation of a public key
//      //from the GUI, turn it into a PublicKey object
//     private void setPublicKeyObject(String publicKeyString) throws InvalidKeySpecException, NoSuchAlgorithmException{
//        byte[] publicBytes = publicKeyString.getBytes();
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicBytes));
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PublicKey publicKeyObject = kf.generatePublic(spec);
//      }


//      //intake string representation of a private key object's Bytes
//      //from the GUI, turn back into privateKeyObject
//     private void setPrivateKeyObject(String privateKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException{
//        byte[] privateBytes = privateKeyString.getBytes();
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateBytes));
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PrivateKey privateKeyObject = kf.generatePrivate(spec);
//      }


//      //take in string input from GUI
//      //encrypt it with private key
//      public String encrypt(String plainText) throws Exception{
//        Cipher cipher;
//        cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, this.privateKeyObject);
//        byte[] encryptedText = cipher.doFinal(plainText.getBytes());
//        return (new String(encryptedText));
//      }

//      //take in string input from GUI
//     //decrypt it with public key
//     public String decrypt(String encryptedText) throws Exception{
//        byte[] textBytes = encryptedText.getBytes();
//        Cipher cipher;
//        cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, this.publicKeyObject);
//        byte[] decryptedText= cipher.doFinal(textBytes);
//        return (new String(decryptedText));
//     }
// }
