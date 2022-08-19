import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CNPValidator {
    /**
     * The standard length of a CNP.
     */
    public static final int LENGTH = 13;

    private static final DateFormat CNP_DATE_FORMAT = new SimpleDateFormat("yyMMdd");

    private static int[] CONTROL_VALUES = new int[]{
            2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9
    };

    private static int[] YEAR_OFFSET = new int[]{
            0, 1900, 1900, 1800, 1800, 2000, 2000
    };


    /**
     * Returns if the given string represents a valid CNP for the given birthdate.
     * The 2nd and the 3rd digits represent the last two digits from the year birthdate,
     * the 4th and 5th represent the month and the 7th and 8th the day.
     */
    public static boolean validateBirthdate(String cnp, Date birthdate) {
        return cnp.length() > 6 && cnp.substring(1, 7).equals(CNP_DATE_FORMAT.format(birthdate));
    }

    /**
     * Returns if the given string represents a valid CNP.
     */
    public static boolean validateGender(String cnp, boolean male, Date birthdate) {
        if (cnp == null || cnp.length() < 1 || !Character.isDigit(cnp.charAt(0)))
            return false;
        int g1 = Character.digit(cnp.charAt(0), 10);
        Calendar c = new GregorianCalendar();
        c.setTime(birthdate);
        int g2 = c.get(Calendar.YEAR) < 2000
                ? male ? 1 : 2
                : male ? 5 : 6;
        return g1 == g2;
    }

    public static String create(boolean male, Date birthdate, int regionId, int birthRegisterNo) {
        StringBuffer result = new StringBuffer("0000000000000");

        Calendar c = new GregorianCalendar();
        c.setTime(birthdate);

        if (c.get(Calendar.YEAR) < 1900) {
            result.setCharAt(0, male ? '3' : '4');
        } else if (c.get(Calendar.YEAR) < 2000) {
            result.setCharAt(0, male ? '1' : '2');
        } else {
            result.setCharAt(0, male ? '5' : '6');
        }
        result.replace(1, 7, CNP_DATE_FORMAT.format(birthdate));

        result.replace(7, 9, new Integer(Math.abs(regionId) % 100).toString());
        result.replace(9, 12, new Integer(Math.abs(birthRegisterNo) % 1000).toString());


        return result.toString();
    }
}