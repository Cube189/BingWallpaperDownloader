package me.gmur.bingImageDownloader.imageDownloader;

import me.gmur.bingImageDownloader.util.Flags;

import java.io.*;
import java.net.URL;

/**
 * <code>BingImageDownloader</code> is responsible
 * for downloading and saving
 * (with use of the <code>{@link ImageSaver}</code> method)
 * the current Bing Image of The Day.
 *
 * @see ImageDownloader
 */
public final class BingImageDownloader implements ImageDownloader {
    private final String imagePath = Flags.IMAGE_FILE_LOCATION;
    private URL imageAddress;

    /**
     * Fetches the Bing Image of The Day.
     *
     * @return File which contains the image.
     */
    public File getImage() {
        imageAddress = new BingJsonProcessor().getImageAddress();
        byte[] imageData = fetchImageData();

        return ImageSaver.saveImageAndGetFile(imageData);
    }

    /**
     * Fetches the contents of the image's JPG file.
     *
     * @return Image data.
     */
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
