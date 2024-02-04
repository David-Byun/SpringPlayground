package spring.webflux.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class AESKeyManager {

    private SecretKey currentAESKey;
    private Date expirationDate;

    public AESKeyManager() {
        refreshAESKey();
    }

    public SecretKey getCurrentAESKey() {
        return currentAESKey;
    }

    public void refreshAESKey() {
        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256); // AES Key Size
            currentAESKey = keyGenerator.generateKey();

            // Set Expiration data for the day
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 1);
            expirationDate = calendar.getTime();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
