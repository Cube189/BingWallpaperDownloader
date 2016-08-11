package me.gmur.bingImageDownloader;

import java.io.File;

/**
 * Downloads image from the specified source.
 * <p>
 * Different objects implementing the interface can download images from different sources.
 */
public interface ImageDownloader {
    /**
     * Returns the downloaded image.
     * @return File which contains the downloaded image.
     */
    File getImage();
}
