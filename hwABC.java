public class hwABC {
      // Utilize wait and notifyall;
      // three booleans of thread
    private static boolean threadOfA = true;
    private static boolean threadOfB = false;
    private static boolean threadOfC = false;

    public static void main(String[] args) {

        final Object o = new Object();

        // Create the new thread A

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; ) {
                    if (threadOfA) {
                        System.out.print(Thread.currentThread().getName());
                        // change the state of B thread to be true;
                        threadOfA = false;
                        threadOfB = true;
                        threadOfC = false;

                        o.notifyAll();
                        i++;
                    } else {
                        try {
                            o.wait();
                        } catch(InterruptedException e) {
                            return;

                        }
                    }
                }
            }
        }, "A").start();

        // Create the new thread B

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; ) {
                    if (threadOfB) {
                        System.out.print(Thread.currentThread().getName());
                        threadOfA = false;
                        threadOfB = false;
                        threadOfC = true;
                        // notify other threads to start
                        o.notifyAll();
                        i++;
                    } else {
                        try {
                            o.wait();
                        } catch(InterruptedException e) {
                            return;

                        }
                    }
                }
            }
        }, "B").start();

        // Create the new thread C

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; ) {
                    if (threadOfC) {
                        System.out.print(Thread.currentThread().getName());
                        threadOfA = true;
                        threadOfB = false;
                        threadOfC = false;
                         // notify other threads to start
                        o.notifyAll();
                        i++;
                    } else {
                        try {
                            o.wait();
                        } catch(InterruptedException e) {
                            return;

                        }
                    }
                }
            }
        }, "C").start();
    }
}