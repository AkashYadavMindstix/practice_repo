package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class CallableImpl implements Callable {

    long waitTime;

    public CallableImpl(long time) {
        waitTime = time;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName();
    }

}

public class FutureTaskAndThreadPool {

    public static void main(String args[]) {

        CallableImpl callable1 = new CallableImpl(1000);
        CallableImpl callable2 = new CallableImpl(2000);

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Done...");
                    executor.shutdown();
                    return;
                }
                if (!futureTask1.isDone()) {
                    // wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output = " + futureTask1.get());
                }
                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if (s != null) {
                    System.out.println("FutureTask2 output = " + s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                // Time Out exception
            }
        }

    }

}
