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
      Encrypts the plaintext by applying the Shift Cipher to each character in the plaintext using the associated letter in the key as the key.

      e.g.
      plaintext = "random"
      key = "abc"
      
      Line up:	random
      	   	abcabc

      Giving:   rbpdpo

Format of Input for GUI:

General:
	No spaces are to be used in the key or plaintext input fields. Capital letters can be used but will be translated to lowercase prior to any cryptographic operation being performed on them.

Shift Cipher:
	Key text field should contain a single integer within the range of 0 to 25 inclusive.

Affine Cipher:
	Key text field should contain two integers seperated by a single space, with the first integer being with the range of 0 to 25 inclusive and the second integer being greater than 0. If decrypting, the additional condition that the first integer must not be a coprime with 26 also holds.

Vigenere Cipher:
	Key text field should contain a string of characters.