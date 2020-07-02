package example20_converter_and_formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyConverter implements Converter<String,Date>{
	private String datePattern;
	public  MyConverter(String datePattern) {
		this.datePattern=datePattern;
		System.out.println("==----myconverter has created---===");
	}
	@Override
	public Date convert(String arg0) {
		SimpleDateFormat sdf=new SimpleDateFormat(datePattern);
		sdf.setLenient(false);
		try {
			return sdf.parse(arg0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("invalidate date !!!");
		}
		
	}

}
