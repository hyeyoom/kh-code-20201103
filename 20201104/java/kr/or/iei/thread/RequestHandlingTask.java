package kr.or.iei.thread;

import java.io.*;
import java.net.Socket;

public class RequestHandlingTask implements Runnable {

    private final Socket socket;

    public RequestHandlingTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                final InputStream in = socket.getInputStream();     // from client
                final OutputStream out = socket.getOutputStream();  // to client
        ) {
            // input
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine;
            while (!(readLine = br.readLine()).equals("")) {
                System.out.println(readLine);
            }

            // output
            final String body = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>제목</h1>\n" +
                    "<p>글글글글</p>\n" +
                    "</body>\n" +
                    "</html>";
            final byte[] raw = body.getBytes();
            final DataOutputStream dos = new DataOutputStream(out);
            // 헤더 쓰는 부분
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + raw.length + "\r\n");
            dos.writeBytes("\r\n");
            // 바디 쓰는 부분
            dos.write(raw, 0, raw.length);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
