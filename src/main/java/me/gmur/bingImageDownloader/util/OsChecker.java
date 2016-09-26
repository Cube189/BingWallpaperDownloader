package me.gmur.bingImageDownloader.util;

import me.gmur.bingImageDownloader.wallpaperSetter.WallpaperSetter;
import org.apache.log4j.Logger;

/**
 * Tries to recognize the end user's operating system family
 * in order to let other classes know which set of logic to apply.
 *
 * @see WallpaperSetter#chooseWallpaperSettingLogic()
 */
public final class OsChecker {
    private static final Logger LOG = Log.getLoggerFor(OsChecker.class);
    private static final OsChecker INSTANCE = new OsChecker();
    private String osName;

    private OsChecker() {
    }

    /**
     * Returns the instance of <code>OsChecker</code>.
     * <br />
     * Since there's only one host OS which the program may know about,
     * it makes sense to implement <code>OsChecker</code> as a singleton.
     *
     * @return <code>OsChecker</code> instance.
     */
    public static OsChecker getInstance() {
        return INSTANCE;
    }

    /**
     * Recognizes the operating system family
     * and returns a corresponding value.
     *
     * @return OS family name.
     */
    public OsType determineOsType() {
        osName = System.getProperty("os.name");

        LOG.info(String.format("OS running recognized as \'%s\'", osName));

        if (isWindows()) {
            return OsType.WINDOWS;
        } else if (isMac()) {
            return OsType.OSX;
        } else {
            return OsType.LINUX;
        }
    }

    private boolean isWindows() {
        return osName.contains("Windows");
    }

    private boolean isMac() {
        return osName.contains("Mac");
    }

    public enum OsType {
        WINDOWS,
        OSX,
        LINUX
    }
}
