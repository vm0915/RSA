import java.math.BigInteger;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());
    private static String message = "London bridge is down.";

    public static void main(String[] args) {
        RSA rsa = new RSA(2048);
        log.info("Исходное сообщение: " + message);

        BigInteger rawMessage = new BigInteger(message.getBytes());
        BigInteger cipherText = rsa.encrypt(rawMessage);
        log.info("Зашифрованное сообщение: " + cipherText);

        rawMessage = rsa.decrypt(cipherText);
        String deciphredMessage = new String(rawMessage.toByteArray());
        log.info("Расшифрованное сообщение: " + deciphredMessage);

    }
}
