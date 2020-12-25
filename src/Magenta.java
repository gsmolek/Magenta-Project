import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * @author Gilad Molek
 * @version 3.0
 * @apiNote Magenta cypher
 */
public class Magenta {
    private int[] sbox;

    /**
     * constructor creates S-BOX.
     */
    public Magenta()
    {
        sbox=new int[256];
        this.f();
    }

    /**
     * Magenta algorithm decryption function
     * @param message 128 byte array consists the encrypted message to decrypt
     * @param key 128/192/256 bytes key,the same as used in encryption
     * @return byte array with the decrypted message
     */
    public byte[] decryption(byte[] message,byte[] key)
    {
        return this.V(this.encryption(this.V(message),key));
    }
    public byte[] V(byte[] message)
    {
        byte[][] array=this.split16LengthArray(message);
        return attachTwoArrays(array[1],array[0]);
    }
    /**
     * Encryption 128 bits block with MAGENTA cipher
     * @param message 128 bits block message to encrypt
     * @param key 128/192/256 bits key for the feistel rounds
     * @return for successful encryption 128 bits of encrypted plaintext
     * as a byte array
     * for an unsuccessful encryption null
     */
    public byte[] encryption(byte[] message,byte[] key)
    {
        int len= key.length;
        if(len==16)
        {
            byte[][] new_key=this.split16LengthArray(key);
            byte[] k1=new_key[0];
            byte[] k2=new_key[1];
            return this.
            feistelRound
                    (feistelRound
                            (feistelRound
                                    (feistelRound
                                            (feistelRound
                                                    (feistelRound(message,k1)
                                                            ,k1)
                                                    ,k2)
                                            ,k2)
                                    ,k1)
                            ,k1);
        }
        else if(len==24)
        {
            byte[][] new_key=this.splitArray(key);
            byte[] k1=new_key[0];
            byte[] k2=new_key[1];
            byte[] k3=new_key[2];
            return this.
            feistelRound(
                    feistelRound(
                            feistelRound(
                                    feistelRound(
                                            feistelRound(
                                                    feistelRound(message,k1),
                                                    k2),
                                            k3),
                                    k3),
                            k2),
                    k1);
        }
        else if(len==32)
        {
            byte[][] new_key=this.splitArray(key);
            byte[] k1=new_key[0];
            byte[] k2=new_key[1];
            byte[] k3=new_key[2];
            byte[] k4=new_key[3];
            return this.
            feistelRound(
                    feistelRound(
                            feistelRound(
                                    feistelRound(
                                            feistelRound(
                                                    feistelRound(
                                                            feistelRound(
                                                                    feistelRound(message,k1)
                                                                    ,k2)
                                                            ,k3)
                                                    ,k4)
                                            ,k4)
                                    ,k3)
                            ,k2)
                    ,k1);
        }
        return null;
    }
    /**
     * Feistel round function-each run is one round of feistel
     * @param arrayX represent 128 bits plaintext in byte[16]
     * @param arrayY represent Key number i in byte[8]
     * @return result the returned bits of one round in byte[16]
     */
    public byte[] feistelRound(byte[] arrayX,byte[] arrayY)
    {
        byte[][] splitedArray=this.split16LengthArray(arrayX);
        byte[] array_x0_x7_xor_E3=new byte[16];
        byte[] array_x8_x15_y0_y7=new byte[16];
        byte[] arrayE3=new byte[8];
        byte[] array_x0_x7=new byte[8];
        byte[] array_x8_x15=new byte[8];
        array_x0_x7=splitedArray[0];
        array_x8_x15=splitedArray[1];
        array_x8_x15_y0_y7=attachTwoArrays(array_x8_x15,arrayY);
        arrayE3=E(array_x8_x15_y0_y7,3);
        array_x0_x7_xor_E3=Mathmatics.twoByteArrayOfBitsXOR(array_x0_x7,arrayE3,8);
        return attachTwoArrays(array_x8_x15,array_x0_x7_xor_E3);
    }
    /**
     * implementation of the E function in magenta, return an array
     * consists the even indexes from the array returned from the
     * C function
     * @param array 16 bytes array
     * @param r the number or iterations
     * @return  AN 8 bytes array of even indexes from the array returned from
     * C-function
     */
    public byte[] E(byte[] array,int r)
    {
        return this.bytesArrayToEvenByteArray(this.C(array,r));
    }
    /**
     * c function of the magenta process
     * @param array 16 bytes array
     * @param j iteration number
     * @return c function
     */
    public byte[] C(byte [] array,int j)
    {
        if(j==1)
            return this.T(array);
        return this.T(this.attachTwoArrays(Mathmatics.twoByteArrayOfBitsXOR(this.split16LengthArray(array)[0],
                this.bytesArrayToEvenByteArray(C(array,j-1)),8),
                Mathmatics.twoByteArrayOfBitsXOR(this.split16LengthArray(array)[1],
                        this.bytesArrayToOddByteArray(C(array,j-1)),8)));
    }

    /**
     * splits a 16 byte array into two separates arrays
     * @param array 16 bytes array
     * @return 2 arrays array consists of the array splits into two arrays
     */
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

    /**
     * splits the input array into 8 bytes arrays
     * support 128(16 bytes)/192(24 bytes)/256(32 bytes) bits array
     * @param array input array to split,128/192/256 bits array
     * @return byte array of byte array holding the arrays in the order0,...,4
     */
    public byte[][] splitArray(byte[] array)
    {
        int len=array.length;
        byte [][] res;
        if(len==16)
        {
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
        else if(len==24)
        {
            byte[] array1=new byte[8];
            byte[] array2=new byte[8];
            byte[] array3=new byte[8];
            for(int i=0;i<8;i++)
            {
                array1[i]=array[i];
                array2[i]=array[i+8];
                array3[i]=array[i+16];
            }
            res=new byte[][] {array1,array2,array3};
            return res;
        }
        else if(len==32)
        {
            byte[] array1=new byte[8];
            byte[] array2=new byte[8];
            byte[] array3=new byte[8];
            byte[] array4=new byte[8];
            for(int i=0;i<8;i++)
            {
                array1[i]=array[i];
                array2[i]=array[i+8];
                array3[i]=array[i+16];
                array4[i]=array[i+24];
            }
            res=new byte[][] {array1,array2,array3,array4};
            return res;
        }
        return null;
    }

    /**
     * concatenate byte array2[8] to byte array1[8] returns a array[16]
     * @param array1 byte array in the length of 8
     * @param array2 byte array in the length of 8
     * @return byte array1 concatenate byte array2
     */
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
     * function A(x,y)=f(,input two bytes
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
     * function f(x) returns the value of the S-BOX at x address
     * @param y one byte variable
     * @return byte f(x)-S-B0X value at x
     */
    public byte f(byte y)
    {
        int new_y=Byte.toUnsignedInt(y);
        return (byte)(this.sbox[new_y]);
    }

    /**
     * function PE(x,y) of the Magenta cipher
     * @param x one byte variable
     * @param y one byte variable
     * @return byte[2] PE(x,y)=(A(x,y),A(y,x))
     * byte array[0]=A(x,y)
     * byte array[1]=A(y,x)
     */
    public byte[] PE(byte x,byte y)
    {
        byte[] peVec=new byte[2];
        peVec[0]=a(x,y);
        peVec[1]=a(y,x);
        return peVec;
    }

    /**
     * a private function using calculates
     * creates the S-BOX of the Magenta cipher
     * @return int[] array consists of the S-BOX
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
     * Getter of SBOX, return the calculated S-BOX array
     * @return the S-BOx as int[]
     */
    public int[] getSbox() {
        return sbox;
    }

    /**
     * prints this magenta S-BOX
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

    /**
     * divides the plaintext into matrix of bytes each row contains a chunk of bits
     * @param plainText bits stream as a String
     * @param chunkSize how much bits in each row
     * @return byte matrix of the plaintext string as sized chunks each row is a chunk
     */
    public byte[][] plainTextToByteArray(String plainText,int chunkSize)
    {
        int counter=0;
        String s;
        byte[][] result;
        int padding=0;

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
        padding=countPadding(plainText);
        if(countPadding(plainText)>0)
        {
            result[numberOfRows-1][16-padding]=(byte)padding;
            result[numberOfRows-1][16-1]=(byte)padding;
        }

        return result;
    }

    /**
     * what is the number of padding needed for the plaintext
     * @param plainText plaintext of bits
     * @return if there is a need for padding the number of padding
     */
    public int countPadding(String plainText)
    {
        int numberOfRows=Mathmatics.divideWithRoundUp(plainText.length(),128);
        int numberOfByteInPlainText=Mathmatics.divideWithRoundUp(plainText.length(),8);
        int numberOfByteEmpty=(numberOfRows*16)-numberOfByteInPlainText;
        return numberOfByteEmpty;
    }

    /**
     * deletes the padding and replaces the last row with smaller row
     * use after decryption
     * @param plainText plaintext of decrypted bits as String
     * @return matrix ,each row is a chunk the last row without the padding
     */
    public byte[][] deletePadding(String plainText)
    {
        int length=plainText.length();
        int last128=length-16;
        int numberOfRows=Mathmatics.divideWithRoundUp(plainText.length(),128);
        numberOfRows-=1;
        byte[][] result=this.plainTextToByteArray(plainText,128);
        int num;
        int count=0;
        for(int i=0;i<16;i++)
        {
            num=result[numberOfRows][i];
            count=0;
            for (int j=i+1;j<16;j++)
            {
                if(result[numberOfRows][j]==0)
                    count+=1;
            }
            if(count==num-2 && result[numberOfRows][i+num-1]==num)
            {
                byte[] temp=new byte[16-num];
                for(int k=0;k<16-num;k++)
                {
                    temp[k]=result[numberOfRows][k];
                }
                result[numberOfRows]=temp;
                return result;
            }

        }
        return result;
    }

    /**
     * converts bytes matrix to String of bits
     * @param matrix matrix of bits
     * @return String containing the bits of the matrix
     */
    public String byteMatrixToStringOfBytes(byte[][] matrix)
    {
        int len=matrix.length;
        String result="";
        for(int i=0;i<len;i++)
        {
            int column_length=matrix[i].length;
            for(int j=0;j<column_length;j++)
            {
                result=result+Integer.toBinaryString(Byte.toUnsignedInt(matrix[i][j]));
            }
        }
        return result;
    }
}
