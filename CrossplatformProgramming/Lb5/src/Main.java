abstract class MediaFile {
    private final String fileName;
    private final double fileSize; // у мегабайтах

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

public class Main {
    public static void main(String[] args) {
        MediaFile[] mediaFiles = new MediaFile[] {
                new Audio("something_song.mp3", 5.4, 180),
                new Video("something_movie.mp4", 1500.0, "1080p")
        };

        System.out.println("Catalog of media files:");
        for (MediaFile media : mediaFiles) {
            media.displayInfo();
            media.play();
            System.out.println();
        }
    }
}