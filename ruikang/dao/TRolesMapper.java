package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.bean.TRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRolesMapper {
    int countByExample(TRolesExample example);

    int deleteByExample(TRolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoles record);

    int insertSelective(TRoles record);

    List<TRoles> selectByExample(TRolesExample example);

    TRoles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoles record, @Param("example") TRolesExample example);

    int updateByExample(@Param("record") TRoles record, @Param("example") TRolesExample example);

    int updateByPrimaryKeySelective(TRoles record);

    int updateByPrimaryKey(TRoles record);
}