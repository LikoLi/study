package powermock.helloworld;

public class FileItem {

    private String path;

    public String getFilePath() {
        return path;
    }

    public String getPayloadName() {
        String pathWithName = getFilePath();
        try {
            int index = pathWithName.lastIndexOf("/");
            return pathWithName.substring(index + 1);
        } catch (Exception e) {
            return pathWithName;
        }
    }
}
