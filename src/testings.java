import java.util.Scanner;
/**
 * @author Gilad Molek
 * @version 2.4
 * @apiNote supports working with vectors
 * @// TODO: 11/12/2020 implement more testings
 */
public class testings {
    private Magenta magenta;

    public testings() {
        magenta = new Magenta();
    }
    public void testingDecrypiton(byte[] message,byte[] key)
    {
        byte[] n=magenta.decryption(message,key);
        System.out.println("Test");
        System.out.println("Decryption-function");
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(message,message.length);
        ByteVector.printByteArrayAsInt(message,message.length);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,n.length);
        ByteVector.printByteArrayAsInt(n,n.length);
        System.out.println();
    }
    public void testingVfunction(byte[] array)
    {
        byte[]n=magenta.V(array);
        System.out.println("Test");
        System.out.println("V-function");
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,array.length);
        ByteVector.printByteArrayAsInt(array,array.length);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,n.length);
        ByteVector.printByteArrayAsInt(n,n.length);
        System.out.println();
    }
    public byte[] testingEncryption(byte[] message,byte[] key)
    {
        byte[] n=magenta.encryption(message,key);
        System.out.println("Test");
        System.out.println("Encryption-function");
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(message,message.length);
        ByteVector.printByteArrayAsInt(message,message.length);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,n.length);
        ByteVector.printByteArrayAsInt(n,n.length);
        System.out.println();
        return n;
    }
    public void testingFeistelfunction(byte[] array, byte[] key1)
    {
        byte[] n=magenta.feistelRound(array,key1);
        System.out.println("Test");
        System.out.println("feistel-function");
        System.out.println("input:");
        System.out.println("plaintext:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("key:");
        ByteVector.printByteArrayAsBits(array,8);
        ByteVector.printByteArrayAsInt(array,8);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,16);
        ByteVector.printByteArrayAsInt(n,16);
        System.out.println();
        byte[] x0_7=magenta.split16LengthArray(array)[0];
        byte[] x8_15=magenta.split16LengthArray(array)[1];
        byte[] c= magenta.attachTwoArrays(x8_15,
                Mathmatics.twoByteArrayOfBitsXOR(x0_7,magenta.E(magenta.attachTwoArrays(x8_15,key1),3),8));
        System.out.println("result lines:");
        ByteVector.printByteArrayAsBits(c,16);
        ByteVector.printByteArrayAsInt(c,16);
        System.out.println();
    }
    public void testingFfunction(byte a)
    {
        byte n= magenta.f(a);
        System.out.println("Test");
        System.out.println("f-function");
        System.out.println("input:");
        System.out.println("a: "+Integer.toBinaryString(a)+"/"+Byte.toUnsignedInt(a));
        System.out.println("result:");
        System.out.println("as binary: "+Integer.toBinaryString(Byte.toUnsignedInt(n)));
        System.out.println("as Int: "+Byte.toUnsignedInt(n));
        System.out.println();
    }
    public void testingAfunction(byte a,byte b)
    {
        byte n=magenta.a(a,b);
        System.out.println("Test");
        System.out.println("A-function:");
        System.out.println("a: "+Integer.toBinaryString(a)+"/"+Byte.toUnsignedInt(a));
        System.out.println("b: "+Integer.toBinaryString(b)+"/"+Byte.toUnsignedInt(b));
        System.out.println("as binary: "+Integer.toBinaryString(Byte.toUnsignedInt(n)));
        System.out.println("as Int: "+Byte.toUnsignedInt(n));
        System.out.println();
    }
    public void testingPEfunction(byte a,byte b)
    {
        byte[] n={0,0};
        n=magenta.PE(a,b);
        System.out.println("Test");
        System.out.println("PE-function:");
        System.out.println("a: "+Integer.toBinaryString(a)+"/"+Byte.toUnsignedInt(a));
        System.out.println("b: "+Integer.toBinaryString(b)+"/"+Byte.toUnsignedInt(b));
        System.out.println("as binary: ("+Integer.toBinaryString(Byte.toUnsignedInt(n[0]))+", "+Integer.toBinaryString(Byte.toUnsignedInt(n[1]))+")");
        System.out.println("as Int: ("+Byte.toUnsignedInt(n[0])+", "+Byte.toUnsignedInt(n[1])+")");
        System.out.println();
    }
    public byte[] testingPIfunction(byte[] array)
    {
        byte[] n=new byte[16];
        n=magenta.PI(array);
        System.out.println("Test");
        System.out.println("PI-function");
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,16);
        ByteVector.printByteArrayAsInt(n,16);
        System.out.println();
        return n;
    }
    public byte[] testingTfunction(byte[] array)
    {
        System.out.println("Test");
        System.out.println("T-function:");
        byte[] n=magenta.T(array);
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,16);
        ByteVector.printByteArrayAsInt(n,16);
        System.out.println();
        return n;
    }
    public void testingEven(byte[] array)
    {
        System.out.println("Test");
        byte[] n= magenta.bytesArrayToEvenByteArray(array);
        System.out.println("Xe-function");
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,16);
        ByteVector.printByteArrayAsInt(n,16);
        System.out.println();
    }
    public void testingOdd(byte[] array)
    {
        System.out.println("Test");
        byte[] n= magenta.bytesArrayToOddByteArray(array);
        System.out.println("Xo-function");
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,16);
        ByteVector.printByteArrayAsInt(n,16);
        System.out.println();
    }
    public void testingCfunction(byte[] array,int t)
    {
        System.out.println("Test");
        System.out.println("C-function");
        byte[] n=magenta.C(array,t);
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,16);
        ByteVector.printByteArrayAsInt(n,16);
        System.out.println();
    }
    public void testingSplitFunction(byte[] array)
    {
        System.out.println("Test");
        System.out.println("Split-function");
        byte[][] n=magenta.splitArray(array);
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,array.length);
        ByteVector.printByteArrayAsInt(array,array.length);
        System.out.println("Key length:"+array.length);
        if(array.length==16)
        {
            System.out.println("result:");
            ByteVector.printByteArrayAsInt(n[0],n[0].length);
            ByteVector.printByteArrayAsInt(n[1],n[1].length);
            System.out.println();
        }
        if(array.length==24)
        {
            System.out.println("result:");
            ByteVector.printByteArrayAsInt(n[0],n[0].length);
            ByteVector.printByteArrayAsInt(n[1],n[1].length);
            ByteVector.printByteArrayAsInt(n[2],n[2].length);
            System.out.println();
        }
        if(array.length==32)
        {
            System.out.println("result:");
            ByteVector.printByteArrayAsInt(n[0],n[0].length);
            ByteVector.printByteArrayAsInt(n[1],n[1].length);
            ByteVector.printByteArrayAsInt(n[2],n[2].length);
            ByteVector.printByteArrayAsInt(n[3],n[3].length);
            System.out.println();
        }
    }
    public void testingEfunction(byte[] array,int t)
    {
        System.out.println("Test");
        System.out.println("E-function");
        byte[] n=magenta.E(array,t);
        System.out.println("input:");
        ByteVector.printByteArrayAsBits(array,16);
        ByteVector.printByteArrayAsInt(array,16);
        System.out.println("result:");
        ByteVector.printByteArrayAsBits(n,8);
        ByteVector.printByteArrayAsInt(n,8);
        System.out.println();
    }
    public void printSboxWithIndexes()
    {
        System.out.println("Test");
        int[] a=magenta.getSbox();
        for (int i = 0;i<256;i++ ) {
            if(i%8==0) {
                System.out.println();
                System.out.println("============================================" +
                        "==============================================================");
            }
            System.out.print("||"+i+": "+a[i]+"||  ");

        }
    }

    /**
     * testing PE with input from user and prints the result
     */
    public void test_PE_Sequence()
    {
        System.out.println("Test");
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            int n1=myObj.nextInt();
            int n2=myObj.nextInt();
            this.testingPEfunction((byte)n1,(byte)n2);
        }
    }
}
