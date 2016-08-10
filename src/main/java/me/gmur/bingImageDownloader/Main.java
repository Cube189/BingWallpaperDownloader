package me.gmur.bingImageDownloader;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File image = new BingImageDownloader().getImage();

        WallpaperSetter.setWallpaperAs(image);
    }
}
