package me.gmur.bingImageDownloader;

public class Main {


    private static String savedImageFilename = "bingimage.jpg";

    public static void main(final String[] args) {

        BingImageDownloader imageDownloader = new BingImageDownloader();
        imageDownloader.downloadImage();

        WallpaperSetter.setWallpaper();
    }


}
