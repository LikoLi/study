import org.joda.time.DateTime;

import java.util.Date;

/**
 * JodaTest
 *
 * @author liko
 * @date 2019/11/15
 */
public class JodaTest {
    public static void main(String[] args) {
        Date date = new Date();
//        System.out.println(date);
//        System.out.println(DateTime.now().dayOfWeek().roundFloorCopy().toDate());
        System.out.println(DateTime.now().getDayOfMonth());
    }
}
