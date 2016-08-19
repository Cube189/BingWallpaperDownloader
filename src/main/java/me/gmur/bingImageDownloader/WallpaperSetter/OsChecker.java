package me.gmur.bingImageDownloader.wallpaperSetter;

/**
 * Tries to recognize the end user's operating system family
 * in order to let other classes know which set of logic to apply.
 *
 * @see WallpaperSetter#chooseWallpaperSettingLogic()
 */
final class OsChecker {
    private static OsChecker instance = new OsChecker();
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
        return instance;
    }

    /**
     * Recognizes the operating system family
     * and returns a corresponding value.
     *
     * @return OS family name.
     */
    public OsType determineOsType() {
        osName = System.getProperty("os.name");

        System.out.println("INFO: OS running recognized as " + osName);

        if (isWindows()) {
            return OsType.WINDOWS;
        } else if (isMac()) {
            return OsType.OSX;
        } else {
            return OsType.LINUX;
        }
    }

    /**
     * Checks if OS name indicates a Windows-family OS.
     *
     * @return Boolean value stating whether the OS is Windows-type.
     */
    private boolean isWindows() {
        return osName.contains("Windows");
    }

    /**
     * Checks if OS name indicates a OS X-family OS.
     *
     * @return Boolean value stating whether the OS is OS X-type.
     */
    private boolean isMac() {
        return osName.contains("Mac");
    }

    public enum OsType {
        WINDOWS,
        OSX,
        LINUX
    }
}
