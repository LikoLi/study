package powermock.session2;

public class Static {

    public static String callStaticMethod1(String args) {
        return "mock_" + args;
    }

    public static String callStaticMethod2(int args) {
        return "mock_" + args;
    }
}
