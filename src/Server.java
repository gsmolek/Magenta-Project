import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Server {
    private HashMap<String,String> users;
    private boolean correct;
    private String username;
    private String password;
    private byte[] bytesFromFile = null;
    private byte[] test = null;
    private byte[] data;
    private Magenta magenta;
    private byte[] key;
    private byte[][] blocksOfData;
    private byte[][] encryptedBlocksOfData;
    public Server(String username,String password)
    {
        users=new HashMap<String, String>();
        this.initializeHashMap();
        this.correct=this.check(username,password);
        if(correct) {
            magenta=new Magenta();
            File f = new File("src/test.mp3");

            try {
                bytesFromFile = FileOperations.getBytes(f);
                System.out.println("bytes length from file is : " + bytesFromFile.length);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
            data = bytesFromFile;
            this.key=magenta.keyGenerator();
            System.out.println("len"+data.length);
            this.blocksOfData=magenta.ByteArrayToByteMatrix(data,128);
            encryptedBlocksOfData=new byte[blocksOfData.length][16];
            for(int i=0;i< blocksOfData.length;i++)
            {
                encryptedBlocksOfData[i]=magenta.encryption(blocksOfData[i],key);
            }
            //byte[] data=bytesFromFile;
            //ByteVector.printByteArrayAsInt(data,data.length);
            //File destination=new File("test.mp3");
            //FileOperations.toFile(data,destination);
        }
    }
    private void initializeHashMap()
    {
        users.put("sharon", "0aed7afd0159e99bfef41e006370ed338deb3802");
        users.put("gilad", "cb1093a06eac78228f8e7bc814e1add628b94bf7");
        users.put("linoy", "bc06fc30219fd7b0007ccc618795178a38319373");
    }
    private boolean check(String name, String pass) {
        String temp = users.get(name);
        if(temp!= null)
            if (temp.equals(pass))
                return true;
        return false;
    }
    public boolean isCorrect() {
        return correct;
    }
}
