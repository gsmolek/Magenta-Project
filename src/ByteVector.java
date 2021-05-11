import javax.swing.plaf.synth.SynthSpinnerUI;
import java.util.Arrays;

/**
 * @author Gilad Molek
 * @version 2.0
 * @apiNote supports working with vectors
 * @// TODO: 11/12/2020 merge with magenta class
 * @// TODO: 11/12/2020 javadocs
 */
public class ByteVector {
    private byte[] vector;
    private int[] polynom;

    public int getPolynomialValue() {
        return polynomialValue;
    }

    private int polynomialValue;
    public ByteVector(byte[] set)
    {
        vector=set;
        polynom=new int[8];
        calculatePolynomial();

    }
    public int[] calculatePolynomial()
    {
        int sum=0;
        int exponential=7;
        for(int i=0;i<8;i++) {
            polynom[i] = vector[i]*Mathmatics.power(2, exponential);
            exponential-=1;
            sum=sum+polynom[i];
        }
        polynomialValue=sum;
        return polynom;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public byte[] getVector() {
        return vector;
    }

    public int[] getPolynom() {
        return polynom;
    }

    /**
     * function that prints byte array
     * @param array the Array to print
     * @param length the Array length
     */
    public static void printByteArrayAsBits(byte[] array,int length)
    {
        System.out.print("[");
        for(int i=0;i<length;i++)
        {
            System.out.print(Integer.toBinaryString(Byte.toUnsignedInt(array[i])));
            if(i<length-1)
                System.out.print(", ");
        }
        System.out.print("]\n");
    }

    /**
     * Prints a byte array as integers
     * @param array Array of bytes to print
     * @param length The length of the array
     */
    public static void printByteArrayAsInt(byte[] array,int length)
    {
        System.out.print("[");
        for(int i=0;i<length;i++)
        {
            System.out.print(Byte.toUnsignedInt(array[i]));
            if(i<length-1)
                System.out.print(", ");
        }
        System.out.print("]\n");
    }
    public static void printByteByteArray(byte[][] array)
    {
        int flag=0;
        System.out.print("[");
        for(int i=0;i<array.length;i++)
        {
            System.out.println("Row "+i+": ");
            for(int j=0;j<array[i].length;j++) {
                System.out.print(Byte.toUnsignedInt(array[i][j]));
                if (j < array[i].length- 1)
                    System.out.print(", ");
            }
            System.out.print("]\n");
        }

    }
    public static void printByteArrayAsBits(byte[] array)
    {
        System.out.print("[");
        for(int i=0;i<array.length;i++)
        {
            System.out.print(Integer.toBinaryString(Byte.toUnsignedInt(array[i])));
            if(i<array.length-1)
                System.out.print(", ");
        }
        System.out.print("]\n");
    }
    public static boolean compareTwoBytesArray(byte[] array1,byte[] array2)
    {
        int array1_length=array1.length;
        int array2_length=array2.length;
        if(array1_length==array2_length)
        {
            for(int i=0;i<array1_length;i++)
            {
                if(array1[i]!=array2[i])
                    return false;
            }
            return true;
        }
        return false;
    }
    public static boolean compareTwoBytesMatrixs(byte[][] mat1,byte[][] mat2)
    {
        int mat1_length=mat1.length;
        int mat2_length=mat2.length;
        if(mat1_length==mat2.length)
        {
            for(int i=0;i<mat1_length;i++)
                for(int j=0;j<16;j++)
                {
                    if(mat1[i][j]!=mat2[i][j])
                        return false;
                }
            return true;
        }
        return false;
    }

    public static byte[] fromMatrixToArray(byte[][] mat)
    {
        int index=0;
        byte[] array = new byte[calculateLengthOfMatrix(mat)];
        for (int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[i].length;j++)
            {
                array[index]=mat[i][j];
                index++;
            }
        }
        return array;
    }
    public static int calculateLengthOfMatrix(byte[][] mat)
    {
        int count=0;
        for(int i=0;i<mat.length;i++) {
            for (int j = 0; j < mat[i].length; j++) {
                count += 1;
            }
        }
        return count;
    }

}
