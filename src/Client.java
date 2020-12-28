import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    private Server server;
    private String id;
    private String password;
    private boolean usernameResult;
    byte[][] sha1_file;
    byte[][] magenta_data;
    byte[][] original_data;
    byte[][] integrity_sha1_data;
    public Client()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter your id: ");
        id=scan.nextLine();
        System.out.println("Please enter your password: ");
        password=scan.nextLine();
        String digest = new Sha1().getDigestOfString(password.getBytes()); //bytes that sha encrypts for password.
        System.out.println("this are the bytes that sha encrypted for your password: " + digest);
        server=new Server(id,digest);
        while(server.isFlag())
        {

        }
        usernameResult=server.isCorrect();
        if(usernameResult==true)
        {
            Rsa rsa=new Rsa();
            Magenta magenta=new Magenta();
            byte[] Sha1_key=server.getSha1_key();
            byte[] rsa_key=server.getRsa_key();
            byte[] original_key=rsa.decrypt(rsa_key);
            byte[] integrity_sha1_key=new Sha1().getDigestOfBytes(original_key);
            boolean isKey=ByteVector.compareTwoBytesArray(integrity_sha1_key,integrity_sha1_key);

            if(isKey) {
               sha1_file = server.getSha1_magenta();
               magenta_data=server.encryptedBlocksOfData;
               //magenta_data = server.getEncryptedBlocksOfData();
               original_data = new byte[magenta_data.length][16];
               integrity_sha1_data = new byte[magenta_data.length][16];
                ByteVector.printByteByteArray(original_data);
                System.out.println("magenta data: "+magenta_data.length);

                for (int i = 0; i < magenta_data.length; i++) {
                    original_data[i] = magenta.decryption(magenta_data[i], original_key);
                    integrity_sha1_data[i] = new Sha1().getDigestOfBytes(original_data[i]);
                }
                boolean isData=ByteVector.compareTwoBytesMatrixs(integrity_sha1_data,sha1_file);
                if(isData)
                {
                    original_data=magenta.deletePaddingByteMatrix(original_data);
                    ByteVector.printByteByteArray(original_data);
                    /*
                    File destination = new File("test.mp3");
                    FileOperations.toFile(original_data, destination);
                    */

                }

            }

        }
        else
        {
            System.out.println("wrong password or username entered");
        }
    }
}
