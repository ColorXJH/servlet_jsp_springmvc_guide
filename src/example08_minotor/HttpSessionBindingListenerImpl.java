package example08_minotor;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@WebListener
public class HttpSessionBindingListenerImpl implements HttpSessionBindingListener{
	private String name;
	private String id;
	private int age;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	//该监听器会在httpsession属性绑定和解绑时在控制台答应信息
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		String attributeName=event.getName();
		System.out.println(attributeName+" valueBound");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		String attributeName=event.getName();
		System.out.println(attributeName+" valueUnbound");
	}

}
