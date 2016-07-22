package edu.ucsb.cs56.projects.utilities.cryptography;

import java.util.Arrays;

/**
    A class to implement the Bifid Cipher
    @author Ryan Peffers
    @version CS56, W15, 3/10/2015
*/

public class BifidCipher  {

    private String cipherKey;

    /**
     Default no-arg constructor.
    */
    public BifidCipher(){}

    /**
     One-arg constructor.
     @param cipherKey String that is used to encrypt the plaintext
    */
    public BifidCipher(String cipherKey){
        if(cipherKey.replaceAll("[^A-Z]", "").length() == 0) throw new IllegalArgumentException();
        if(cipherKey.matches(".*\\d.*")) throw new IllegalArgumentException();
        this.cipherKey = cipherKey;
    }

    /**
     Getter for the cipher key
     @return cipher key integer
    */
    public String getCipherKey(){
        return this.cipherKey;
    }

    /**
     Setter for the cipher key
     @param cipherKey String is used to encrypt the plaintext
    */
    public void setCipherKey(String cipherKey){
        if(cipherKey.replaceAll("[^A-Z]", "").length() == 0) throw new IllegalArgumentException();
        if(cipherKey.matches(".*\\d.*")) throw new IllegalArgumentException();
        this.cipherKey = cipherKey;
    }

    public String encrypt (String input) {
        if(input.matches(".*\\d.*")) throw new IllegalArgumentException();
        String formatted = input.toUpperCase().replaceAll("[^A-Z]", "");
        if(formatted.length() == 0) throw new IllegalArgumentException();

        char[] characters = formatted.toCharArray();
        char[][] Table5x5 = _makeTable5x5(this.cipherKey.replaceAll("[^A-Z]", ""));

        int[] rows = new int[characters.length];
        int[] cols = new int[characters.length];

        for(int i = 0; i < characters.length; i++) {
            for(int row = 0; row < 5; row++) {
                for(int column = 0; column < 5; column++) {
                    if(Table5x5[row][column] == characters[i]) {
                        rows[i] = row;
                        cols[i] = column;
                    }
                }
            }
        }

        int[] combined = new int[characters.length * 2];
        System.arraycopy(rows, 0, combined, 0, characters.length);
        System.arraycopy(cols, 0, combined, characters.length, characters.length);

        char[] values = new char[characters.length];
        for(int i = 0; i < combined.length; i=i+2) {
            values[i/2] = Table5x5[combined[i]][combined[i+1]];
        }

        return new String(values);
    }

    public String decrypt (String input) {
        if(input.matches(".*\\d.*")) throw new IllegalArgumentException();
        String formatted = input.toUpperCase().replaceAll("[^A-Z]", "");
        if(formatted.length() == 0) throw new IllegalArgumentException();

        char[] characters = formatted.toCharArray();
        char[][] Table5x5 = _makeTable5x5(this.cipherKey.replaceAll("[^A-Z]", ""));

        int[] combined = new int[characters.length * 2];

        int combinedIdx = 0;
        for(int i = 0; i < characters.length; i++) {
            for(int row = 0; row < 5; row++) {
                for(int column = 0; column < 5; column++) {
                    if(Table5x5[row][column] == characters[i]) {
                        combined[combinedIdx] = row;
                        combined[combinedIdx + 1] = column;
                        combinedIdx += 2;
                    }
                }
            }
        }

        char[] value = new char[characters.length];
        for(int i = 0; i < characters.length; i++) {
            value[i] = Table5x5[combined[i]][combined[i + characters.length]];
        }

        return new String(value);
    }

    private char[][] _makeTable5x5(String key) {
        char[][] Table5x5 = new char[5][5];
        char[] characters = key.toUpperCase().toCharArray();

        int[] index = new int[26];
        Arrays.fill(index, 0);

        // Populate with unique characters from cipherKey
        int row = 0;
        int column = 0;
        for(int i = 0; i < characters.length; i++) {
            if(characters[i] == 'I' && index[74 - 65] != 0) {
                characters[i] = 'J';
            } else if(characters[i] == 'J' && index[73 - 65] != 0) {
                characters[i] = 'I';
            }

            int value = (int) characters[i];

            if(index[value - 65] == 0) {
                Table5x5[row][column] = characters[i];
                index[value - 65] = 1;
            } else {
                continue;
            }

            if(column == 4) {
                row++;
                column = 0;
            } else {
                column++;
            }
        }

        // Populate with remaining characters
        for(int i = 65; i < 91; i++) {
            if(i == 74 && index[73 - 65] != 0) {
                continue;
            } else if(i == 73 && index[74 - 65] != 0) {
                continue;
            }

            if(index[i - 65] == 0) {
                Table5x5[row][column] = (char) i;
                index[i - 65] = 1;

                if(column == 4) {
                    row++;
                    column = 0;
                } else {
                    column++;
                }
            }
        }
        return Table5x5;
    }
    
	


  //Generate a random ten-letters String.
	public void generateKey() {
		final char[] letters=new char [] {'a','b','c','d','e','f','g',
			'h','i','j','k','l','m','n','o','p','q','r','s','t'
			,'u','v','w','x','y','z','A','B','C','D','E','F',
			'G','H','I','J','K','L','M','N','O','P','Q','R',
			'S','T','U','V','W','X','Y','Z'};
		cipherKey=Character.toString(letters[(int)(Math.random()*52)]);
	for(int i=0;i<9;++i){
		cipherKey+=letters[(int)(Math.random()*52)];
		}
	
	}


}
