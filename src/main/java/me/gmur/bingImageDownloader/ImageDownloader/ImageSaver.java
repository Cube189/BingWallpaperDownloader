package me.gmur.bingImageDownloader.imageDownloader;

import me.gmur.bingImageDownloader.util.Flags;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <code>ImageSaver</code> is a utility class which
 * contains methods needed to create a local file
 * and writing the downloaded image's data to it.
 */
final class ImageSaver {
    private static final String filename = Flags.IMAGE_FILE_LOCATION;

    private ImageSaver() {
    }

    /**
     * Writes the image file's downloaded content to a local file.
     *
     * @param _imageData Downloaded content of the image file.
     */
    public static File saveImageAndGetFile(final byte[] _imageData) {
        FileOutputStream writer = null;

        try {
            try {
                System.err.println("INFO: Writing image content to \'" + filename + "\'...");

                writer = new FileOutputStream(filename);
                writer.write(_imageData);
            } finally {
                assert writer != null;
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(filename);
    }
}
