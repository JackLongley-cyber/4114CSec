package doctorsSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
 
public class encyrption {
    //written with help from (https://www.codejava.net/coding/file-encryption-and-decryption-simple-example)
	private static String key = "VmYq3t6w9z$C&F)J";
	private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    
    public static void encrypt(File file) throws Exception {
        //passes in the mode, secret key and files to the "doEncyrption" subRoutine
    	doEncyrption(Cipher.ENCRYPT_MODE, file);
    }
 
    public static void decrypt(File file) throws Exception {
    	//passes in the mode, secret key and files to the "doEncyrption" subRoutine
        doEncyrption(Cipher.DECRYPT_MODE, file);
    }
 
    private static void doEncyrption(int cipherMode, File file) throws Exception {
        try {
            //encodes 'key' into 'secretKey' changing the text of the string to bytes and uses AES Algorithm
        	Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            //sets the cipher to AES
        	Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            //initialises the cipher with the mode(either decrypt or encrypt) and the new secret key
        	cipher.init(cipherMode, secretKey);
             
        	//FileInputStream obtains the raw bytes from the file
            FileInputStream inputStream = new FileInputStream(file);
            byte[] inputBytes = new byte[(int) file.length()];
            //reads the raw bytes from 'inputBytes'
            inputStream.read(inputBytes);
             
            byte[] outputBytes = cipher.doFinal(inputBytes);
            
            //writes to the file
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(outputBytes);
             
            inputStream.close();
            outputStream.close();
             
        } 
        catch (Exception ex) {
            throw new Exception("Error encrypting/decrypting file", ex);
        }
    }
}
