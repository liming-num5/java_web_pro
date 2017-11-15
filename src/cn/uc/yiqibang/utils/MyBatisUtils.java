package cn.uc.yiqibang.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	
	static SqlSessionFactory factory=null;
	static String config="config.xml";
	
	//获取应用唯一的factory对象
	private static SqlSessionFactory factory(){
		if(factory==null){
			InputStream is = MyBatisUtils.class.getClassLoader().getResourceAsStream(config);
			factory= new SqlSessionFactoryBuilder().build(is);
		}
		return factory;
	}
	
	//使用应用唯一的factory对象打开一个SqlSession对象
	public static SqlSession openSession(){
		return factory().openSession();
	}
}
