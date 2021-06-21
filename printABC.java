import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class printABC2 {
      
    // Utilization of ExecutorService with a single thread executor to print A or B or C.
    // Test: CPU Time: 0.22 sec(s), Memory: 38664 kilobyte(s)
    // Code is much shorter but a little slower compared with "wait and notify all".
      
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        Thread threadA = new Thread(() -> System.out.print("A"));
        Thread threadB = new Thread(() -> System.out.print("B"));
        Thread threadC = new Thread(() -> System.out.print("C"));

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i< 50; i++){
            executorService.submit(threadA);
            executorService.submit(threadB);
            executorService.submit(threadC);
        }
        executorService.shutdown();
        long endTime=System.currentTimeMillis();
        System.out.println("\n" + "The program is running for "+ (endTime-startTime)+"ms");  
    }
}

// public class pringABC1 {
//       // Utilize wait and notifyall;
//       // three booleans of thread
//       //Test: CPU Time: 0.12 sec(s), Memory: 35096 kilobyte(s)
//     private static boolean threadOfA = true;
//     private static boolean threadOfB = false;
//     private static boolean threadOfC = false;

//     public static void main(String[] args) {

//         final Object o = new Object();
//         long startTime=System.currentTimeMillis();

//         // Create the new thread A

//         new Thread(() -> {
//             synchronized (o) {
//                 for (int i = 0; i < 50; ) {
//                     if (threadOfA) {
//                         System.out.print(Thread.currentThread().getName());
//                         // change the state of B thread to be true;
//                         threadOfA = false;
//                         threadOfB = true;
//                         threadOfC = false;

//                         o.notifyAll();
//                         i++;
//                     } else {
//                         try {
//                             o.wait();
//                         } catch(InterruptedException e) {
//                             return;

//                         }
//                     }
//                 }
//             }
//         }, "A").start();

//         // Create the new thread B

//         new Thread(() -> {
//             synchronized (o) {
//                 for (int i = 0; i < 50; ) {
//                     if (threadOfB) {
//                         System.out.print(Thread.currentThread().getName());
//                         threadOfA = false;
//                         threadOfB = false;
//                         threadOfC = true;
//                         // notify other threads to start
//                         o.notifyAll();
//                         i++;
//                     } else {
//                         try {
//                             o.wait();
//                         } catch(InterruptedException e) {
//                             return;

//                         }
//                     }
//                 }
//             }
//         }, "B").start();

//         // Create the new thread C

//         new Thread(() -> {
//             synchronized (o) {
//                 for (int i = 0; i < 50; ) {
//                     if (threadOfC) {
//                         System.out.print(Thread.currentThread().getName());
//                         threadOfA = true;
//                         threadOfB = false;
//                         threadOfC = false;
//                          // notify other threads to start
//                         o.notifyAll();
//                         i++;
//                     } else {
//                         try {
//                             o.wait();
//                         } catch(InterruptedException e) {
//                             return;

//                         }
//                     }
//                 }
//             }
//         }, "C").start();
//      long endTime=System.currentTimeMillis();
//      System.out.println("\n" + "The program is running for "+ (endTime-startTime)+"ms");  
//     }
// }
