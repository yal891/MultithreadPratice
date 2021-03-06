public class modelOfPc {

// Producer and custumer achieved by wait and notifyall
// Time: 0.25 sec(s), Memory: 38180 kilobyte(s)
	
    private static Integer size = 0;
    private static final Integer Capacity = 10;
    private static String synchronizedLock = "lock";

    public static void main(String[] args) {
        modelOfPc pcmodel = new modelOfPc();
        //start the thread
        new Thread(pcmodel.new Producer()).start();
        new Thread(pcmodel.new Producer()).start();
        new Thread(pcmodel.new Producer()).start();
        new Thread(pcmodel.new Consumer()).start();
        new Thread(pcmodel.new Producer()).start();
        new Thread(pcmodel.new Consumer()).start();
        new Thread(pcmodel.new Consumer()).start();



    }
    //Thread of Producer
    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    return;
                }
                synchronized (synchronizedLock) {
                    while (size == Capacity) {
                        try {
                            synchronizedLock.wait();
                        } catch (Exception e) {
                            return;
                        }
                    }
                    size++;
                    System.out.println(Thread.currentThread().getName() + "----the total after Producer is: " + size);
                    
                    //Notify when the capacity is full
                    if (size == Capacity) System.out.println("The Capacity is full. Consuming! ");
                    synchronizedLock.notifyAll();
                }
            }
        }
    }

    //Thread of Consumer
    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                synchronized (synchronizedLock) {
                    while (size == 0) {
                        try {
                            synchronizedLock.wait();
                        } catch (Exception e) {
			    return;
                        }
                    }
                    size--;
                    System.out.println(Thread.currentThread().getName() + "----the total after Consumer is: " + size);
                    //Notify when the capacity is empty.
                    if (size == 0) System.out.println("The Capacity is empty. Producing! ");
                    synchronizedLock.notifyAll();
                }
            }
        }
    }
}
