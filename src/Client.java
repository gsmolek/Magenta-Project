import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    private Server server;
    private String id;
    private String password;
    private boolean usernameResult;
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
        usernameResult=server.isCorrect();
        if(usernameResult==true)
        {
            //File destination = new File("test.mp3");
            //FileOperations.toFile(data, destination);
        }
        else
        {
            System.out.println("wrong password or username entered");
        }
    }
}
