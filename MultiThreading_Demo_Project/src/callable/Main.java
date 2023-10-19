package callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class CallableExample implements Callable {

    @Override
    public Object call() throws Exception {
        Random generator = new Random();

        Integer randomNumber = generator.nextInt(5);

        Thread.sleep(randomNumber * 1000);

        return randomNumber;
    }

}

public class Main { // CallableFutureExample

    public static void main(String args[]) throws Exception {

        FutureTask[] randomNumberTasks = new FutureTask[5]; // defined FutureTask[] size of 5

        for (int i = 0; i < 5; i++) {

            Callable callable = new CallableExample();

            System.out.println(i + " : " + callable.toString()); // 1

            randomNumberTasks[i] = new FutureTask(callable);

            System.out.println(randomNumberTasks[i]); // 2

            // Created Thread with FutureTask
            Thread t = new Thread(randomNumberTasks[i]); // 3

            System.out.println("Thread : " + t);

            t.start();

        }

        for (int i = 0; i < 5; i++) {
            System.out.print("Ans : " + i + " ");
            System.out.println(randomNumberTasks[i].get());
        }

    }

}
