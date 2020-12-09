import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class Magenta {
    private int[] sbox;

    public Magenta()
    {
        sbox=new int[256];
        this.f();
    }

    public byte[] C(byte [] array,int j)
    {
        if(j==1)
            return this.T(array);
        return this.T(this.attachTwoArrays(Mathmatics.twoByteArrayOfBitsXOR(this.split16LengthArray(array)[0],
                this.bytesArrayToEvenByteArray(C(array,j-1)),8),
                Mathmatics.twoByteArrayOfBitsXOR(this.split16LengthArray(array)[1],
                        this.bytesArrayToOddByteArray(C(array,j-1)),8)));
    }
    public byte[][] split16LengthArray(byte[] array)
    {
        byte[][] res;
        byte[] array1=new byte[8];
        byte[] array2=new byte[8];
        for(int i=0;i<8;i++)
        {
            array1[i]=array[i];
            array2[i]=array[i+8];
        }
        res=new byte[][] {array1,array2};
        return res;
    }
    public byte[] attachTwoArrays(byte[] array1,byte[] array2)
    {
        byte [] array_combined=new byte[16];
        for(int i=0;i<8;i++)
        {
            array_combined[i]=array1[i];
            array_combined[i+8]=array2[i];
        }
        return array_combined;
    }
    /**
     * function PI-Each two indexes are representing two PE vector in size of 2
     * @param array byte array of 16 bytes
     * @return vector of PE vectors
     */
    public byte[] PI (byte [] array)
    {
        byte[] piVector=new byte[16];
        for(int i=0,j=0;i<16;i+=2,j+=1)
        {
            byte[] temp =new byte[2];
            temp=PE(array[j],array[j+8]);
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
        return this.f(xorRes);
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
     * @return byte[2] (A(x,y),A(y,x))
     */
    public byte[] PE(byte x,byte y)
    {
        byte[] peVec=new byte[2];
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
                Mathmatics.twoByteArrayOfBitsXOR(on,xor,8);
                on=Mathmatics.twoByteArrayOfBitsXOR(on,xor,8);
                flag=0;
            }
            result=Mathmatics.convertByteArrayToInteger(on,8);
            sbox[i]=result;
        }
        sbox[255]=0;
        return sbox;
    }

    /**
     * return the array
     * @return int[]
     */
    public int[] getSbox() {
        return sbox;
    }

    /**
     * prints this magenta Sbox
     */
    public void printSbox()
    {
        System.out.print("[");
        for(int i=0;i<256;i++)
        {
            System.out.print(sbox[i]);
            if(i!=255)
                System.out.print(",");
            if(i>7 && (i)%8==0)
                System.out.print("\n");
        }
        System.out.print("]\n");
    }
}
