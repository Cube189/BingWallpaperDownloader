package me.gmur.bingImageDownloader;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.URL;

public final class BingImageDownloader implements ImageDownloader {
    private final String imagePath = "bingimage.jpg";
    private URL imageAddress;

    private BingImageDownloader() {
    }

    // TODO: Implement external properties file support
    public static BingImageDownloader createInstance() {
        return new BingImageDownloader();
    }

    public File getImage() {
        imageAddress = new BingJsonProcessor().getImageAddress();
        byte[] imageData = fetchImageData();

        ImageSaver.saveImageDataTo(imageData, imagePath);

        return new File(imagePath);
    }

    private byte[] fetchImageData() {
        byte[] imageContents = null;

        InputStream input;
        ByteArrayOutputStream output;
        byte[] buffer = new byte[1024];

        try {
            System.err.println("INFO: Getting image data from \'" + imageAddress.toString() + "\'...");

            input = new BufferedInputStream(imageAddress.openStream());
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
