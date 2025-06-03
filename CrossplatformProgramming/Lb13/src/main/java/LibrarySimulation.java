import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class LibrarySimulation {
    public static void main(String[] args) throws InterruptedException {
        List<Book> books = Arrays.asList(
                new Book("Майстер і Маргарита"),
                new Book("Гаррі Поттер"),
                new Book("Війна і мир")
        );

        int totalReaders = 5;
        CountDownLatch registrationLatch = new CountDownLatch(totalReaders);
        Semaphore readingRoom = new Semaphore(10);
        List<Book> firstReaderBooks = Arrays.asList(books.get(0));

        Thread firstReader = new Thread(
                new Reader("Reader-1", firstReaderBooks, registrationLatch, readingRoom),
                "Reader-1"
        );
        firstReader.setPriority(Thread.MAX_PRIORITY);
        firstReader.start();
        System.out.println("Додаємо читача " + firstReader.getName());

        for (int i = 2; i <= totalReaders; i++) {
            List<Book> readerBooks = Arrays.asList(books.get((i - 1) % books.size()));
            Thread readerThread = new Thread(
                    new Reader("Reader-" + i, readerBooks, registrationLatch, readingRoom),
                    "Reader-" + i
            );
            readerThread.setPriority(Thread.NORM_PRIORITY);
            readerThread.start();
            System.out.println("Додаємо читача " + readerThread.getName());
        }

        firstReader.join();
        registrationLatch.await();
    }
}