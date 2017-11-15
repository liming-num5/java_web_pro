package cn.uc.yiqibang.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class WriteResultToClient {
    
	public static void writeMethod(Object obj,HttpServletResponse response){
		PrintWriter out=null;
		try{
		out = response.getWriter();
		}catch(Exception e){}
		String jsonObj = JSONObject.fromObject(obj).toString();
		out.println(jsonObj);
		out.flush();
		out.close();
	}
}
