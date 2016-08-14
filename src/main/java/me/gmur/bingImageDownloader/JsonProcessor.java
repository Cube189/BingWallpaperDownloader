package me.gmur.bingImageDownloader;

import java.net.URL;

/**
 * Downloads and processes a JSON file required by a specific implementation.
 */
public interface JsonProcessor {
    /**
     * Returns a URL object with URL pointing to the image to be downloaded.
     *
     * @return URL to the image to be downloaded.
     */
    URL getImageAddress();
}
