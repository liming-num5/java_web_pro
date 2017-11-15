package cn.uc.yiqibang.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求参数action，明确用户到底要请求servlet的哪一个操作（方法）
		String action=request.getParameter("action");
		if(action==null||"".equals(action)){
		    //没有获取到action，说明用户请求的时候没有添加action参数
			response.getWriter().println("请求参数非法，必须携带action表明意图！");
		}else{
			//在BaseServlet的子类中，已经声明了相关的操作方法，在这里需要根据action进行调用
		    //使用反射机制，获取到action方法，然后调用就可以了
			try {
				//使用当前的类名.getMethod（参数）来获得某个方法--param1为方法名，param2为形参类型
				Method method = this.getClass().getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
				//方法调用--param1为调用方法的对象，param2实参
				method.invoke(this,request,response);
				
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("请求参数值错误，没有该操作！");
			} 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
