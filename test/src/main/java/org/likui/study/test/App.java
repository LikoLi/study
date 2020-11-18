package org.likui.study.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static final SimpleDateFormat BONDEXECUTION_TRADE_TIME_FOMATTER = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

    public static void main(String[] args) throws ParseException {
//        System.out.println( "Hello World!" );
//        long current = System.currentTimeMillis();
//        System.out.println(current);
//        String formatNumber = formatNumber(current % 1000, 3);
//        System.out.println(formatNumber);
//        Long time = parseToTimestamp(BONDEXECUTION_TRADE_TIME_FOMATTER, "00:00:00");
//        System.out.println(time);
//        time = parseToTimestamp(BONDEXECUTION_TRADE_TIME_FOMATTER, "00:00:01");
//        System.out.println(time);
//        time = parseToTimestamp(BONDEXECUTION_TRADE_TIME_FOMATTER, "00:00:02");
//        System.out.println(time);
//        time = parseToTimestamp(BONDEXECUTION_TRADE_TIME_FOMATTER, "00:00:03");
//        System.out.println(time);
//        time = parseToTimestamp(BONDEXECUTION_TRADE_TIME_FOMATTER, "00:00:04");
//        System.out.println(time);
//        long time = parseToTimestamp(BONDEXECUTION_TRADE_TIME_FOMATTER, "00:00:05");
//        System.out.println(time);
//        Long date = parseToTimestamp(DATE_FORMAT, "20170101");
//        System.out.println(date);
//        System.out.println(time + date);
//
//        System.out.println(parseToTimestamp(DATE_TIME_FORMAT, "20170101 00:00:05"));
//        new Thread(() -> {
//
//        });
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        Object collect = list.stream().filter((a) -> {
//            System.out.println(a);
//            return (int) a > 2;
//        }).collect(Collectors.toList());
//        System.out.println(collect);
        Map<String, Integer> map = new HashMap<>();
//        list.stream().forEach(a -> map.put((String) a, (Integer) a));
//        App::key(123);
        list.stream().forEach(App::key);

        System.out.println("123" + null);

        list.set(0, null);
        System.out.println("123" + list.get(0));
    }


    public static String key(Object a) {
        return "A" + a;
    }

    public static void key1(Object a) {
        System.out.println("A" + a);
    }

    private static String formatNumber(long value, int len) {
        return String.format("%0" + len + "d", value);
    }

    /**
     * 将时间格式化为时间戳
     *
     * @param formatter
     * @param dateStr
     * @return
     */
    public static synchronized Long parseToTimestamp(SimpleDateFormat formatter, String dateStr) throws ParseException {
        Date date = formatter.parse(dateStr);
        if (null != date) {
            return date.getTime();
        }
        return null;
    }
}
