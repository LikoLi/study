package powermock.staticMock;

public class MockStatic {
    private static MockDao mockDao = new MockDao();

    public static void mockStatic() {
        mockDao.call();
    }
}
