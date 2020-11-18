package demo.evenchecker;

/**
 * EvenGenerator
 *
 * @author liko
 * @date 2019/11/1
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {
        System.out.println(Thread.currentThread().getName() + " : [BEGIN] " + currentEvenValue);
        ++currentEvenValue;
        System.out.println(Thread.currentThread().getName() + " : [MID] " + currentEvenValue);
        ++currentEvenValue;
        System.out.println(Thread.currentThread().getName() + " : [END] " + currentEvenValue);
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(), 2);
    }
}
