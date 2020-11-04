package kr.or.iei.thread;

import java.awt.*;

public class BeepThread extends Thread {

    @Override
    public void run() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        while (true) {
            tk.beep();
            try {
                System.out.printf("[%s] thread is running\n", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
