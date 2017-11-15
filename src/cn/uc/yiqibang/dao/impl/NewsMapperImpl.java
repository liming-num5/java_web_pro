package cn.uc.yiqibang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.uc.yiqibang.beans.TComment;
import cn.uc.yiqibang.beans.TNews;
import cn.uc.yiqibang.dao.TCommentMapper;
import cn.uc.yiqibang.dao.TNewsMapper;
import cn.uc.yiqibang.utils.Constants;
import cn.uc.yiqibang.utils.MyBatisUtils;
import cn.uc.yiqibang.utils.Result;

public class NewsMapperImpl implements TNewsMapper {

	TCommentMapper commDao = new CommentMapperImpl();

	@Override
	public Result selectAll() {
		Result result = new Result();
		SqlSession session = MyBatisUtils.openSession();
		List<TNews> newsList = session.selectList(Constants.newsMapper_selectAll);
		session.close();
		if (newsList != null) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
			result.setRetData(newsList);
		} else {
			result.setRetCode(Constants.RETCODE_FAILED);
			result.setRetMsg(false);
		}
		return result;
	}
	
	@Override
	public Result selectAllByPage(int pageNum) {
		Result result = new Result();
		SqlSession session = MyBatisUtils.openSession();
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("startIndex",Constants.newsPageCounts*(pageNum-1));
		map.put("pageCounts",Constants.newsPageCounts);
		List<TNews> newsList = session.selectList(Constants.newsMapper_selectAllByPage,map);
		session.close();
		if (newsList != null) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
			result.setRetData(newsList);
		} else {
			result.setRetCode(Constants.RETCODE_FAILED);
			result.setRetMsg(false);
		}
		return result;
	}
	
	@Override
	public Result deleteByPrimaryKey(Integer id) {
		Result result = new Result();
		result.setRetCode(Constants.RETCODE_FAILED);
		result.setRetMsg(false);

		// 删除该id关联的所有评论
		TComment comm = new TComment();
		comm.settNId(id);
		commDao.deleteByCondition(comm);

		// 删除新闻
		SqlSession session = MyBatisUtils.openSession();
		int row = session.delete(Constants.newsMapper_deleteByPrimaryKey, id);
		session.commit();
		session.close();
		if (row > 0) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
		}
		return result;

	}

	@Override
	public int insert(TNews record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Result insertSelective(TNews record) {
		Result result = new Result();
		result.setRetCode(Constants.RETCODE_FAILED);
		result.setRetMsg(false);
		// 添加新闻
		SqlSession session = MyBatisUtils.openSession();
		int row = session.insert(Constants.newsMapper_insertSelective, record);
		session.commit();
		session.close();
		if (row > 0) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
		}
		return result;
	}

	@Override
	public TNews selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result updateByPrimaryKeySelective(TNews record) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setRetCode(Constants.RETCODE_FAILED);
		result.setRetMsg(false);
		// 添加新闻
		SqlSession session = MyBatisUtils.openSession();
		int row = session.insert(Constants.newsMapper_updateByPrimaryKeySelective, record);
		session.commit();
		session.close();
		if (row > 0) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
		}
		return result;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(TNews record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TNews record) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public Result selectByLike(String likeStr) {
		Result result = new Result();
		result.setRetCode(Constants.RETCODE_FAILED);
		result.setRetMsg(false);
		// 添加新闻
		SqlSession session = MyBatisUtils.openSession();
		List<TNews> news = session.selectList(Constants.newsMapper_selectByLike, likeStr);
		session.close();
		if (news!=null) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
			result.setRetData(news);
		}
		return result;
	}

	@Override
	public Result selectNewsByTypeid(int typeid) {
		Result result = new Result();
		result.setRetCode(Constants.RETCODE_FAILED);
		result.setRetMsg(false);
		// 添加新闻
		SqlSession session = MyBatisUtils.openSession();
		List<TNews> news = session.selectList(Constants.newsMapper_selectByTypeId, typeid);
		session.close();
		if (news!=null) {
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
			result.setRetData(news);
		}
		return result;
	}

	

}
