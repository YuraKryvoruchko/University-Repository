import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private final String title;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition available = lock.newCondition();

    private boolean isTaken = false;

    public Book(String title) {
        this.title = title;
    }

    public void takeBook() throws InterruptedException {
        if (lock.tryLock()) {
            try {
                if (!isTaken) {
                    isTaken = true;
                    return;
                }
            }
            finally {
                lock.unlock();
            }
        }
        lock.lock();

        try {
            while (isTaken) {
                available.await();
            }
            isTaken = true;
        }
        finally {
            lock.unlock();
        }
    }

    public void returnBook() {
        lock.lock();
        try {
            isTaken = false;
            available.signal();
        }
        finally {
                lock.unlock();
        }
    }

    public String getTitle() {
        return title;
    }
}

