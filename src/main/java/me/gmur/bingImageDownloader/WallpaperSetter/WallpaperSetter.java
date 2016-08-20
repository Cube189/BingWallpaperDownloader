package me.gmur.bingImageDownloader.wallpaperSetter;

import me.gmur.bingImageDownloader.util.Log;
import me.gmur.bingImageDownloader.util.OsChecker;
import me.gmur.bingImageDownloader.util.OsChecker.OsType;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * <code>WallpaperSetter</code> attempts to set the desktop background image
 * to the downloaded image.
 */
public final class WallpaperSetter {
    private static final Logger LOG = Log.getLoggerForClass("WallpaperSetter");
    private static final WallpaperSetter INSTANCE = new WallpaperSetter();
    private File wallpaperImage;
    private String[] script;

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
        return INSTANCE;
    }

    /**
     * Sets the desktop background image.
     *
     * @param _image The downloaded image file.
     */
    public void setTo(final File _image) {
        wallpaperImage = _image;

        LOG.info("Setting " + wallpaperImage.toString() + " as wallpaper");
        setWallpaper();
    }

    private void setWallpaper() {
        chooseWallpaperSettingLogic();

        try {
            Runtime.getRuntime().exec(script);
        } catch (IOException e) {
            LOG.error("Executing wallpaper-setting script failed with an error " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void chooseWallpaperSettingLogic() {
        OsType osType = OsChecker.getInstance().determineOsType();

        if (osType == OsType.WINDOWS) {
            wallpaperSettingLogicForWindows();
        } else if (osType == OsType.OSX) {
            wallpaperSettingLogicForMac();
        } else {
            wallpaperSettingLogicForLinux();
        }
    }

    private void wallpaperSettingLogicForWindows() {
        // According to http://www.windows-commandline.com/change-windows-wallpaper-command-line/
        script = new String[]{
                "reg",
                "add \"HKEY_CURRENT_USER\\Control Panel\\Desktop\" /v Wallpaper /t REG_SZ /d" + wallpaperImage.getAbsolutePath() + "/f",
                "RUNDLL32.EXE user32.dll,UpdatePerUserSystemParameters"
        };
    }

    private void wallpaperSettingLogicForMac() {
        // According to http://stackoverflow.com/a/22278487
        script = new String[]{
                "osascript",
                "-e", "tell application \"Finder\"",
                "-e", "set desktop picture to POSIX file \"" + wallpaperImage.getAbsolutePath() + "\"",
                "-e", "end tell"
        };
    }

    private void wallpaperSettingLogicForLinux() {
        // According to http://askubuntu.com/a/69500
        // This method currently works only on GNOME-based desktop environments
        script = new String[]{
                "gsettings",
                "set org.gnome.desktop.background picture-uri file://" + wallpaperImage.getAbsolutePath()
        };
    }
}
