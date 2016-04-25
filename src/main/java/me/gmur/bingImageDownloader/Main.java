package me.gmur.bingImageDownloader;

public class Main {


    private static String savedImageFilename = "bingimage.json";

    public static void main(String[] args) {

        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.getImageAddress();

    }


}
