package me.gmur.bingImageDownloader;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.URL;

public class BingImageDownloader implements ImageDownloader {
    private final String imagePath = "bingimage.jpg";

    private BingImageDownloader() {
    }

    public static BingImageDownloader createWithProperties() {
        return new BingImageDownloader();
    }

    public File getImage() {
        URL imageAddress = new JsonParser().getImageAddress();
        byte[] imageData = fetchImageDataFrom(imageAddress);

        ImageSaver.saveImageDataTo(imageData, imagePath);

        return new File(imagePath);
    }

    private byte[] fetchImageDataFrom(@NotNull URL _imageAddress) {
        byte[] imageContents = null;

        InputStream input;
        ByteArrayOutputStream output;
        byte[] buffer = new byte[1024];

        try {
            System.err.println("INFO: Getting image data from \'" + _imageAddress.toString() + "\'...");

            input = new BufferedInputStream(_imageAddress.openStream());
            output = new ByteArrayOutputStream();

            int n;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }

            output.close();
            input.close();

            imageContents = output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageContents;
    }
}
