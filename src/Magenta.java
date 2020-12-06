public class Magenta {


    public static int[] f()
    {
        int[] sbox=new int[256];
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
