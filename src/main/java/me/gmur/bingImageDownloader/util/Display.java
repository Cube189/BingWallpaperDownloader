package me.gmur.bingImageDownloader.util;

import java.awt.*;

public abstract class Display {
    static final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public static int getDisplayWidth() {
        return graphicsDevice.getDisplayMode().getWidth();
    }

    public static int getDisplayHeight() {
        return graphicsDevice.getDisplayMode().getHeight();
    }
}
