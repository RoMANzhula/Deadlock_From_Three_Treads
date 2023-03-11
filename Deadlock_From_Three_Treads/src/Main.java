public class Main {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    public static Object lock3 = new Object();

    public static void main(String[] args) {
        ThreadDemo1 t1 = new ThreadDemo1();
        ThreadDemo2 t2 = new ThreadDemo2();
        ThreadDemo3 t3 = new ThreadDemo3();

        t1.start();
        t2.start();
        t3.start();
    }

    private static class ThreadDemo1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread1: is holding lock1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {}
                System.out.println("Thread1: Waiting for lock2...");
                synchronized (lock2) {
                    System.out.println("Thread1: is holding lock1 & lock2...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException interruptedException) {}
                    System.out.println("Thread1: Waiting for lock3...");
                    synchronized (lock3) {
                        System.out.println("Thread1: is holding lock1 & lock2 & lock3...");

                    }
                }
            }
        }
    }

    private static class ThreadDemo2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread2: is holding lock2...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {}
                System.out.println("Thread2: Waiting for lock3...");
                synchronized (lock3) {
                    System.out.println("Thread1: is holding lock2 & lock3...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException interruptedException) {}
                    System.out.println("Thread2: Waiting for lock3...");
                    synchronized (lock1) {
                        System.out.println("Thread2: is holding lock2 & lock3 & lock1...");
                    }
                }
            }
        }
    }

    private static class ThreadDemo3 extends Thread {
        @Override
        public void run() {
            synchronized (lock3) {
                System.out.println("Thread3: is holding lock3...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {}
                System.out.println("Thread3: Waiting for lock1...");
                synchronized (lock1) {
                    System.out.println("Thread1: is holding lock1 & lock3...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException interruptedException) {}
                    System.out.println("Thread3: Waiting for lock2...");
                    synchronized (lock2) {
                        System.out.println("Thread3: is holding lock1 & lock3 & lock2...");

                    }
                }
            }
        }
    }
}