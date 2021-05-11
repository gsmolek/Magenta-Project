import java.util.ArrayList;
import java.util.List;

/**
 * @author Gilad Molek
 * @version 2.4
 * @apiNote supports working with vectors
 * @// TODO: 11/12/2020 merge with magenta class
 * @// TODO: 11/12/2020 javadocs
 * @// TODO: 11/12/2020 add more testing 
 */
public class TestingController {
    TestingController()
    {
        byte[] arr = {1, 0, 0, 1, 1, 0, 0, 1};
        byte[] array=new byte[16];
        String array3=new String();
        int[] sbox;
        byte check = 1;
        Magenta m = new Magenta();

        byte[] a=m.T(array);
        byte first=1;
        for(int i=0;i<16;i++)
        {
            array[i]=first;
            first=(byte)((byte)(first)+1);
        }
        first=1;
        for(int i=0;i<111;i++)
        {
            array3=array3+Integer.toBinaryString(first);
            first=(byte)((byte)(first)+1);
        }
        byte[] array2={(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162,(byte)87,(byte)181,(byte)49,(byte)66
                ,(byte)125,(byte)140,(byte)218,(byte)117};
        byte[] key={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18};
        byte[] key128={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)123,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18};
        byte[] key192={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)123,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162};
        byte[] key256={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)123,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162,(byte)125,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18};
        testings t=new testings();
        t.testingFfunction((byte)20);
        t.testingFfunction((byte)21);
        t.testingFfunction((byte)110);
        t.testingFfunction((byte)131);
        t.testingAfunction((byte)20,(byte)21);
        t.testingPEfunction((byte)20,(byte)21);
        t.testingPIfunction(array);
        //t.testingAfunction((byte)9,(byte)1);
        t.testingTfunction(array2);
        t.testingCfunction(array,10);
        t.testingEfunction(array,10);
        t.testingFeistelfunction(array,key);
        t.testingSplitFunction(key128);
        t.testingSplitFunction(key192);
        t.testingSplitFunction(key256);
        t.testingEncryption(array,key128);
        t.testingEncryption(array,key192);
        t.testingEncryption(array,key256);
        t.testingDecrypiton(t.testingEncryption(array,key256),key256);
        System.out.println(array3.length());
        System.out.println("testing");
        //this.plainTextToByteArray(array3,128);
        System.out.println("testing partition");
        System.out.println("plainText:");
        System.out.print(array3);
        byte[][] res=m.plainTextToByteArray(array3,128);
        ByteVector.printByteByteArray(res);
        res=m.deletePadding(array3);
        ByteVector.printByteByteArray(res);
        String s=m.byteMatrixToStringOfBytes(res);
        System.out.println("====================================");
        System.out.println(s);
        System.out.println("====================================");
        System.out.println("testing partition END");
        byte[] enc=m.encryption(res[0],key128);
        m.keyGenerator();
        //byte[] b={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        //byte[] ar=t.testingEncryption(b,key256);
        //t.testingEncryption(res[0],key256);
        /////////enc=m.decryption(enc,key128);
        /////////ByteVector.printByteArrayAsInt(enc,16);

    }
    public byte[][] plainTextToByteArray(String plainText,int chunkSize)
    {
        int counter=0;
        String s;
        byte[][] result;


        List<String> t=new ArrayList<>();

        for(int i=0;i<plainText.length();i+=8)
        {
            t.add(plainText.substring(i,Math.min(plainText.length(),i+8)));
        }
        int numberOfRows=Mathmatics.divideWithRoundUp(plainText.length(),128);
        result=new byte[numberOfRows][16];
        byte[] temp=new byte[t.size()];
        for(int i=0 ;i<t.size();i++)
        {
            int num=Mathmatics.convertStringToInteger(t.get(i));
            temp[i]=(byte)num;
        }
        int row=0,col=0;
        for(int i=0 ;i<t.size();i++)
        {
            result[row][col]=temp[i];
            col++;
            if(col==16)
            {
                col=0;
                row++;
            }
        }
        System.out.println("result after partition");
        System.out.println();
        ByteVector.printByteByteArray(result);
        System.out.println();
        ByteVector.printByteArrayAsBits(temp);
        //System.out.println(temp);
        System.out.println();
        System.out.println(t);

        return null;
    }

}
