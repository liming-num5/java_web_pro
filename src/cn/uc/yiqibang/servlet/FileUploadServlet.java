package cn.uc.yiqibang.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   public void addUserPhoto(HttpServletRequest request,HttpServletResponse response){
	   //当用户请求FileUploadServlet，并且请求的action=addUserPhoto时调用
	   
	   //对于文件的处理，我们不能使用request.getParameter()来接收文件，需要使用磁盘工厂和文件上传类
	   
	   //首先创建磁盘工厂
	   DiskFileItemFactory factory=new DiskFileItemFactory();
	   factory.setRepository(new File("D://"));//设置磁盘工厂的库--本地的一个文件夹
	   factory.setSizeThreshold(50*1024*1024);//50M--缓存磁盘的大小--以byte为单位

	   //创建文件上传的servlet工具类对象
	   ServletFileUpload upload=new ServletFileUpload(factory);
	   upload.setFileSizeMax(-1);//上传的单个文件的大小    -1表示无限制
	   upload.setHeaderEncoding("UTF-8");
	   upload.setSizeMax(50*1024*1024);//单次上传所有文件的总大小
	   boolean result=false;
	   String saveName="";
	   try {
		   //这里的fileItem类型指的是请求参数==文件+属性
		List<FileItem> files = upload.parseRequest(request);//从请求里面获取所有的参数（属性+文件）
		Iterator<FileItem> it = files.iterator();
		while(it.hasNext()){
			FileItem file=it.next();
			if(file.isFormField()){//通过file.isFormFild()判断该参数是不是数值属性，如果是文件参数，返回false
				System.out.println("数值参数："+file.getFieldName());//通过file.getFieldName()获取属性名
			    System.out.println(file.getString("UTF-8"));//file.getString("UTF-8")以固定编码格式获取属性值
			}else{
				//System.out.println("文件参数,名称为:"+file.getName());//通过file.getName()获取文件名
				String fileName=file.getName();
				//如果获取到文件参数的文件名
				if(fileName!=null){
					//将文件保存写入到当前项目的某个目录--img
					//获取项目中某个资源文件夹的真实/绝对路径，使用servletContext对象.getRealPath(资源文件夹名)
					String fileImgPath = request.getServletContext().getRealPath("img");
					/*File imgPath = new File(fileImgPath);
					if(!imgPath.exists()){
						imgPath.mkdirs();
					}*/
				    System.out.println("要存放文件的真实路径为："+fileImgPath);
//				    C:\Users\12644\workspace_eclipse_jee\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\java_web_pro\img\img1.jpg
				   
				    //修改保存的文件的文件名为：当前时间+后缀
				    //当前时间获取：new Date().getTime()    /   System.currentTimeMillis
				    long time = System.currentTimeMillis();
				    //获取文件后缀
				    String nextFix = fileName.substring(fileName.lastIndexOf("."));
				    saveName=time+nextFix;//文件名称
				    String fileRealPath = fileImgPath+File.separator+saveName;
				    File file1=new File(fileRealPath);//文件的全路径所表示的File对象-文件
				    
				    file.write(file1);//将file的文件数据写入对应的文件目录对应的文件中
				    result=true;
				}else{
					System.out.println("没有文件");
				}
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("上传失败");
		e.printStackTrace();
	}
	 try {
		response.getWriter().println("{\"retMsg\":"+result+",\"imgName\":\""+saveName+"\"}");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
   }

   
   
}
