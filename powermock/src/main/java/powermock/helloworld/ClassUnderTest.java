package powermock.helloworld;

import java.io.File;

public class ClassUnderTest {

    Dependency d = new Dependency();

    public boolean callInternalInstance(String path) {
        File file = new File(path);
        return file.exists();
    }


    public boolean callFinalMethod(Dependency d) {
        return d.isAlive();
    }

    public boolean callPrivateMethod() {
        return isAlive();
    }

    private boolean isAlive() {
        return true;
    }

}


class Dependency {
    public final boolean isAlive() {
        //do something
        return true;
    }
}

