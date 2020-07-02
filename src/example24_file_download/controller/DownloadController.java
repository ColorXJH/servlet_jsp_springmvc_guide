package example24_file_download.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class DownloadController {
	@RequestMapping("/downloadfile")
	public void sendFileToClient(HttpServletRequest request,HttpServletResponse response) {
			if(request.getHeader("referer")==null) {
				System.out.println("防止仅在浏览器中输入网址就能下载图片的情况发生");
				return ;
			}
		    String path=request.getServletContext().getRealPath("/jsp/example24");
			File file=new File(path, "picture1.jpg");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=picture1.jpg");
			byte[] bytes=new byte[1024];
			FileInputStream fileInputStream=null;
			BufferedInputStream bufferedInputStream=null;
			//java7 twr
			try(BufferedInputStream bfs=new BufferedInputStream(new FileInputStream(file))){
				OutputStream os=response.getOutputStream();
				int i=bfs.read(bytes);
				while(i!=-1) {
					os.write(bytes,0,i);
					i=bfs.read(bytes);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*try {
				fileInputStream=new FileInputStream(file);
				bufferedInputStream=new BufferedInputStream(fileInputStream);
				OutputStream outputStream=response.getOutputStream();
				int i=bufferedInputStream.read(bytes);
				while(i!=-1) {
					outputStream.write(bytes,0,i);
					i=bufferedInputStream.read(bytes);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(bufferedInputStream!=null) {
					try {
						bufferedInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(fileInputStream!=null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}*/
				
	}
}
