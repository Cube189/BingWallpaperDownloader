package me.gmur.bingImageDownloader;

final class OsChecker {
    private static OsChecker instance = new OsChecker();
    private String osName;
    enum OsType {
        WINDOWS,
        OSX,
        LINUX
    }

    private OsChecker() {
    }

    public static OsChecker getInstance() {
        return instance;
    }

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

    private boolean isWindows() {
        return osName.contains("Windows");
    }

    private boolean isMac() {
        return osName.contains("Mac");
    }
}
