package edu.ucsb.cs56.projects.utilities.cryptography;

import java.math.BigInteger;

/**
 * A class to implement a collection of utility methods for mathematical
 * operations.
 @author Miranda Aperghis
 @version Project CS56, S13, 06/06/2013
 * */
public class MathUtil {

    /**
       Find the multiplicative inverse of the two numbers
       @param a first integer
       @param b second integer
       @return multiplicative inverse (integer)
    */
    public static final int modInverse(int a){
	BigInteger bigR = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26));
	return bigR.intValue();
    }

    /**
       Find if two numbers are coprime, gcd(a,b)=1
       @param a first integer
       @param b second integer
       @return true if coprime, false if not coprime
    */
    public static final boolean coPrime(int a, int b){
	if(gcd(a,b)==1)return true;
	return false;
    }
    
    
    /** 
     * Method that calculates the Greatest Common Divisor (GCD) of two
     * positive integer numbers.
     * */
    public static final int gcd(int x1,int x2) {
	if(x1<0 || x2<0) {
	    throw new IllegalArgumentException("Cannot compute the GCD "+
					       "if one integer is negative.");
	}
	int a,b,g,z;
	
	if(x1>x2) {
	    a = x1;
	    b = x2;
	} else {
	    a = x2;
	    b = x1;
	}
	
	if(b==0) return 0;
	
	g = b;
	while (g!=0) {
	    z= a%g;
	    a = g;
	    g = z;
	}
	return a;
    }

}
