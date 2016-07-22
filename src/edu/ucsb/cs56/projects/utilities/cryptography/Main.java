package edu.ucsb.cs56.projects.utilities.cryptography;

import static org.junit.Assert.assertEquals;

public class Main {
	public static void main(){
		VigenereCipher a=new VigenereCipher();
    	a.generateKey();
    	a.encrypt("abc");
    	assertEquals(10,a.getCipherKey().length());
		
		
		
	}
}
