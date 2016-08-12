package me.gmur.bingImageDownloader;

import me.gmur.bingImageDownloader.OsChecker.OsType;

import java.io.File;

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
        setWallpaper();
    }

    private void setWallpaper() {
        OsType osType = OsChecker.getInstance().determineOsType();

        if (osType == OsType.WINDOWS) {
            // TODO: do Windows code
        } else if (osType == OsType.OSX) {
            // TODO: Do Mac code
        } else {
            // TODO: Do Linux code
        }
    }
}