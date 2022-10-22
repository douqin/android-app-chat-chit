package JavaCode.Clib;

import java.util.Date;

public class mSystem {
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    public static Date datecurr() {
         return java.util.Calendar.getInstance().getTime();
    }
}
