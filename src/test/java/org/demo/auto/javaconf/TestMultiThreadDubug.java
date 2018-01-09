package org.demo.auto.javaconf;

public class TestMultiThreadDubug {

    public static void main(String[] args) throws InterruptedException {

        new Thread(new myTask1(), "test-thread1").start();
        new Thread(new myTask2(), "test-thread2").start();

        for (int i=0 ;i<10; i++) {
            Thread.sleep(500);
        }
    }

    static class myTask1 implements Runnable{

        public void run() {
            for(int i=0; i<10; i++)
                System.out.println("running task1 : " + i);
        }
    }

    static class myTask2 implements Runnable{

        public void run() {
            for(int i=0; i<10; i++)
                System.out.println("running task2 : " + i);
        }
    }
}
