package cz.churchcrm.testframework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
    private static final String OLD_FORMAT = "yyyy-mm-dd";
    private static final String NEW_FORMAT = "mm-dd-yy";

    public static String changeDateFormat(String oldDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDate);
        sdf.applyPattern(NEW_FORMAT);

        return sdf.format(d);
    }
}
