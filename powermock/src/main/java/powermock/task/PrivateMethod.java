package powermock.task;

import java.util.ArrayList;
import java.util.List;

public class PrivateMethod {
    private List<String> getList(String args) {
        List<String> result = new ArrayList<>();
        result.add(args);
        return result;
    }

    public void sout() {
        System.out.println(getList("abccc"));
    }
}
