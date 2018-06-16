package DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public String dateFormat() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("yyyyÄêMMÔÂddÈÕ HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());  
	    String dateString = sdf.format(new Date());
	    return dateString.toString();
	}
	 
}
