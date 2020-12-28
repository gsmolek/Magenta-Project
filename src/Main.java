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
        /*
        byte[][] sha1_file;
        byte[][] magenta_data;
        byte[][] original_data;
        byte[][] integrity_sha1_data;
        byte[][] decrypted;
         */
        //TestingController testing=new TestingController();

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
            Rsa rsa=new Rsa(e,d,n);

            key_original=rsa.decrypt(key_rsa);
            key_original_sha1=new Sha1().getDigestOfBytes(key_original);


            while(ByteVector.compareTwoBytesArray(key_sha1,key_original_sha1)==false)
            {
                key_original=rsa.decrypt(key_rsa);
                key_original_sha1=new Sha1().getDigestOfBytes(key_original);
            }
            ByteVector.printByteArrayAsInt(key_original_sha1,16);
            ByteVector.printByteArrayAsInt(key_sha1,16);
            if(ByteVector.compareTwoBytesArray(key_sha1,key_original_sha1))
            {
                magenta_file_integrity=new byte[magenta_file.length][16];
                for(int i=0;i<magenta_file_integrity.length;i++)
                {
                    magenta_file_integrity[i]=new Sha1().getDigestOfBytes(magenta_file[i]);
                }
                if(ByteVector.compareTwoBytesMatrixs(magenta_file_integrity,magenta_sha1))
                {
                    encrypted=new byte[magenta_file.length][16];
                    for(int i=0;i< encrypted.length;i++)
                    {
                        Magenta magenta=new Magenta();
                        encrypted[i]=magenta.decryption(magenta_file[i],key_original);
                    }
                    byte[] array_file=ByteVector.fromMatrixToArray(encrypted);

                    Random random=new Random();
                    int number=random.nextInt(1000000);

                    File destination = new File("music "+number+".mp3");
                    FileOperations.toFile(array_file, destination);
                }

            }
            /*
            Rsa rsa=server.getR();
            Magenta magenta=new Magenta();
            byte[] original_key=server.getR().decrypt(server.getRsa_key());
            byte[] Sha1_key=server.getSha1_key();
            byte[] rsa_key=server.getRsa_key();
            byte[] integrityCheck= server.getSha1_key();
            byte[] integrity_sha1_key=new Sha1().getDigestOfBytes(original_key);
            boolean isKey=ByteVector.compareTwoBytesArray(integrity_sha1_key,integrityCheck);
            sha1_file = server.getSha1_magenta();
            magenta_data=server.getEncryptedBlocksOfData();
            byte[] integrityCheckFile=new Sha1().getDigestOfBytes(ByteVector.fromMatrixToArray(magenta_data));
            original_data = new byte[magenta_data.length][16];
            boolean isFile=ByteVector.compareTwoBytesArray(integrity_sha1_data,ByteVector.fromMatrixToArray(sha1_file));
            ByteVector.printByteArrayAsInt(rsa_key,rsa_key.length);
            ByteVector.printByteArrayAsInt(original_key,16);
            */
            if(isKey) {
                /*
                System.out.println("magenta data: "+magenta_data.length);
                magenta=new Magenta(magenta_data,original_key);
                //magenta.decrypt_operation();
                decrypted=new byte[magenta_data.length][16];
                for(int k=0;k<magenta_data.length;k++)
                    decrypted[k]=magenta.decryption(magenta_data[k],original_key);
                ByteVector.printByteArrayAsInt(decrypted[magenta_data.length-1],16);
                byte[] j=ByteVector.fromMatrixToArray(decrypted);
                File destination = new File("music.mp3");
                FileOperations.toFile(j, destination);
                ByteVector.printByteArrayAsInt(sha1_file[0],16);
                ByteVector.printByteArrayAsInt(integrity_sha1_data,16);
                */
            }

        }
        else
        {
            System.out.println("wrong password or username entered");
        }

    }
}