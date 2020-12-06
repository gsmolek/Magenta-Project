import javax.swing.plaf.synth.SynthSpinnerUI;
import java.util.Arrays;

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
    public static void printByteArray(byte[] array,int length)
    {
        System.out.print("[");
        for(int i=0;i<length;i++)
        {
            System.out.print(array[i]);
            if(i<length-1)
                System.out.print(", ");
        }
        System.out.print("]\n");
    }

}
