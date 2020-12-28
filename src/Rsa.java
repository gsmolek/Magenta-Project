import java.math.BigInteger;
import java.util.Random;
/*
 A client (for example browser) sends its public key to the server and requests for some data.
The server encrypts the data using client�s public key and sends the encrypted data.
Client receives this data and decrypts it.
 */
import java.util.Scanner;

public class Rsa {

    private BigInteger p,q,n,eoilerN,one=BigInteger.valueOf(1);
    private BigInteger e;//Encryption key
    private BigInteger d;//Decryption key
    private int bitlength = 64;
    private Random rand;

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public Rsa()
    {
        rand=new Random();
        p = BigInteger.probablePrime(bitlength, rand);//choose big large prime number randomly
        q = BigInteger.probablePrime(bitlength, rand);//choose big large prime number randomly
        n = p.multiply(q); // multiply p with q;
        eoilerN=p.subtract(one).multiply(q.subtract(one));//multiply (p-1)*(q-1)
        e = BigInteger.probablePrime(bitlength/2, rand);
        while (eoilerN.gcd(e).compareTo(one) > 0 && e.compareTo(eoilerN) < 0 ) // if(gcd(eoilerN,e)>1 && e<eoilerN
        { // we want 1<e<eiolerN ,we can write it gcd(e,eoilern)=1
            e.add(one);
        }
        d = e.modInverse(eoilerN); //d=e^-1mod(eiolerN) == 1mod(eoilerN)==d*mod(eoilerN)=1;
    }

    public Rsa(BigInteger e, BigInteger d, BigInteger n) {
        this.e = e;
        this.d = d;
        this.n = n;

    }

    public byte[] decrypt(byte[] cyperText) {
        return (new BigInteger(cyperText)).modPow(d, n).toByteArray(); //plainText=cypertext^d(mod(n)).
    }

    private static String bytesToString(byte[] cyperText) {
        String str = "";
        for (byte b : cyperText) {
            str += Byte.toString(b);
        }
        return str;
    }

    public byte[] encrypt(byte[] bytes) {
        return (new BigInteger(bytes)).modPow(e, n).toByteArray(); // cyperText=plainText(in bits)^e (mod(n))
    }





}
