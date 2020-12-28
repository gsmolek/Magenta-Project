import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
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
    private Rsa r;
    private byte[] key;
    private byte[][] blocksOfData;
    byte[][] encryptedBlocksOfData;
    private byte[][] sha1_magenta;
    private byte[] sha1_key;
    private boolean flag=true;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;


    public boolean isFlag() {
        return flag;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public byte[][] getEncryptedBlocksOfData() {
        byte[][] temp=new byte[encryptedBlocksOfData.length][16];
        temp=encryptedBlocksOfData;
        return temp;
    }
    public byte[] getKey() {
        return key;
    }
    public byte[][] getSha1_magenta() {
        return sha1_magenta;
    }

    public byte[] getSha1_key() {
        return sha1_key;
    }

    public byte[] getRsa_key() {
        return rsa_key;
    }

    public Rsa getR() {
        return r;
    }

    private byte[] rsa_key;
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
            ByteVector.printByteArrayAsInt(key,key.length);
            System.out.println("len"+data.length);
            this.blocksOfData=magenta.ByteArrayToByteMatrix(data,128);
            encryptedBlocksOfData=new byte[blocksOfData.length][16];
            sha1_magenta=new byte[blocksOfData.length][16];
            r=new Rsa();

            this.e=r.getE();
            this.d=r.getD();
            this.n=r.getN();

            rsa_key=r.encrypt(key);
            sha1_key=new Sha1().getDigestOfBytes(key);

            for(int i=0;i< blocksOfData.length;i++)
            {
                encryptedBlocksOfData[i]=magenta.encryption(blocksOfData[i],key);
                sha1_magenta[i]=new Sha1().getDigestOfBytes(encryptedBlocksOfData[i]);
            }

            System.out.println("key rsa");
            //ByteVector.printByteArrayAsInt(blocksOfData[0],16);
            ByteVector.printByteArrayAsInt(rsa_key,rsa_key.length);
            flag=false;
            System.out.println("finish server");

        }
    }
    private void initializeHashMap()
    {
        users.put("sharon", "0aed7afd0159e99bfef41e006370ed338deb3802");
        users.put("gilad", "cb1093a06eac78228f8e7bc814e1add628b94bf7");
        users.put("linoy", "bc06fc30219fd7b0007ccc618795178a38319373");
    }
    public void printEncry()
    {
        ByteVector.printByteByteArray(this.encryptedBlocksOfData);
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
