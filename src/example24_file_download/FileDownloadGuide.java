package example24_file_download;

public class FileDownloadGuide {
		//文件下载
			//像图片或html文件这样的静态资源，在浏览器中打开正确的url即可下载，只要该资源是在应用程序目录下，或者在其字目录下，而不是放在web-inf下
			//servlet/jsp容器就会将资源发送到浏览器，然而有时候数据保存在应用程序目录之外，或者是保存在某一数据库中，或者有时需要控制它的访问权限，
			//如果出现上述任意一种情况都需要编程来发送资源
				//简而言之通过编程进行的文件下载，使你可以有选择的将文件发送到浏览器
			
		//文件下载概览
			//为了将文件这样的资源发送到浏览器，需要在控制器中完成以下工作：
				//1对请求处理方法使用void返回类型，并在方法中田间HttpServletResponse参数
				//2：将响应的内容类型设置为文件的内容类型,Content-type标题在某个实体body中定义数据的类型，并包含媒体类型和子类型标识符，如不清楚类型
					//并希望浏览器始终显示为”另存为“对话框，则将他设置为:application/octet-stream,这个值是不区分大小写的
				//3：添加一个名为Content-Disposition的http响应标题，并赋值attachment;filename=fileName,这里的fileName是默认的文件名，应该出现在
					//文件下载对话框中，它常与文件名同名，但也并不都是如此
			//将一个文件发送到浏览器：
				//FileInputStream fis=new FileInputStream(file);
				//BufferedInputStream bis=new BufferedInputStream(fis);
				//byte[] bytes=new byte[bis.avaliable()];
				//response.setContentType(contentType);
				//response.setHeader("Content-Disposition","attachment;filename="+fileName)
				//OutputStream os=response.getOutputStream();
				//bis.read(bytes);
				//os.write(bytes);
					//首先读取文件作为一个fileinputstream,并将文件加载到一个字节数组，随后通过response获取outputstream
					//并调用write方法传入字节数组
		//小结：在控制器中编写文件下载
	
}
