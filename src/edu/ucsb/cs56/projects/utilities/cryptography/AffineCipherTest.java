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
    public void test1(){
	int keyA = 3;
	int keyB = 7;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "hello";
	assertEquals("ctoox",a.encrypt(word));
    }

    /** Test encryption 2*/
    @Test
    public void test2(){
	int keyA = 1;
	int keyB = 3;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "hello";
	assertEquals("khoor",a.encrypt(word));
    }

    /** Test encryption 3*/
    @Test
    public void test3(){
	int keyA = 9;
	int keyB = 23;
	AffineCipher a = new AffineCipher(keyA, keyB);
	String word = "miranda";
	assertEquals("bruxkyx",a.encrypt(word));
    }

    /** Test encryption 4*/
    @Test
    public void test4(){
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
	int cipherKey = 4;
	ShiftCipher s = new ShiftCipher(cipherKey);
	String word = "hello4";
	String cipher = s.encrypt(word);
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

   
    
}
