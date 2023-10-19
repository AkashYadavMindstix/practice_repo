package thread;

class ThreadDemo implements Runnable {
    Thread t;

    ThreadDemo() {
        t = new Thread(this, "T1");
        System.out.println("Running Thread : " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted !");
        }
    }
}

class ThreadDemo2 implements Runnable {
    Thread t;

    ThreadDemo2() {
        t = new Thread(this, "T2");
        System.out.println("Running Thread : " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 11; i <= 30; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted !");
        }
    }
}

public class Main {

    public static void main(String args[]) {
        new ThreadDemo();
        new ThreadDemo2();
    }
}
