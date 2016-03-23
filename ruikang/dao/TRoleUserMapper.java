package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRoleUser;
import com.zms.hengjinsuo.bean.TRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleUserMapper {
    int countByExample(TRoleUserExample example);

    int deleteByExample(TRoleUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoleUser record);

    int insertSelective(TRoleUser record);

    List<TRoleUser> selectByExample(TRoleUserExample example);

    TRoleUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoleUser record, @Param("example") TRoleUserExample example);

    int updateByExample(@Param("record") TRoleUser record, @Param("example") TRoleUserExample example);

    int updateByPrimaryKeySelective(TRoleUser record);

    int updateByPrimaryKey(TRoleUser record);
}