package dev.mikel_villota.vlrhub_api.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Encryption Utils. This class implements methods to encrypt a String with AES.
 * @author Mikel Villota
 */
public class EncryptionUtils {
    private static final String AES_ALGORITHM = "AES";

    /**
     *  Generates a random SecretKey with AES from a seed.
     * @param seed ({@link String}) used to generate the random SecretKey
     * @return {@link SecretKey}
     */
    public static SecretKey generateRandomSecretKey(String seed) {
        SecretKey secretKey = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed.getBytes());
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256,secureRandom);
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error generating random secretKey: " + e.getMessage());
        }
        return secretKey;
    }

    /**
     * Encrypts a String with AES.
     * @param plaintext ({@link String}) text to be encrypted
     * @param seed ({@link String}) used to generate the random SecretKey
     * @return {@link String}
     */
    public static String encryptWithAES(String plaintext, String seed) {
        byte[] ciphertext = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
            cipher.init(Cipher.ENCRYPT_MODE, generateRandomSecretKey(seed), iv);
            ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Error encrypting plaintext: " + e.getMessage());
        }
            return Base64.getEncoder().encodeToString(ciphertext);
    }

    /**
     * Decrypts a String with AES.
     * @param ciphertext ({@link String}) text to be decrypted
     * @param seed ({@link String}) used to generate the random SecretKey
     * @return {@link String}
     */
    public static String decryptWithAES(String ciphertext, String seed) {
        String plaintext = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
            cipher.init(Cipher.DECRYPT_MODE, generateRandomSecretKey(seed), iv);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
            plaintext = new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error decrypting ciphertext : " + e.getMessage());
        }
        return plaintext;
    }
}
