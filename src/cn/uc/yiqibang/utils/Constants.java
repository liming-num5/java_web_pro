package cn.uc.yiqibang.utils;

public class Constants {
	//响应码
		public static final int RETCODE_SUCCESS=0;
		public static final int RETCODE_FAILED=2000;
		
		//定义映射文件.xml的包的根路径
		 public static final String root="cn.uc.yiqibang.mapper";
		 //定义mapper映射文件的名称
		 public static final String commentMapper=".TCommentMapper";
		 public static final String newsMapper=".TNewsMapper";
		 public static final String typeMapper=".TTypeMapper";
		 public static final String picMapper=".TPicMapper";
		 
		 //定义操作的sql语句的id名称
		 public static final String selectAll=".selectAll";
		 public static final String selectByPrimaryKey=".selectByPrimaryKey";
		 public static final String selectByLike=".selectByLike";
		 public static final String insertSelective=".insertSelective";
		 public static final String insert=".insert";
		 public static final String update=".update";
		 public static final String deleteByPrimaryKey=".deleteByPrimaryKey";
		 public static final String deleteByCondition=".deleteByCondition";
		 public static final String updateByPrimaryKeySelective=".updateByPrimaryKeySelective";
		 public static final String selectAllByPage=".selectAllByPage";
		 //...
		 
		 //Comment评论的操作语句路径
		 public static final String commentMapper_selectAll=root+commentMapper+selectAll;
		 public static final String commentMapper_insert=root+commentMapper+insert;
		 public static final String commentMapper_deleteByCondition=root+commentMapper+deleteByCondition;
		 //News新闻的操作语句路径
		 public static final int newsPageCounts=20;//新闻每页的显示条数
		 public static final String newsMapper_selectAll=root+newsMapper+selectAll;
		 public static final String newsMapper_selectAllByPage=root+newsMapper+selectAllByPage;
		 public static final String newsMapper_deleteByPrimaryKey=root+newsMapper+deleteByPrimaryKey;
		 public static final String newsMapper_insertSelective=root+newsMapper+insertSelective;
		 public static final String newsMapper_selectByLike=root+newsMapper+selectByLike;
		 public static final String newsMapper_updateByPrimaryKeySelective=root+newsMapper+updateByPrimaryKeySelective;
		 public static final String newsMapper_selectByTypeId=root+newsMapper+".selectByTypeId";
		 
		 //Type新闻分类的操作语句路径
		 public static final String typeMapper_selectByPrimaryKey=root+typeMapper+selectByPrimaryKey;
		 public static final String typeMapper_selectAll=root+typeMapper+selectAll;

		 //Pic图片
		 public static final String picMapper_deleteByCondition=root+picMapper+deleteByCondition;
		 public static final String picMapper_insertSelective=root+picMapper+insertSelective;
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 

}
