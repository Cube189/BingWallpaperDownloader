package me.gmur.bingImageDownloader.util;


import org.apache.log4j.Logger;

public final class Log {
    public static Logger getLoggerForClass(final String _className) {
        return Logger.getLogger(_className);
    }
}
