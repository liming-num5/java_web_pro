package cn.uc.yiqibang.dao;

import cn.uc.yiqibang.beans.TType;
import cn.uc.yiqibang.utils.Result;

public interface TTypeMapper {
    /**
     * 
     *根据id删除类型
     */
    Result deleteByPrimaryKey(Integer id);

    /**
     *添加类型
     */
    Result insert(TType record);

    /**
     * 添加类型
     */
    Result insertSelective(TType record);

    /**
     * 根据id查找类型详情
     * @param id
     * @return
     */
    Result selectByPrimaryKey(Integer id);

    /**
     * 更新类型
     * @param record
     * @return
     */
    Result updateByPrimaryKeySelective(TType record);

    /**
     * 根据id更新类型
     * @param record
     * @return
     */
    Result updateByPrimaryKey(TType record);
    
    Result selectAll();
}