package me.gmur.bingImageDownloader;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

// Thanks for the hint, Stack Overflow :)   http://stackoverflow.com/a/22819371/4064267
public class WallpaperSetter {
    final static String imagePath = "bingimage.jpg";

    public static void setWallpaper() {
        System.out.println("Setting wallpaper to " + imagePath);

        User32.INSTANCE.SystemParametersInfo(0x0014, 0, imagePath, 1);
    }

    public static interface User32 extends Library {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }
}
