package edu.ucsb.cs56.projects.utilities.cryptography;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
   A class to test AffineCipher.
   @author Miranda Aperghis
   @author Callum Steele
   @version Project, CS56, S13, 06/06/2013
   @see MathUtil
*/
public class MathUtilTest{

    /** Test coprime */
    @Test
    public void testCoPrime1(){
	int b = 2;
	int c = 4;
	MathUtil m = new MathUtil();
	assertEquals(false,m.coPrime(b,c));
    }

    /** Test coprime */
    @Test
    public void testCoPrime2(){
	int b = 2;
	int c = 3;
	MathUtil m = new MathUtil();
	assertEquals(true,m.coPrime(b,c));
    }

    /** Test multiplicative inverse */
    @Test
    public void testModInverse(){
	int a = 5;
	MathUtil m = new MathUtil();
	assertEquals(21,m.modInverse(a));
    }

    /** Test multiplicative inverse 2 */
    @Test
    public void testModInverse2(){
	int a = 9;
	MathUtil m = new MathUtil();
	assertEquals(3,m.modInverse(a));
    }

}
