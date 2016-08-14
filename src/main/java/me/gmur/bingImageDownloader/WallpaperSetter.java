package me.gmur.bingImageDownloader;

import me.gmur.bingImageDownloader.OsChecker.OsType;

import java.io.File;
import java.io.IOException;

/**
 * <code>WallpaperSetter</code> attempts to set the desktop background image
 * to the downloaded image.
 */
public class WallpaperSetter {
    private static WallpaperSetter instance = new WallpaperSetter();
    private File wallpaperImage;

    private WallpaperSetter() {
    }

    /**
     * Returns the instance of <code>WallpaperSetter</code>.
     * <br />
     * Since there's only one host OS desktop which the program may know about,
     * it makes sense to implement <code>WallpaperSetter</code> as a singleton.
     *
     * @return <code>WallpaperSetter</code> instance.
     */
    public static WallpaperSetter getInstance() {
        return instance;
    }

    /**
     * Sets the desktop background image.
     *
     * @param _image The downloaded image file.
     */
    public void setTo(final File _image) {
        wallpaperImage = _image;

        System.out.println("INFO: Setting \'" + wallpaperImage.toString() + "\' as wallpaper...");
        chooseWallpaperSettingLogic();
    }

    /**
     * Chooses which type of logic to apply
     * in order to set a desktop background image.
     *
     * @see OsChecker
     */
    private void chooseWallpaperSettingLogic() {
        OsType osType = OsChecker.getInstance().determineOsType();

        if (osType == OsType.WINDOWS) {
            setWallpaperWindows();
        } else if (osType == OsType.OSX) {
            setWallpaperMac();
        } else {
            setWallpaperLinux();
        }
    }

    /**
     * Applies logic needed to set a wallpaper image
     * if the OS appears to be Windows.
     */
    private void setWallpaperWindows() {
        // TODO: do Windows code
        try {
            Runtime.getRuntime().exec("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies logic needed to set a desktop background image
     * if the OS appears to be OS X/macOS.
     */
    private void setWallpaperMac() {
        // TODO: Do Mac code
        try {
            String[] script = {
                    "osascript",
                    "-e", "tell application \"Finder\"",
                    "-e", "set desktop picture to POSIX file \"" + wallpaperImage.getAbsolutePath() + "\"",
                    "-e", "end tell"
            };
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies logic needed to set a desktop background picture
     * if the OS appears to be other than Windows or OS X/macOS
     * (possibly a Linux distro).
     */
    private void setWallpaperLinux() {
        // TODO: Do Linux code
        try {
            Runtime.getRuntime().exec("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
