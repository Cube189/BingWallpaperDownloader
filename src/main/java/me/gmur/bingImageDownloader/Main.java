package me.gmur.bingImageDownloader;

import me.gmur.bingImageDownloader.imageDownloader.BingImageDownloader;
import me.gmur.bingImageDownloader.imageDownloader.ImageDownloader;
import me.gmur.bingImageDownloader.wallpaperSetter.WallpaperSetter;

import java.io.File;

public class Main {
    public static void main(final String[] args) {
        ImageDownloader imageDownloader = new BingImageDownloader();
        File image = imageDownloader.getImage();

        WallpaperSetter.getInstance().setTo(image);
    }
}
