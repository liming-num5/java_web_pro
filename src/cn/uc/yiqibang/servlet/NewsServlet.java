package cn.uc.yiqibang.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.uc.yiqibang.beans.TNews;
import cn.uc.yiqibang.dao.TNewsMapper;
import cn.uc.yiqibang.dao.impl.NewsMapperImpl;
import cn.uc.yiqibang.utils.ImgUtil;
import cn.uc.yiqibang.utils.Result;
import cn.uc.yiqibang.utils.WriteResultToClient;

@WebServlet("/NewsServlet")
public class NewsServlet extends BaseServlet {

	private static final long serialVersionUID = 2901354022165043875L;
	
	TNewsMapper newsDao=new NewsMapperImpl();
	
	public void getAllNews(HttpServletRequest request,HttpServletResponse response){
		Result result = newsDao.selectAll();
		WriteResultToClient.writeMethod(result, response);
	}
	
	public void adminGetAllNewsByPage(HttpServletRequest request,HttpServletResponse response){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		Result result = newsDao.selectAllByPage(pageNum);
		WriteResultToClient.writeMethod(result, response);
	}
	public void adminDeleteNewsById(HttpServletRequest request,HttpServletResponse response){
		int newsid=Integer.parseInt(request.getParameter("id"));
		Result result = newsDao.deleteByPrimaryKey(newsid);
		WriteResultToClient.writeMethod(result, response);
	}
	public void adminGetNewsByLike(HttpServletRequest request,HttpServletResponse response){
		String likeStr = request.getParameter("likeStr");
		Result result = newsDao.selectByLike("%"+likeStr+"%");
		WriteResultToClient.writeMethod(result, response);
	}
	
	public void adminInsertNews(HttpServletRequest request,HttpServletResponse response){
		String title=request.getParameter("title");
		String source=request.getParameter("source");
		String author =request.getParameter("author");
		String content =request.getParameter("content");
		
		//从content里面获取图片列表
		 List<String> list = ImgUtil.getImageSrc(content);  
		 String picturePath = ImgUtil.listToString(list, ',');
		 
		String editorValue=request.getParameter("editorValue");
		int  typeid=Integer.parseInt(request.getParameter("typeid"));
	    boolean ifHot=Boolean.parseBoolean(request.getParameter("ifHot"));
	    
	    TNews news=new TNews();
	    news.setnAuthor(author);
	    news.setnPics(picturePath);
	    news.setnContent(editorValue);
	    news.setnCreatetime(new Date());
	    news.setnIfhot(ifHot);
	    news.setnSource(source);
	    news.setnTitle(title);
	    news.setnContent(content);
	    news.settTId(typeid);
	    Result result = newsDao.insertSelective(news);
	    WriteResultToClient.writeMethod(result, response);
	}
	
	public void adminUpdateNews(HttpServletRequest request,HttpServletResponse response){
		int id=Integer.parseInt(request.getParameter("newsid"));
		String title=request.getParameter("title");
		String source=request.getParameter("source");
		String author =request.getParameter("author");
		String content =request.getParameter("content");
		
		//从content里面获取图片列表
		 List<String> list = ImgUtil.getImageSrc(content);  
		 String picturePath = ImgUtil.listToString(list, ',');
		 
		String editorValue=request.getParameter("editorValue");
		int  typeid=Integer.parseInt(request.getParameter("typeid"));
	    boolean ifHot=Boolean.parseBoolean(request.getParameter("ifHot"));
	    
	    TNews news=new TNews();
	    news.setId(id);
	    news.setnAuthor(author);
	    news.setnPics(picturePath);
	    news.setnContent(editorValue);
	    news.setnCreatetime(new Date());
	    news.setnIfhot(ifHot);
	    news.setnSource(source);
	    news.setnTitle(title);
	    news.setnContent(content);
	    news.settTId(typeid);
	    Result result = newsDao.updateByPrimaryKeySelective(news);
	    WriteResultToClient.writeMethod(result, response);
	}
	
	
	public void getNewsByTypeId(HttpServletRequest request,HttpServletResponse response){
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		Result result = newsDao.selectNewsByTypeid(typeid);
		WriteResultToClient.writeMethod(result, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
