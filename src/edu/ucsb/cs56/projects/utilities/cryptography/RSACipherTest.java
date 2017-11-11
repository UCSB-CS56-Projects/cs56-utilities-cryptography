import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;
import java.lang.Exception;

/**
 *    A class to Test the RSA Cipher class
 *    @author Arielle Robles
 *    @author Kaushik Mahorker
 *    @version CS56 F17 UCSB
 *
 */

public class RSACipherTest  {

    public static void main(String args[])  throws NoSuchAlgorithmException, InvalidKeySpecException, Exception{
      RSACipher cipher1 = new RSACipher();
      cipher1.generateKey();
      System.out.println("PUBLIC KEY");
      System.out.println(cipher1.getPublicKey());
      System.out.println("PRIVATE KEY");
      System.out.println(cipher1.getPrivateKey());

      String message = "This message is not encrypted";
      System.out.println("Going to encrypt :" + message);
      String encrypted = cipher1.encrypt(message);
      System.out.println(encrypted);

      System.out.println("Going to decrypt now:");
      String decrypted = cipher1.decrypt(encrypted);
      System.out.println(decrypted);
  }

}
