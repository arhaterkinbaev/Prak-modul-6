package ex1;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.loadConfiguration("config.properties"); // Загрузим конфигурацию

        Runnable logTask = () -> {
            logger.log("This is an info message.", LogLevel.INFO);
            logger.log("This is a warning message.", LogLevel.WARNING);
            logger.log("This is an error message.", LogLevel.ERROR);
        };

        Thread thread1 = new Thread(logTask);
        Thread thread2 = new Thread(logTask);
        Thread thread3 = new Thread(logTask);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogReader logReader = new LogReader("logs.txt");
        System.out.println("\nReading ERROR logs:");
        logReader.readLogs(LogLevel.ERROR);
    }
}
