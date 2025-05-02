import java.util.ArrayList;
import java.util.List;

abstract class MediaFile {
    private final String fileName;
    private final double fileSize;

    protected MediaFile(String fileName, double fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public abstract void play();

    public void displayInfo() {
        System.out.println("File: " + fileName);
        System.out.println("Size: " + fileSize + " MB");
    }
}

class Audio extends MediaFile {
    private final int duration;

    public Audio(String fileName, double fileSize, int duration) {
        super(fileName, fileSize);
        this.duration = duration;
    }

    @Override
    public void play() {
        System.out.println("Play audio: " + getFileName() + " (" + duration + " sec.)");
    }
}

class Video extends MediaFile {
    private final String resolution;

    public Video(String fileName, double fileSize, String resolution) {
        super(fileName, fileSize);
        this.resolution = resolution;
    }

    @Override
    public void play() {
        System.out.println("Play video: " + getFileName() + " in resolution " + resolution);
    }
}

interface IObserver {
    void update(String message);
}

class User implements IObserver{
    private final String name;

    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String message){
        System.out.println(name + " received update: " + message);
    }
}

class MediaLibrary {
    private final String name;

    private final List<IObserver> observers = new ArrayList<>();
    private final List<MediaFile> mediaFiles = new ArrayList<>();

    public MediaLibrary(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void displayMediaFiles(){
        System.out.println("Media files in " + name + ":");
        for (MediaFile media : mediaFiles) {
            media.displayInfo();
            media.play();
            System.out.println();
        }
    }

    public void uploadNewMedia(MediaFile newMedia) {
        mediaFiles.add(newMedia);
        System.out.println("New media uploaded: " + newMedia.getFileName() + " in " + name + "library");
        notifyObservers("New media available: " + newMedia.getFileName() + " in " + name + "library");
    }

    private void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MediaLibrary horrorLibrary = new MediaLibrary("Horror library");
        MediaLibrary casualLibrary = new MediaLibrary("Casual library");

        User yuriiUser = new User("Yurii");
        User annaUser = new User("Anna");
        User vadimUser = new User("Vadim");

        horrorLibrary.addObserver(yuriiUser);
        horrorLibrary.addObserver(vadimUser);
        casualLibrary.addObserver(annaUser);
        casualLibrary.addObserver(vadimUser);

        horrorLibrary.uploadNewMedia(new Audio("horror_song.mp3", 5.4, 180));
        System.out.println();
        horrorLibrary.uploadNewMedia(new Video("horror_movie.mp4", 1500.0, "1080p"));
        System.out.println();

        casualLibrary.uploadNewMedia(new Audio("casual_song.mp3", 3.8, 120));
        System.out.println();
        casualLibrary.uploadNewMedia(new Video("casual_movie.mp4", 1000.0, "720p"));
        System.out.println();

        horrorLibrary.displayMediaFiles();
        casualLibrary.displayMediaFiles();
    }
}
