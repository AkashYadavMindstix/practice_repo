package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Tasks implements Runnable {

    public String name;

    public Tasks(String s) {
        name = s;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 5; i++) {

                if (i == 0) {
                    Date d = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("Initialization Time for task name : " + name + " = " + df.format(d));
                } else {
                    Date d = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("Executing Time for task name - " + name + " = " + df.format(d));
                }
                Thread.sleep(2000);
            }
            System.out.println(name + " Complete...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadPoolExample {

    // Maximum number of threads in the thread pool
    static final int MAX_THREAD_SIZE = 3;

    public static void main(String args[]) {

        Runnable r1 = new Tasks("task 1");
        Runnable r2 = new Tasks("task 2");
        Runnable r3 = new Tasks("task 3");
        Runnable r4 = new Tasks("task 4");
        Runnable r5 = new Tasks("task 5");

        // creates a thread pool with MAX_THREAD_SIZE no. of
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_SIZE);

        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        pool.shutdown();

    }

}
