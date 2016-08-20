package me.gmur.bingImageDownloader.wallpaperSetter;

import me.gmur.bingImageDownloader.util.OsChecker;
import me.gmur.bingImageDownloader.util.OsChecker.OsType;

import java.io.File;
import java.io.IOException;

/**
 * <code>WallpaperSetter</code> attempts to set the desktop background image
 * to the downloaded image.
 */
public class WallpaperSetter {
    private static WallpaperSetter instance = new WallpaperSetter();
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
        setWallpaper();
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
            wallpaperSettingLogicForWindows();
        } else if (osType == OsType.OSX) {
            wallpaperSettingLogicForMac();
        } else {
            wallpaperSettingLogicForLinux();
        }
    }

    /**
     * Executes the logic chosen by
     * <code>{@link WallpaperSetter#chooseWallpaperSettingLogic()}</code>
     */
    private void setWallpaper() {
        try {
            Runtime.getRuntime().exec(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies logic needed to set a wallpaper image
     * if the OS appears to be Windows.
     */
    private void wallpaperSettingLogicForWindows() {
        // According to http://www.windows-commandline.com/change-windows-wallpaper-command-line/
        script = new String[]{
                "reg",
                "add \"HKEY_CURRENT_USER\\Control Panel\\Desktop\" /v Wallpaper /t REG_SZ /d" + wallpaperImage.getAbsolutePath() + "/f",
                "RUNDLL32.EXE user32.dll,UpdatePerUserSystemParameters"
        };
    }

    /**
     * Applies logic needed to set a desktop background image
     * if the OS appears to be OS X/macOS.
     */
    private void wallpaperSettingLogicForMac() {
        // According to http://stackoverflow.com/a/22278487
        script = new String[]{
                "osascript",
                "-e", "tell application \"Finder\"",
                "-e", "set desktop picture to POSIX file \"" + wallpaperImage.getAbsolutePath() + "\"",
                "-e", "end tell"
        };
    }

    /**
     * Applies logic needed to set a desktop background picture
     * if the OS appears to be other than Windows or OS X/macOS
     * (possibly a Linux distro).
     */
    private void wallpaperSettingLogicForLinux() {
        // According to http://askubuntu.com/a/69500
        // This method currently works only on GNOME-based desktop environments
        script = new String[]{
                "gsettings",
                "set org.gnome.desktop.background picture-uri file://" + wallpaperImage.getAbsolutePath()
        };
    }
}
