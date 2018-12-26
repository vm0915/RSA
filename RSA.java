import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger n; //модуль
    private BigInteger d;//закрытая экспонента
    private BigInteger e; //открытая экспонента
    private BigInteger eulerFunction; //значение функции Эйлера m=(P-1)(Q-1)

    public RSA(int bitDepth) {      //bitDepth - разрядность случайных простых чисел в битах
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitDepth /2, 100, random); // Выбрать большие простые P и Q
        BigInteger q = new BigInteger(bitDepth /2, 100, random);
        n = p.multiply(q); // Вычислить модуль N=P*Q
        eulerFunction = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // вычислить значение функции Эйлера m=(P-1)(Q-1)
        e = new BigInteger("3"); // Выбирается целое число e (1<e<f(n)), взаимно простое со значением функции m
        while (eulerFunction.gcd(e).intValue() > 1) { // Пока наибольший общий делитель больше одного
            e = e.add(new BigInteger("2")); // е = е + 2
        }
        d = e.modInverse(eulerFunction); // Вычисляется число d, мультипликативно обратное к числу e по модулю m
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n); // Пара (e,n) - открытый ключ RSA
    } // c = E(message) = message^e mod n

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n); // Пара (d,n) - закрытый ключ RSA
    } // m = D(encryptedMessage) = encryptedMessage^d mod n


}