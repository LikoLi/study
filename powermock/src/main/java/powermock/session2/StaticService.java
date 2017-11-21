package powermock.session2;

public class StaticService {
    public static void m1(String args) {
        System.out.println("mock_" + args);
    }

    public static final void m2(String args) {
        System.out.println("final mock_" + args);
    }
}
