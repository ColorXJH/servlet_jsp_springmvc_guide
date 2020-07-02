package example20_converter_and_formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>{
	private String datePattern;
	private SimpleDateFormat sdf;
	public  DateFormatter(String datePattern) {
		this.datePattern=datePattern;
		sdf=new SimpleDateFormat(datePattern);
		sdf.setLenient(false);
	}
	@Override
	public String print(Date object, Locale locale) {
		// TODO Auto-generated method stub
		return sdf.format(object);
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		try {
			return sdf.parse(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("formatter error!");
		}
	}

}

