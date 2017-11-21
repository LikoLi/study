package powermock.session3;

public class ClassUnderTest {
    public void methodTest() {
        System.out.println(IdGenerator.generateNewId());
    }

    public String pm() {
        String input = methodToMock("input");
        System.out.println(input);
        return input;
    }

    private String methodToMock(String input) {
        return "REAL VALUE = " + input;
    }
}
