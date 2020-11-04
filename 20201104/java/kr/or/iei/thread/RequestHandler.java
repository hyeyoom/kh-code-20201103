package kr.or.iei.thread;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("사용자가 들어왔음.");
        try (
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
        ) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            final DataOutputStream dos = new DataOutputStream(out);
            String readLine;
            while (!(readLine = br.readLine()).equals("")) {
                System.out.println(readLine);
            }
            System.out.println("v");
            final byte[] bytes = "message".getBytes();
            response200Header(dos, bytes.length);
            responseBody(dos, bytes);
            System.out.println(Thread.currentThread().getName() + " 뭔가 다하고 끝냄");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
        }
    }
}
