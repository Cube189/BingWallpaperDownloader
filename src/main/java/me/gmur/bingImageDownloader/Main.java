package me.gmur.bingImageDownloader;

import java.io.File;

public class Main {
    public static void main(final String[] args) {
        ImageDownloader imageDownloader = BingImageDownloader.createWithProperties();
        File image = imageDownloader.getImage();

        WallpaperSetter.setTo(image);
    }
}
