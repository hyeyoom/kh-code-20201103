package kr.or.iei.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {

    public static void main(String[] args) {
        try (final ServerSocket serverSocket = new ServerSocket(1905)) {
            final Socket socket = serverSocket.accept();
            while (true) {
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
                    final String body = "한국말도 돼요?";
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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
