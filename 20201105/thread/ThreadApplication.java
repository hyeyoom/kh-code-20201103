package kr.or.iei.thread;

import java.util.Scanner;

public class ThreadApplication {

    public static void main(String[] args) {
        final BeepThread thread = new BeepThread();
        thread.setDaemon(true);
        thread.start();

        final Scanner scanner = new Scanner(System.in);
        while (true) {
            final int value = scanner.nextInt();
            System.out.println("main: " + value);
        }
    }
}
