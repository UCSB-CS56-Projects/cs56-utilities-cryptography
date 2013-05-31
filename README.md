cs56-utilities-Cryptography
===========================

Implementation of 3 different types of ciphers:
	       -Shift Cipher
	       -Affine Cipher
	       -Vigenere Cipher

Shift Cipher:
      Takes an integer a as the key.
      Encrypts the plaintext by shifting the characters along by adding the key to each of the characters and taking modulus 26

Affine Cipher:
       Takes 2 integers (a and b) as the key.
       Encrypts the plaintext by multiplying the character values by a and then adding b, taking modulus 26.

Vigenere Cipher:
      Takes a String s as the key.
      Encrypts the plaintext by applying a the Shift Cipher to each character in the plaintext using the associated letter in the key as the key.

      e.g.
      plaintext = "random"
      key = "abc"
      
      Line up:	random
      	   	abcabc

      Giving:   rbpdpo