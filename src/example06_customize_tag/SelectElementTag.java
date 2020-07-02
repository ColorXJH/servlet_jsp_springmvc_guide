package example06_customize_tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SelectElementTag extends SimpleTagSupport{
	private String[] countries= {"china","usa","japan","korea","russia"};
	
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspContext	JspContext=getJspContext();
		JspWriter jspWriter=JspContext.getOut();
		jspWriter.print("<select>\n");
		System.out.println("xixixi-------");
		for(int i=0;i<5;i++) {
			getJspContext().setAttribute("value", countries[i]);//默认为page scope
			getJspContext().setAttribute("text", countries[i]);
			getJspBody().invoke(null);
		}
		jspWriter.print("</select>\n");
	}

}
