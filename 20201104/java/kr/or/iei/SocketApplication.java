package kr.or.iei;

import kr.or.iei.thread.RequestHandlingTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketApplication {

    public static void main(String[] args) {
        
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100)
        );

        try (final ServerSocket serverSocket = new ServerSocket(1905)) {
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                final Runnable task = new RequestHandlingTask(socket);
                executor.execute(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
