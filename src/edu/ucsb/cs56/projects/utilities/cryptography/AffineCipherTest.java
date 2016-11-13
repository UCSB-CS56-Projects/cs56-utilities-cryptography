package edu.ucsb.cs56.projects.utilities.cryptography;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
   A class to test AffineCipher.
   @author Miranda Aperghis
   @author Callum Steele
   @version Project, CS56, S13, 05/29/2013
   @see AffineCipher
*/
public class AffineCipherTest{

    /** Test constructor and getter */
    @Test
    public void testConstructorAndGetter(){
	int keyA = 2;
	int keyB = 4;
	AffineCipher a = new AffineCipher(keyA, keyB);
	assertEquals(2,a.getKeyA());
	assertEquals(4,a.getKeyB());
    }
    
    /** Test setter and getter */
    @Test
    public void testSetter(){
	int keyA = 2;
	int keyB = 4;
	AffineCipher a = new AffineCipher(keyA, keyB);
	a.setKeyA(5);
	a.setKeyB(7);
	assertEquals(5,a.getKeyA());
	assertEquals(7,a.getKeyB());
    }

    /** Test encryption 1 */
    @Test
    public void testEncryptionAffine1(){
	int keyA = 3;
	int keyB = 7;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "hello";
	assertEquals("ctoox",a.encrypt(word));
    }

    /** Test encryption 2*/
    @Test
    public void testEncryptionAffine2(){
	int keyA = 1;
	int keyB = 3;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "hello";
	assertEquals("khoor",a.encrypt(word));
    }

    /** Test encryption 3*/
    @Test
    public void testEncryptionAffine3(){
	int keyA = 9;
	int keyB = 23;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "miranda";
	assertEquals("bruxkyx",a.encrypt(word));
    }

    /** Test encryption 4*/
    @Test
    public void testEncrptionAffine4(){
	int keyA = 9;
	int keyB = 23;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "HELLO";
	assertEquals("ihsst",a.encrypt(word));
    }


    /** Test illegal argument exception word 1*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord1(){
	int keyA = 3;
	int keyB = 7;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = " ";
	String cipher = a.encrypt(word);
    }

    /** Test illegal argument exception word 2*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord2(){
        int keyA = 3;
	int keyB = 7;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = null;
	String cipher = a.encrypt(word);
    }

    /** Test illegal argument exception word 3*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord3(){
	int keyA = 4;
	int keyB = 5;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "hello4";
	String cipher = a.encrypt(word);
    }

    /** Test illegal argument exception word 4*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord4(){
        int keyA = 3;
	int keyB = 7;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "57";
	String cipher = a.encrypt(word);
    }

    /** Test illegal argument exception co prime key*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionCoPrime(){
        int keyA = 4;
	int keyB = 7;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "hello";
	String cipher = a.decrypt(word);
    }

    /** Test decryption word 1*/
    @Test
    public void testDecryptAffine1(){
        int keyA = 1;
	int keyB = 3;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "khoor";
	assertEquals("hello",a.decrypt(word));
    }

    /** Test decryption word 2*/
    @Test
    public void testDecryptAffine2(){
        int keyA = 5;
	int keyB = 22;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "ekdwjlw";
	assertEquals("miranda",a.decrypt(word));
    }

    /** Test decryption word 3*/
    @Test
    public void testDecryptAffine3(){
        int keyA = 21;
	int keyB = 13;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "fZgNaYn";
	assertEquals("miranda",a.decrypt(word));
    }
    
    /** Test generateKey 1*/
    @Test
    public void testGenerateKey(){
    	AffineCipher a=new AffineCipher();
    	a.generateKey();
    	assertEquals(a.getKeyA()<=25&&a.getKeyA()>=0,true);
    	assertEquals(a.getKeyB()>0,true);
    	assertEquals(MathUtil.coPrime(a.getKeyB(),26),true);
    }
}
