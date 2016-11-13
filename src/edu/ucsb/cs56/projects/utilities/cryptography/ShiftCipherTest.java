package edu.ucsb.cs56.projects.utilities.cryptography;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
   A class to test ShiftCipher.
   @author Miranda Aperghis
   @author Callum Steele
   @version Project, CS56, S13, 05/29/2013
   @see ShiftCipher
*/
public class ShiftCipherTest{

    /** Test constructor and getter */
    @Test
    public void testConstructorAndGetter(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	assertEquals(4,s.getCipherKey());
    }
    
    /** Test setter and getter */
    @Test
    public void testSetter(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	s.setCipherKey(6);
	assertEquals(6,s.getCipherKey());
    }

    /** Test encryption 1 */
    @Test
    public void testEncryptShift1(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "hello";
	assertEquals("lipps",s.encrypt(word));
    }

    /** Test encryption 2*/
    @Test
    public void testEncryptShift2(){
	int cipherKey = 13;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "miranda";
	assertEquals("zvenaqn",s.encrypt(word));	
    }

    /** Test encryption 3 with upper cases*/
    @Test
    public void testEncryptShift3(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "HELLO";
	assertEquals("lipps",s.encrypt(word));
    }

    /** Test decryption 1*/
    @Test
    public void testDecryptShift1(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "lipps";
	assertEquals("hello",s.decrypt(word));
    }

    /** Test decryption 2*/
    @Test
    public void testDecryptShift2(){
	int cipherKey = 13;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "zVeNaQn";
	assertEquals("miranda",s.decrypt(word));
    }

    /** Test illegal argument exception word 1*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord1(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = " ";
	String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception word 2*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord2(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = null;
	String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception word 3*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord3(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "hello4";
	String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception word 4*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord4(){
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "57";
	String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception key 1*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey1(){
	int cipherKey = -6;
	ShiftCipher s = new ShiftCipher(cipherKey);
    }

    /** Test illegal argument exception key 2*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey2(){
	int cipherKey = 27;
	ShiftCipher s = new ShiftCipher(cipherKey);
    }

    /** Test illegal argument exception key 3*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey3(){
	int cipherKey = 17;
	ShiftCipher s = new ShiftCipher(cipherKey);
	int newCipherKey = -1;
	s.setCipherKey(newCipherKey);
    }

    /** Test illegal argument exception key 4*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey4(){
	int cipherKey = 17;
	ShiftCipher s = new ShiftCipher(cipherKey);
	int newCipherKey = 45;
	s.setCipherKey(newCipherKey);
    }
    
    /** Test generateKey 1*/
    @Test
    public void testGenerateKey(){
    	ShiftCipher a=new ShiftCipher();
    	a.generateKey();
    	a.encrypt("abc");
    	assertEquals(a.getCipherKey()<=25&&a.getCipherKey()>=0,true);

    }
    
}
