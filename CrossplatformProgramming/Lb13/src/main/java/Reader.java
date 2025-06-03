import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    private final String name;
    private final List<Book> booksToRead;
    private final CountDownLatch registrationLatch;
    private final Semaphore readingRoom;

    private final ThreadLocal<List<Book>> heldBooks = ThreadLocal.withInitial(ArrayList::new);
    public Reader(String name, List<Book> booksToRead, CountDownLatch registrationLatch, Semaphore readingRoom) {
        this.name = name;
        this.booksToRead = booksToRead;
        this.registrationLatch = registrationLatch;
        this.readingRoom = readingRoom;
    }

    @Override
    public void run() {
        registrationLatch.countDown();

        for (Book book : booksToRead) {
            try {
                readingRoom.acquire();
                book.takeBook();
                heldBooks.get().add(book);

                System.out.println(this.name + " почав читати книжку " + book.getTitle());
                Thread.sleep(1000);
                System.out.println(this.name + " закінчив читати книжку " + book.getTitle());

                book.returnBook();
                heldBooks.get().remove(book);
                readingRoom.release();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
} 