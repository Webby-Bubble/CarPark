package utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {//额外工具包
    public static String now(){
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
    }
    public static long totime(String enterdate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").parse(enterdate);
        return date.getTime();
    }
    public static String getTimeFormatValue(long time){
        time = time/1000;
        long s = time%60;
        long m = (time/60)%60;
        long h=time/3600;
        DecimalFormat f = new DecimalFormat("00");
        return f.format(h)+":"+f.format(m)+":"+f.format(s);
    }
}
