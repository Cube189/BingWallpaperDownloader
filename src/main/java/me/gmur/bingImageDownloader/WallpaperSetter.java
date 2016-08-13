package me.gmur.bingImageDownloader;

import me.gmur.bingImageDownloader.OsChecker.OsType;

import java.io.File;
import java.io.IOException;

public class WallpaperSetter {
    private static WallpaperSetter instance = new WallpaperSetter();
    private File wallpaperImage;

    private WallpaperSetter() {
    }

    public static WallpaperSetter getInstance() {
        return instance;
    }

    public void setTo(File _image) {
        wallpaperImage = _image;

        System.out.println("INFO: Setting \'" + wallpaperImage.toString() + "\' as wallpaper...");
        setWallpaper();
    }

    private void setWallpaper() {
        OsType osType = OsChecker.getInstance().determineOsType();

        if (osType == OsType.WINDOWS) {
            setWallpaperWindows();
        } else if (osType == OsType.OSX) {
            setWallpaperMac();
        } else {
            setWallpaperLinux();
        }
    }

    private void setWallpaperWindows() {
        // TODO: do Windows code
        try {
            Runtime.getRuntime().exec("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setWallpaperMac() {
        // TODO: Do Mac code
        try {
            String as[] = {
                    "osascript",
                    "-e", "tell application \"Finder\"",
                    "-e", "set desktop picture to POSIX file \"" + wallpaperImage.getAbsolutePath() + "\"",
                    "-e", "end tell"
            };
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(as);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setWallpaperLinux() {
        // TODO: Do Linux code
        try {
            Runtime.getRuntime().exec("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}