package me.gmur.bingImageDownloader;

import com.sun.istack.internal.NotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <code>ImageSaver</code> is a utility class which
 * contains methods needed to create a local file
 * and writing the downloaded image's data to it.
 */
final class ImageSaver {
    private ImageSaver() {
    }

    /**
     * Writes the image file's downloaded content to a local file.
     *
     * @param _imageContents Downloaded content of the image file.
     * @param _filename      Name of the newly created image file.
     */
    public static void saveImageDataTo(final byte[] _imageContents, @NotNull final String _filename) {
        FileOutputStream writer = null;

        try {
            System.err.println("INFO: Writing image content to \'" + _filename + "\'...");

            writer = new FileOutputStream(_filename);
            writer.write(_imageContents);

            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
