import java.util.Scanner;

public class testings {
    private Magenta magenta;
    public testings() {
        magenta=new Magenta();
    }
    public void testingFfunction(byte a)
    {
        byte n= magenta.f(a);
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
    public void printSboxWithIndexes()
    {
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
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            int n1=myObj.nextInt();
            int n2=myObj.nextInt();
            this.testingPEfunction((byte)n1,(byte)n2);
        }
    }
}
