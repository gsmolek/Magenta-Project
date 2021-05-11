import java.io.*;


public class FileOperations {

    public static byte[] getBytes(File f) throws FileNotFoundException,IOException
    {
        byte[] buffer=new byte[1024];
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        FileInputStream fis=new FileInputStream(f);
        int read;
        while((read=fis.read(buffer))!=-1)// if equal -1 no bytes are readed
        {
            os.write(buffer,0,read);
        }
        fis.close();// close file input stream
        os.close();//close byte array output stream
        return os.toByteArray();
    }

    public static void toFile(byte[] data,File destination)
    {
        try(FileOutputStream fos=new FileOutputStream(destination)){
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
