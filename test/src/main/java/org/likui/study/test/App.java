package org.likui.study.test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        long current = System.currentTimeMillis();
        System.out.println(current);
        String formatNumber = formatNumber(current % 1000, 3);
        System.out.println(formatNumber);
    }

    private static String formatNumber(long value, int len) {
        return String.format("%0" + len + "d", value);
    }
}
