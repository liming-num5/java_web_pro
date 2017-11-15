package cn.uc.yiqibang.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.uc.yiqibang.beans.TComment;
import cn.uc.yiqibang.dao.TCommentMapper;
import cn.uc.yiqibang.utils.Constants;
import cn.uc.yiqibang.utils.MyBatisUtils;
import cn.uc.yiqibang.utils.Result;

public class CommentMapperImpl implements TCommentMapper{

	@Override
	public Result deleteByCondition(TComment comm) {
			Result result=new Result();
			SqlSession session = MyBatisUtils.openSession();
			int row = session.delete(Constants.commentMapper_deleteByCondition,comm);
			session.commit();
			session.close();
			if(row>0){
				result.setRetCode(Constants.RETCODE_SUCCESS);
				result.setRetMsg(true);
			}else{
				result.setRetCode(Constants.RETCODE_FAILED);
				result.setRetMsg(false);
			}
			return result;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TComment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TComment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TComment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TComment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TComment record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
