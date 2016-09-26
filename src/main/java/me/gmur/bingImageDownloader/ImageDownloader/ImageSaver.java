package me.gmur.bingImageDownloader.imageDownloader;

import me.gmur.bingImageDownloader.util.Flags;
import me.gmur.bingImageDownloader.util.Log;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * <code>ImageSaver</code> is a utility class which
 * contains methods needed to create a local file
 * and writing the downloaded image's data to it.
 */
final class ImageSaver {
    private static final String FILENAME = Flags.IMAGE_FILE_LOCATION;
    private static final Logger LOG = Log.getLoggerFor(ImageSaver.class);

    private ImageSaver() {
    }

    /**
     * Writes the image file's downloaded content to a local file.
     *
     * @param _imageData Downloaded content of the image file.
     * @return File which contains the image
     */
    public static File saveImageAndGetFile(final byte[] _imageData) {
        FileOutputStream writer = null;

        try {
            try {
                LOG.info(String.format("Writing image data to \'%s\'", FILENAME));

                writer = new FileOutputStream(FILENAME);
                writer.write(_imageData);
            } finally {
                assert writer != null;
                writer.close();
            }
        } catch (IOException e) {
            LOG.error(String.format("Saving file failed with an error \'%s\'", Arrays.toString(e.getStackTrace())));
        }

        return new File(FILENAME);
    }
}
