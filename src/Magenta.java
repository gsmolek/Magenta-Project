import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class Magenta {
    int[] sbox;
    public Magenta()
    {
        sbox=new int[256];
    }

    /**
     * function PI-Each two indexes are representing two PE vector in size of 2
     * @param array byte array of 16 bytes
     * @return vector of PE vectors
     */
    public byte[] PI (byte [] array)
    {
        byte[] piVector=new byte[15];
        for(int i=0;i<8;i++)
        {
            byte[] temp = PE(array[i],array[i+8]);
            piVector[i]=temp[0];
            piVector[i+1]=temp[1];
        }
        return piVector;
    }

    /**
     * returns a 8 bytes array of even indexes from a 16 byte array
     * @param array byte array of 16 bytes
     * @return evens array size 8 bytes
     */
    public byte[] bytesArrayToEvenByteArray(byte[] array)
    {
        byte[] even=new byte[8];
        for(int i=0,j=0;i<16;i=i+2,j++)
        {
            even[j]=array[i];
        }
        return even;
    }

    /**
     * returns a 8 bytes array of odd indexes from a 16 byte array
     * @param array byte array of 16 bytes
     * @return odds array size 8 bytes
     */
    public byte[] bytesArrayToOddByteArray(byte[] array)
    {
        byte[] even=new byte[8];
        for(int i=1,j=0;i<16;i=i+2,j++)
        {
            even[j]=array[i];
        }
        return even;
    }
    /**
     * function T - preforms four time PI function on input
     * @param array byte array of 16 bytes
     * @return 4XPI on input
     */
    public byte[] T (byte [] array)
    {
        byte [] new_array=array;
        for(int i=0;i<4;i++)
        {
            new_array=this.PI(new_array);
        }
        return new_array;
    }
    /**
     * function A(x,y),input two bytes
     * @param x one byte
     * @param y one byte
     * @return byte f(x xor f(y))
     */
    public byte a(byte x,byte y)
    {
        byte yRes=this.f(y);
        byte xorRes =(byte)(x^yRes);
        return xorRes;
    }

    /**
     * function f(x)
     * @param y one byte
     * @return byte f(x)
     */
    public byte f(byte y)
    {
        int new_y=Byte.toUnsignedInt(y);
        return (byte)(this.sbox[new_y]);
    }

    /**
     * function PE(x,y)
     * @param x one byte
     * @param y one byte
     * @return byte[] (A(x,y),A(y,x))
     */
    public byte[] PE(byte x,byte y)
    {
        byte[] peVec={0,0};
        peVec[0]=a(x,y);
        peVec[1]=a(y,x);
        return peVec;
    }

    /**
     * creates Sbox
     * @return int[] sbox
     */
    private int[] f()
    {
        byte[] initial={0,0,0,0,0,0,0,1};
        byte[] first={1,0,0,0,0,0,0,0};
        byte[] xor={0,1,1,0,0,1,0,1};
        byte[] on;
        int flag=0;
        int result;
        on=initial;
        sbox[0]=1;
        for(int i=1;i<256;i++)
        {
            if(on[0]==1)
                flag=1;
            on=Mathmatics.shiftleft(on,8);
            if(flag==1)
            {
                on=Mathmatics.twoByteArraysXOR(on,xor,8);
                flag=0;
            }
            result=Mathmatics.convertByteArrayToInteger(on,8);
            sbox[i]=result;
        }
        return sbox;
    }
}
