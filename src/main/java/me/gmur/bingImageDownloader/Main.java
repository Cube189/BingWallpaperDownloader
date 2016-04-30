package me.gmur.bingImageDownloader;

import me.gmur.bingImageDownloader.Impl.ImageDownloaderImpl;

public class Main {


    private static String savedImageFilename = "bingimage.jpg";

    public static void main(final String[] args) {

        ImageDownloaderImpl imageDownloader = new ImageDownloaderImpl();
        imageDownloader.downloadImage();

    }


}
