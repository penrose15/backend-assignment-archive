package codestatePrac.Stream;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
/*        File file = new File("../codestates.txt");

        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.canWrite());*/
        File parentDir = new File("./");
        File[] list = parentDir.listFiles();

        String prefix= "code";

        for(int i = 0; i<list.length;i++) {
            String fileName = list[i].getName();

            if(fileName.endsWith("txt") && !fileName.startsWith("code")) {
                list[i].renameTo(new File(parentDir,prefix+fileName));
            }
        }
    }
}
