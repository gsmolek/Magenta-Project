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
    public static byte[] shiftleft(byte [] arr,int length)
    {
        for(int i=0;i<length-1;i++)
        {
            arr[i]=arr[i+1];
        }
        arr[length-1]=0;
        return arr;
    }
    public static byte[] twoByteArraysXOR(byte[] arr,byte[] xor,int length)
    {

        byte[] result={0,0,0,0,0,0,0,0};
        for(int i=0;i<length;i++)
        {
            if(arr[i]!=xor[i])
                result[i]=1;
        }
        System.out.println("arr");
        ByteVector.printByteArray(arr,8);
        System.out.println("XOR");
        ByteVector.printByteArray(xor,8);
        System.out.println("result");
        ByteVector.printByteArray(result,8);
        return result;
    }
}