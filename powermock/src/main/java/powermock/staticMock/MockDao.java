package powermock.staticMock;

public class MockDao {
    public void call() {
        throw new UnsupportedOperationException();
    }

    public static void staticMethod() {
        throw new RuntimeException("dao ...");
    }
}
