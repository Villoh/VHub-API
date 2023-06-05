import dev.mikel_villota.vlrhub_api.utils.EncryptionUtils;

import javax.crypto.SecretKey;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        SecretKey secretKey = EncryptionUtils.generateRandomSecretKey(); // Obtén la SecretKey
        byte[] keyBytes = secretKey.getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println(base64Key);
    }
}
