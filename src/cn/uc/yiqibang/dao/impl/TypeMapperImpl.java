package cn.uc.yiqibang.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.uc.yiqibang.beans.TType;
import cn.uc.yiqibang.dao.TTypeMapper;
import cn.uc.yiqibang.utils.Constants;
import cn.uc.yiqibang.utils.MyBatisUtils;
import cn.uc.yiqibang.utils.Result;

public class TypeMapperImpl implements TTypeMapper{

	@Override
	public Result deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result insert(TType record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result insertSelective(TType record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selectByPrimaryKey(Integer id) {
		Result result=new Result();
		SqlSession session=MyBatisUtils.openSession();
		TType type = session.selectOne(Constants.typeMapper_selectByPrimaryKey,id);
		session.close();
		if(type!=null){
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
			result.setRetData(type);
		}else{
			result.setRetCode(Constants.RETCODE_FAILED);
			result.setRetMsg(false);
		}
		return result;
	}

	@Override
	public Result updateByPrimaryKeySelective(TType record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result updateByPrimaryKey(TType record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selectAll() {
		Result result=new Result();
		SqlSession session=MyBatisUtils.openSession();
		List<TType> types = session.selectList(Constants.typeMapper_selectAll);
		session.close();
		if(types!=null){
			result.setRetCode(Constants.RETCODE_SUCCESS);
			result.setRetMsg(true);
			result.setRetData(types);
		}else{
			result.setRetCode(Constants.RETCODE_FAILED);
			result.setRetMsg(false);
		}
		return result;
	}

}
