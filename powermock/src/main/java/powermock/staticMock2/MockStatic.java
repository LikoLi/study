package powermock.staticMock2;

public class MockStatic {

    public int mockStatic() {
        MockDao.staticMethod();
        if (true) {
            throw new RuntimeException("llala");
        }
        return 1;
    }

    public int mockStatic1() {
        int i = mockStatic();
        if (i == 0) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
        return 10086 + i;
    }
}
