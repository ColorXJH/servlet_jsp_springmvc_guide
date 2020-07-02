package example06_customize_tag;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateFormaterTag extends SimpleTagSupport {
	private String header;
	private String items;
	
	public void setHeader(String header) {
		this.header=header;
	}
	public void setItems(String items) {
		this.items=items;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspContext jspContext =getJspContext();
		JspWriter out=jspContext.getOut();
		out.print("<table style='border:1px solid green'>\n"
				+ "<tr><td><span style='font-weight:bold'>"
				+ header + "</span></td></tr>\n");
		StringTokenizer tokenizer=new StringTokenizer(items,",");
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			out.print("<tr><td>" + token + "</td></tr>\n");
		}
		out.print("</table>");
	}
}
