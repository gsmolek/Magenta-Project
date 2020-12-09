import com.sun.jdi.ByteValue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        byte[] arr = {1, 0, 0, 1, 1, 0, 0, 1};
        byte[] array=new byte[16];
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
        byte[] array2={(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162,(byte)87,(byte)181,(byte)49,(byte)66
                ,(byte)125,(byte)140,(byte)218,(byte)117};
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
    }
}