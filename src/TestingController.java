/**
 * @author Gilad Molek
 * @version 2.4
 * @apiNote supports working with vectors
 * @// TODO: 11/12/2020 merge with magenta class
 * @// TODO: 11/12/2020 javadocs
 * @// TODO: 11/12/2020 add more testing 
 */
public class TestingController {
    TestingController()
    {
        byte[] arr = {1, 0, 0, 1, 1, 0, 0, 1};
        byte[] array=new byte[16];
        int[] sbox;
        byte check = 1;
        Magenta m = new Magenta();

        byte[] a=m.T(array);
        byte first=1;
        for(int i=0;i<16;i++)
        {
            array[i]=first;
            first=(byte)((byte)(first)+1);
        }
        byte[] array2={(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162,(byte)87,(byte)181,(byte)49,(byte)66
                ,(byte)125,(byte)140,(byte)218,(byte)117};
        byte[] key={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18};
        byte[] key128={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)123,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18};
        byte[] key192={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)123,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162};
        byte[] key256={(byte)11,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)123,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18,(byte)31,(byte)57,(byte)9,(byte)242,(byte)84
                ,(byte)131,(byte)85,(byte)162,(byte)125,(byte)12,(byte)13,(byte)14,(byte)15,(byte)16
                ,(byte)17,(byte)18};
        testings t=new testings();
        t.testingFfunction((byte)20);
        t.testingFfunction((byte)21);
        t.testingFfunction((byte)110);
        t.testingFfunction((byte)131);
        t.testingAfunction((byte)20,(byte)21);
        t.testingPEfunction((byte)20,(byte)21);
        t.testingPIfunction(array);
        //t.testingAfunction((byte)9,(byte)1);
        t.testingTfunction(array2);
        t.testingCfunction(array,10);
        t.testingEfunction(array,10);
        t.testingFeistelfunction(array,key);
        t.testingSplitFunction(key128);
        t.testingSplitFunction(key192);
        t.testingSplitFunction(key256);
        t.testingEncryption(array,key128);
        t.testingEncryption(array,key192);
        t.testingEncryption(array,key256);
        t.testingDecrypiton(t.testingEncryption(array,key256),key256);
    }
}
