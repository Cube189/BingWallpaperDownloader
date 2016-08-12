package me.gmur.bingImageDownloader;

import java.io.File;

public class Main {
    public static void main(final String[] args) {
        ImageDownloader imageDownloader = BingImageDownloader.createInstance();
        File image = imageDownloader.getImage();

        WallpaperSetter.getInstance().setTo(image);
    }
}
