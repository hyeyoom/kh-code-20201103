package kr.or.iei;

import java.io.*;

public class FileApplication {

    public static void main(String[] args) {
        final File origin = new File("C:\\Users\\user1\\Desktop\\dog.png");
        try (
                final InputStream fileInputStream = new FileInputStream(origin);
                final OutputStream fileOutputStream = new FileOutputStream("C:\\Users\\user1\\Desktop\\dog_copied.png");
        ) {
            final byte[] buffer = new byte[4096];
            int result;
            int totalSize = 0;
            while ((result = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer);
                System.out.println("read byte: " + result);
                totalSize += result;
            }
            System.out.println("file size: " + totalSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
