package codestatePrac.Stream;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        try{
            String fileName = "codestates.txt";
            FileWriter writer = new FileWriter(fileName);
            String str = "WRITTEN!";
            writer.write(str);
            writer.close();

        }catch (IOException e) {
            e.printStackTrace();

        }
    }
}
