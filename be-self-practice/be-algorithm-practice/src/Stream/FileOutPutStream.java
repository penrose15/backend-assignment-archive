package codestatePrac.Stream;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class FileOutPutStream {
    public static void main(String[] args) {
        try{
            //생성할 파일의 경로 및 파일명으로 File 객체 생성
            FileOutputStream fos = new FileOutputStream("test.txt",true);
            String word = "code";

            byte b[] = word.getBytes();
            fos.write(b);
            fos.close();



        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
