package example11_asynchronous;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

//不需要注解@WebListener
public class MyAsyncListener implements AsyncListener{

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("onComplete");
		
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("onTimeout");
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("onError");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("onStartAsync");
	}

}
