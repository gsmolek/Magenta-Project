import com.sun.jdi.ByteValue;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.spec.RSAMultiPrimePrivateCrtKeySpec;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Server server;
        String id;
        String password;
        boolean usernameResult;
        byte[] key_sha1;
        byte[] key_rsa;
        byte[][] magenta_file;
        byte[][] magenta_file_integrity;
        byte[][] magenta_sha1;
        byte[] key_original;
        byte[] key_original_sha1;
        byte[][] encrypted;
        boolean isKey=false;
        boolean isFile;
        BigInteger n;
        BigInteger e;
        BigInteger d;
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter your id: ");
        id=scan.nextLine();
        System.out.println("Please enter your password: ");
        password=scan.nextLine();
        String digest = new Sha1().getDigestOfString(password.getBytes()); //bytes that sha encrypts for password.
        System.out.println("this are the bytes that sha encrypted for your password: " + digest);
        server=new Server(id,digest);
        usernameResult=server.isCorrect();
        if(usernameResult==true)
        {
            key_sha1=server.getSha1_key();
            key_rsa= server.getRsa_key();
            magenta_file= server.getEncryptedBlocksOfData();
            magenta_sha1=server.getSha1_magenta();
            n=server.getN();
            d=server.getD();
            e=server.getE();
            Rsa rsa=new Rsa(server.getE(),server.getD(),server.getN());

            key_original=rsa.decrypt(key_rsa);
            key_original_sha1=new Sha1().getDigestOfBytes(key_original);


            ByteVector.printByteArrayAsInt(key_original_sha1,16);
            ByteVector.printByteArrayAsInt(key_sha1,16);
            if(ByteVector.compareTwoBytesArray(key_sha1,key_original_sha1))
            {
                magenta_file_integrity=new byte[magenta_file.length][20];
                for(int i=0;i<magenta_file_integrity.length;i++)
                {
                    magenta_file_integrity[i]=new Sha1().getDigestOfBytes(magenta_file[i]);
                }
                if(ByteVector.compareTwoBytesMatrixs(magenta_file_integrity,magenta_sha1))
                {
                    encrypted=new byte[magenta_file.length][16];
                    Magenta magenta=new Magenta();
                    for(int i=0;i< encrypted.length;i++)
                    {
                        encrypted[i]=magenta.decryption(magenta_file[i],key_original);
                    }
                    byte[] array_file=ByteVector.fromMatrixToArray(encrypted);

                    Random random=new Random();
                    int number=random.nextInt(1000000);

                    File destination = new File("music "+number+".mp3");
                    FileOperations.toFile(array_file, destination);


                }

            }
            else {

            }

        }
        else
        {
            System.out.println("wrong password or username entered");
        }

    }
}