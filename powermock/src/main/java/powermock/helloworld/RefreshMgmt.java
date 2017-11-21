package powermock.helloworld;

public class RefreshMgmt {
    private static final String MSG_DOWNLOAD_FAILED = Messages.getString("RefreshThread.0");

    public boolean downloadFiles(String path) {
        return download(path);
    }

    private boolean download(String localPath) {
        return false;
    }
}

class Messages {
    public static String getString(String args) {
        throw new RuntimeException("123");
    }
}
