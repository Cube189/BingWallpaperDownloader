package me.gmur.bingImageDownloader.util;


import org.apache.log4j.Logger;

public final class Log {
    public static Logger getLoggerFor(final Class _class) {
        return Logger.getLogger(_class);
    }
}
