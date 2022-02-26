public class ThreadsABC {

    private static char letter = 'A';
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        printABC();
    }

    private static void printABC() {

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (letter != 'A') {
                            monitor.wait();
                        }
                        printA();
                        letter = 'B';
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (letter != 'B') {
                            monitor.wait();
                        }
                        printB();
                        letter = 'C';
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (letter != 'C') {
                            monitor.wait();
                        }
                        printC();
                        letter = 'A';
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void printA() {
        System.out.print('A');
    }

    private static void printB() {
        System.out.print('B');
    }

    private static void printC() {
        System.out.println('C');
    }


}
