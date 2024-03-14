package codestatePrac.Stream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class FileInputStreamExample {
    public static void main(String[] args) {
        try{
            FileInputStream fileInputStream = new FileInputStream("codestates.txt");
            BufferedInputStream buffered = new BufferedInputStream(fileInputStream);
            int i = 0;
            while((i = buffered.read()) !=-1) {
                System.out.println((char)i);
            }
            fileInputStream.close();

        }catch (Exception e) {
            System.out.print(e);

        }
    }
}
