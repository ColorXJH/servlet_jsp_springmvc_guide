package example09_filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="DownloadCountFilter",urlPatterns= {"/*"},asyncSupported=true)
public class DownloadCounterFilter  implements Filter{
	ExecutorService es1=Executors.newSingleThreadExecutor();//执行器类生成单个的执行器服务
	Properties downloadLog;
	File logFile;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("download count filter init----- ");
		String appPath=filterConfig.getServletContext().getRealPath("/");
		logFile=new File("E:/logs","download.txt");
		if(!logFile.exists()) {//不存在当前目录则创建
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		downloadLog=new Properties();
		try {
			downloadLog.load(new FileReader(logFile));//创建property对象并读取该文件
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
			HttpServletRequest hRequest=(HttpServletRequest)request;
			final String uri=hRequest.getRequestURI();
			es1.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String property=downloadLog.getProperty(uri);
					if(property==null) {
						downloadLog.setProperty(uri, "1");
					}else {
						int count=0;
						count=Integer.parseInt(property);
						count++;
						downloadLog.setProperty(uri, Integer.toString(count));
					}
					
					try {
						downloadLog.store(new FileWriter(logFile), "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		es1.shutdown();//销毁执行器服务
	}

}
