/**
 * @author Gilad Molek
 * @version 2.4
 * @apiNote supports working with vectors
 * @// TODO: 11/12/2020 merge with magenta class
 * @// TODO: 11/12/2020 javadocs
 */
public class  Mathmatics {
    public static int power(int number,int exp)
    {
        int temp=number;
        if(exp!=0) {
            for (int i = exp - 1; i > 0; i--) {
                temp = temp * number;
            }
            return temp;
        }
        else if(number!=0)
            return 1;
        else return 0;
    }
    public static int convertByteArrayToInteger(byte[] arr,int length)
    {
        int exp=length-1;
        int num=0;
        for(int i=0;i<length;i++)
        {
            num=num+arr[i]*Mathmatics.power(2,exp);
            exp-=1;
        }
        return num;
    }
    public static int convertStringToInteger(String str)
    {
        int exp=str.length()-1;
        int num=0;
        for(int i=0;i<str.length();i++)
        {
            num=num+(Integer.parseInt(String.valueOf(str.charAt(i)))*Mathmatics.power(2,exp));
            exp-=1;
        }
        return num;
    }
    public static byte[] shiftleft(byte [] arr,int length)
    {
        for(int i=0;i<length-1;i++)
        {
            arr[i]=arr[i+1];
        }
        arr[length-1]=0;
        return arr;
    }
    public static byte twoByteXOR(byte arr,byte xor)
    {

        byte result;
        result=(byte)(Byte.toUnsignedInt(arr)^Byte.toUnsignedInt(xor));
        return result;
    }

    /**
     * preform xor operation on two byte arrays,each object in the array represent a bit value
     * @param arr the input array consist
     * @param xor
     * @param length
     * @return
     */
    public static byte[] twoByteArrayOfBitsXOR(byte[] arr,byte[] xor,int length)
    {

        byte[] result={0,0,0,0,0,0,0,0};
        for (int i=0;i<length;i++) {
            result[i]=(byte)(Byte.toUnsignedInt(arr[i])^Byte.toUnsignedInt(xor[i]));
        }
        return result;
    }
    public static int divideWithRoundUp(int numerator,int denominator)
    {
        return (numerator+denominator-1)/denominator;
    }
}
