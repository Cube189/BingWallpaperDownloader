package me.gmur.bingImageDownloader;


public interface ImageDownloader {
    /**
     * Downloads Bing image of the day and writes it to an external file as '_fileName'
     */
    void downloadImage();
}
