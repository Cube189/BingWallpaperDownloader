package me.gmur.bingImageDownloader;

import java.io.File;

/**
 * <code>ImageDownloader</code> interface is implemented by every class
 * which purpose is to download an image later used as a desktop wallpaper.
 * <br />
 * Notice that classes implementing the <code>ImageDownloader</code> interface
 * must have their constructor's access modifier set to <code>private</code>
 * and implement a static zero-argument (niladic) <code>createInstance()</code>
 * method used for instantiating their objects:
 * <pre>
 *     public class MyImageDownloader implements ImageDownloader {
 *         // ...
 *         private MyImageDownloader {}
 *         public static ImageDownloader createInstance() {
 *             return new MyImageDownloader();
 *         }
 *         // ...
 *     }
 * </pre>
 */
public interface ImageDownloader {
    /**
     * Returns the downloaded image.
     *
     * @return File which contains the downloaded image.
     */
    File getImage();
}
