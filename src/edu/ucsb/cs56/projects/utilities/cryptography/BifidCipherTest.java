package edu.ucsb.cs56.projects.utilities.cryptography;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 A class to test the BifidCipher
 @author Ryan Peffers
 @version CS56, W15, 3/10/2015
 */
public class BifidCipherTest {


    /** Test constructor and getter */
    @Test
    public void testConstructorAndGetter(){
        String cipherKey = "HELLO";
        BifidCipher s = new BifidCipher(cipherKey);
        assertEquals("HELLO",s.getCipherKey());
    }

    /** Test setter and getter */
    @Test
    public void testSetter(){
        String cipherKey = "HELLO";
        BifidCipher s = new BifidCipher(cipherKey);
        s.setCipherKey("goodBYE");
        assertEquals("goodBYE",s.getCipherKey());
    }

    /** Test encryption 1 */
    @Test
    public void testEncryptShift1(){
        String cipherKey = "NICE";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "miranda";
        assertEquals("KQICDVH",s.encrypt(word));
    }

    /** Test encryption 2*/
    @Test
    public void testEncryptShift2(){
        String cipherKey = "HELLO";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "gucci mane";
        assertEquals("FCMLAWBPR",s.encrypt(word));
    }

    /** Test encryption 3 with lower cases in key*/
    @Test
    public void testEncryptShift3(){
        String cipherKey = "HeLlO";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "gucci mane";
        assertEquals("FDMOGVHNS",s.encrypt(word));
    }

    /** Test decryption 1*/
    @Test
    public void testDecryptShift1(){
        String cipherKey = "HELLO";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "FCMLAWBPR";
        assertEquals("GUCCIMANE",s.decrypt(word));
    }

    /** Test decryption 2*/
    @Test
    public void testDecryptShift2(){
        String cipherKey = "HeLlO";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "FDMOGVHNS";
        assertEquals("GUCCIMANE",s.decrypt(word));
    }

    /** Test illegal argument exception word 1*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord1(){
        String cipherKey = "Hello";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = " ";
        String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception word 2*/
    @Test(expected=NullPointerException.class)
    public void testIllegalArgumentExceptionWord2(){
        String cipherKey = "HeLoL";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = null;
        String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception word 3*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord3(){
        String cipherKey = "";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "hello4";
        String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception word 4*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWord4(){
        String cipherKey = "TEST";
        BifidCipher s = new BifidCipher(cipherKey);
        String word = "57";
        String cipher = s.encrypt(word);
    }

    /** Test illegal argument exception key 1*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey1(){
        String cipherKey = "643";
        BifidCipher s = new BifidCipher(cipherKey);
    }

    /** Test illegal argument exception key 2*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey2(){
        String cipherKey = "";
        BifidCipher s = new BifidCipher(cipherKey);
    }

    /** Test illegal argument exception key 3*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey3(){
        String cipherKey = "WEFEDF";
        BifidCipher s = new BifidCipher(cipherKey);
        String newCipherKey = "38ehcue3";
        s.setCipherKey(newCipherKey);
    }

    /** Test illegal argument exception key 4*/
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentExceptionKey4(){
        String cipherKey = "HELPME";
        BifidCipher s = new BifidCipher(cipherKey);
        String newCipherKey = "";
        s.setCipherKey(newCipherKey);
    }

    /** Test generateKey 1*/
    @Test
    public void testGenerateKey(){
    	BifidCipher a=new BifidCipher();
    	a.generateKey();
    	a.decrypt("abc");
    	assertEquals(10,a.getCipherKey().length());
    }
}
