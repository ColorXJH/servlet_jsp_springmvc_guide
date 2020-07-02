package example06_customize_tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class MyFirstTag implements SimpleTag{
	JspContext JspContext;
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do tag---");
		JspContext.getOut().print("this is my first tag");
		
	}

	@Override
	public void setParent(JspTag parent) {
		// TODO Auto-generated method stub
		System.out.println("set Parent----");
	}

	@Override
	public JspTag getParent() {
		// TODO Auto-generated method stub
		System.out.println("getParent----");
		return null;
	}

	@Override
	public void setJspContext(JspContext pc) {
		// TODO Auto-generated method stub
		System.out.println("set jspContext------");
		JspContext=pc;//将由jsp容器中传入的jspContext对象赋值给变量
	}

	@Override
	public void setJspBody(JspFragment jspBody) {
		// TODO Auto-generated method stub
		System.out.println("setJspBody-----");
	}

}
