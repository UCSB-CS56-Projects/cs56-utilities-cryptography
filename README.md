cs56-utilities-Cryptography
===========================
Run the project by typing
```
 ant run
```
javadoc website: https://ucsb-cs56-m16.github.io/cs56_utilities_cryptography_javadoc/javadoc/index.html


javadoc repo: https://github.com/UCSB-CS56-M16/cs56_utilities_cryptography_javadoc

project history
===============
```
 YES | mastergberry | Implementation of 5 different types of string ciphers, both encryption and decryption.
```

Implementation of 5 different types of ciphers:
	       -Shift Cipher
	       -Affine Cipher
	       -Vigenere Cipher
	       -Bifid Cipher
	       -RSA Cipher

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

Bifid Cipher:
	Takes a String s as the key.
	Encrypts the plaintext by applying the Bifid Cipher to each character using a 25 letter 'key square' example of encryption using this 25 letter 'key square'
	   
	   1 2 3 4 5
	   
	1| p h q g m
	
	2| e a y l n
	
	3| o f d x k
	
	4| r c v s z
	
	5| w b u t i
	
	e.g.
	plaintext:   defend the east wall of the castle
	
	step 1: row  323223 512 2245 5222 33 512 424522
	        col  312153 421 1244 1244 12 421 224441
	        
	step 2:      32322 35122 24552 22335 12424 522 
	             31215 34211 24412 44124 21224 441 
	             
	step 3:      3232231215 3512234211 2455224412 2233544124 1242421224 522441
	
	step 4:      f f y h m  k h y c p  l i a s h  a d t r l  h c c h l  b l r

RSA Cipher:
	Two keys are automatically generated.
	Encrypts plaintext using an RSA Algorithm. First the public key is used for encryption then the private key is used for decryption.
	

Format of Input for GUI:

General:
	No spaces are to be used in the key or plaintext input fields. Capital letters can be used but will be translated to lowercase prior to any cryptographic operation being performed on them.

Shift Cipher:
	Key text field should contain a single integer within the range of 0 to 25 inclusive.

Affine Cipher:
	Key text field should contain two integers seperated by a single space, with the first integer being with the range of 0 to 25 inclusive and the second integer being greater than 0. If decrypting, the additional condition that the first integer must be a coprime with 26 also holds.

Vigenere Cipher:
	Key text field should contain a string of characters.
	
Bifid Cipher:
	The Plaintext can have uppercase or lowercase letters and spaces but numbers will result in an error. The key has to have at least one captial letter and no numbers. The key setter will parse out all non capital letters.

RSA Cipher:
	The plaintext can have any letters uppercase or lowercase, but no numbers or symbols. The key must be generated automatically because they must be different large prime numbers.


W16 final remarks:

-Most of ciphers have not been changed and have only been implemented differently.

-Now you can input more than one string with spaces in between.

-You can now use all the ciphers at once to encrypt one string.

-After each cipher, the program will write to a file called "output.txt"

The program itself takes in an input string and input key to encrypt or decrypt the input string. Each encryption cipher has already been defined and most of the work will be in the Cryptography.java file. There is a lot of opportunity to refactor some of the JButton codes. 


This is the picture included in welcome window and is a pictorial demonstration of general usage of application. 
![alt tag](https://github.com/keithlo/cs56-utilities-cryptography/blob/master/lib/GUI.jpg)


M16 final remarks:

1. The methods of encryption and decryption have not been changed.
2. Now, you can generate random keys.
3. A new GUI has been added, which have one big input text area and output text area, four seperate key input areas.
4. Now, you can look at the javadoc on website.
5. The keys and ciphered text will be saved to a file, default named output.txt.
6. You can decide where the output file will be saved
7. The methods of anonymous ActionListener class are factored out to a class called GUIActionMethid.
8. The lambda function has been used on implementing anonymous ActionListener.
9. You get another window for all ciphers. The class AllCipherGUI is for that window.
10. If you want to modify or add new function for each widget, for example, to edit error message, you may look at the GUIActionMethod class.
11. If you want to add new widget or modify the GUI, you may look at the CryptographyGUI class or AllCipherGUI classes.
12. If you want to edit or add algorithm for ciphers, you may look at the classes AffineCipher, ShiftCipher...



F16 final remarks:

- Bifid cipher has special cases for the letters I and J
- Added RSA Encryption window
- GUI has been updated with a new look and feel
- You can now choose your save folder from a pop up
- You an choose an input file to put into the plain text window
- A welcome window has been added
- Added a picture explanation in the README.md file
- Added a few comments in the code for clarity
- Added specific error messages
- Tip: Follow the methods that are called when a button in the GUI is pressed to get an understanding of how the classes interact with each other.