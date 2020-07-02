package example20_converter_and_formatter;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class MyRegistrar implements FormatterRegistrar{
	private String datePattern;
	public MyRegistrar(String datePattern) {
		this.datePattern=datePattern;
	}
	@Override
	public void registerFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
		registry.addFormatter(new DateFormatter(datePattern));
		//register more formatter here
	}

}
