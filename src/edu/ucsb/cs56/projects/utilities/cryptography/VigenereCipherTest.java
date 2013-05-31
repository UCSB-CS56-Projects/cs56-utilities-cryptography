package edu.ucsb.cs56.projects.utilities.cryptography;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
   A class to implement the Vigenere Cipher.
   @author Callum Steele and Miranda Aperghis
   @version Project cs56, S13
   @see VigenereCipher
 */

public class VigenereCipherTest {

    /**
       Test case for VigenereCipher.getCipherKey()
     */
    @Test
    public void test_getCipherKey() {
	VigenereCipher v = new VigenereCipher("test");
	assertEquals("test", v.getCipherKey());
    }
    
    /**
       Test case for VigenereCipher.setCipherKey()
     */
    @Test
    public void test_setCipherKey() {
	VigenereCipher v = new VigenereCipher("test");
	v.setCipherKey("word");
	assertEquals("word", v.getCipherKey());
    }

    /**
       Test illegal argument exception constructor.
    */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionConstructor1() {
	VigenereCipher v = new VigenereCipher("");
    }

    /**
       Test illegal argument exception constructor.
    */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionConstructor2() {
	VigenereCipher v = new VigenereCipher(null);
    }

    /**
       Test illegal argument exception for VigenereCipher.setCipherKey()
    */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionSetCipherKey1() {
	VigenereCipher v = new VigenereCipher("k");
	v.setCipherKey("");
    }

    /**
       Test illegal argument exception for VigenereCipher.setCipherKey()
    */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionSetCipherKey2() {
	VigenereCipher v = new VigenereCipher("k");
	v.setCipherKey(null);
    }

    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt1() {
	VigenereCipher v = new VigenereCipher("a");
	assertEquals("random", v.encrypt("random"));
    }
    
    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt2() {
	VigenereCipher v = new VigenereCipher("aaaaaaaaaaa");
	assertEquals("random", v.encrypt("random"));
    }

    /**
       Test case for VigenereCipher.encrypt()
    */
    @Test
    public void test_encrypt3() {
	VigenereCipher v= new VigenereCipher("bbbb");
	assertEquals("bbbbb", v.encrypt("aaaaa"));
    }

    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt4() {
	VigenereCipher v= new VigenereCipher("word");
	assertEquals("lzrljhvap", v.encrypt("plaintext"));
    }

    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt5() {
	VigenereCipher v= new VigenereCipher("extremelylongkey");
	assertEquals("titzr", v.encrypt("plain"));
    }

    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt6() {
	VigenereCipher v= new VigenereCipher("k");
	assertEquals("zvksxdohd", v.encrypt("plaintext"));
    }

    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt7() {
	VigenereCipher v= new VigenereCipher("hello");
	assertEquals("wpltbaiie", v.encrypt("PlAiNtExT"));
    }

    /**
       Test case for VigenereCipher.encrypt()
     */
    @Test
    public void test_encrypt8() {
	VigenereCipher v= new VigenereCipher("HeLlO");
	assertEquals("wpltbaiie", v.encrypt("PlAiNtExT"));
    }

    /**
       Test illegal argument exception for VigenereCipher.encrypt()
     */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionEncrypt1() {
	VigenereCipher v= new VigenereCipher("k");
        v.encrypt("456432");
    }

    /**
       Test illegal argument exception for VigenereCipher.encrypt()
     */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionEncrypt2() {
	VigenereCipher v= new VigenereCipher("k");
        v.encrypt("hello4");
    }

    /**
       Test illegal argument exception for VigenereCipher.encrypt()
     */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionEncrypt3() {
	VigenereCipher v= new VigenereCipher("k");
        v.encrypt("");
    }

    /**
       Test illegal argument exception for VigenereCipher.encrypt()
     */
    @Test(expected=IllegalArgumentException.class)
    public void test_IllegalArgumentExceptionEncrypt4() {
	VigenereCipher v= new VigenereCipher("k");
        v.encrypt(null);
    }
}
