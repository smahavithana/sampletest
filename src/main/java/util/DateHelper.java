package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sampath on 27/08/2017.
 */
public class DateHelper {

    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    long unixTime = 0;
    Date myDate = null;

    public String getUnixDateTime(String date){
        try {
            myDate = formatter.parse(date);
            unixTime = myDate.getTime()/1000;

    } catch (ParseException e) {
        e.printStackTrace();
    }
    return Long.toString(unixTime);
    }
}
