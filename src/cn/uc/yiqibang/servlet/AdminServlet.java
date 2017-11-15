package cn.uc.yiqibang.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public void adminLogin(HttpServletRequest request,HttpServletResponse response){
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String code=request.getParameter("code");
    	
    	System.out.println(username+"-"+password+"-"+code);
    	//获取验证码生成的4个正确的数组
    	HttpSession session = request.getSession();
    	String ccode = String.valueOf(session.getAttribute("ccode"));
    	
    	try{
	    	//将用户数入的验证码和正确的进行比较
	    	if(code!=null&&code.equals(ccode)){
	    		//产生的和用户输入的一样--验证码输对了
	    		System.out.println("登录");
	    		if("xiaoming".equals(username)&&"1234".equals(password)){
	    			response.sendRedirect(request.getContextPath()+"/index.jsp");
	    		}else{
	    			session.setAttribute("tip", "用户名或者密码错误");
	    			response.sendRedirect(request.getContextPath()+"/login.jsp");
	    		}
	    	}else{
	    		session.setAttribute("tip", "验证码错误");
	    		response.sendRedirect(request.getContextPath()+"/login.jsp?ifFirst=false");
	    	}
    	}catch(Exception e){
    		System.out.println("跳转失败");
    	}
    	
    }

}
