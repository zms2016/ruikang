package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRoleRight;
import com.zms.hengjinsuo.bean.TRoleRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleRightMapper {
    int countByExample(TRoleRightExample example);

    int deleteByExample(TRoleRightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoleRight record);

    int insertSelective(TRoleRight record);

    List<TRoleRight> selectByExample(TRoleRightExample example);

    TRoleRight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoleRight record, @Param("example") TRoleRightExample example);

    int updateByExample(@Param("record") TRoleRight record, @Param("example") TRoleRightExample example);

    int updateByPrimaryKeySelective(TRoleRight record);

    int updateByPrimaryKey(TRoleRight record);
}