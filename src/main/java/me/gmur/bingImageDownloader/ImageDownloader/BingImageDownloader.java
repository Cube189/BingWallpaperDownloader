package me.gmur.bingImageDownloader.imageDownloader;

import me.gmur.bingImageDownloader.util.Log;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.Arrays;

/**
 * <code>BingImageDownloader</code> is responsible
 * for downloading and saving
 * (with use of the <code>{@link ImageSaver}</code> method)
 * the current Bing Image of The Day.
 *
 * @see ImageDownloader
 */
public final class BingImageDownloader implements ImageDownloader {
    private static final Logger LOG = Log.getLoggerForClass("BingImageDownloader");
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

        InputStream input = null;
        ByteArrayOutputStream output = null;

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        try {
            try {
                LOG.info("Getting image data from \'" + imageAddress.toString() + "\'...");

                input = new BufferedInputStream(imageAddress.openStream());
                output = new ByteArrayOutputStream();

                int inputLength;
                while ((inputLength = input.read(buffer)) != -1) {
                    output.write(buffer, 0, inputLength);
                }

                imageContents = output.toByteArray();
            } finally {
                assert output != null;
                output.close();
                input.close();
            }
        } catch (IOException e) {
            LOG.error("Fetching image data failed with an error " + Arrays.toString(e.getStackTrace()));
        }

        return imageContents;
    }
}
