/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 *
 * @author Giang Minh
 */
public class EncryptionDecryptionAES {

    static Cipher cipher;

    public static String enDecrypt(String input, String salt, boolean isEncrypt) {
        if (salt.length() < 16) {
            salt += "e";
        } else if (salt.length() > 16) {
            salt = salt.substring(0, 16);
        }

        try {
            final String secretKey = salt;
            /*
            create key
            If we need to generate a new key use a KeyGenerator
            If we have existing plaintext key use a SecretKeyFactory
             */
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256); // block size is 128bits
//            SecretKey secretKey = generateSecretKeyFromEmail(salt);


            /*
            Cipher Info
            Algorithm : for the encryption of electronic data
            mode of operation : to avoid repeated blocks encrypt to the same values.
            padding: ensuring messages are the proper length necessary for certain ciphers
            mode/padding are not used with stream cyphers.
             */
            cipher = Cipher.getInstance("AES"); //SunJCE provider AES algorithm, mode(optional) and padding schema(optional)  

            if (isEncrypt) {
                // input must be plainText
//                return encrypt(salt, input);
//                System.out.println("encrypt secretKey: " + secretKey);
                return encrypt(input, secretKey);
            } else {
                // input must be encryptedText
//                return decrypt(input, secretKey);
//                System.out.println("strToDecrypt secretKey: " + input);
                return decrypt(input, secretKey);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptionDecryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(EncryptionDecryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EncryptionDecryptionAES.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(final String strToDecrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private static final Random RANDOM = new SecureRandom();
    
    public static String getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
