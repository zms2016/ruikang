package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TRightsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRightsMapper {
    int countByExample(TRightsExample example);

    int deleteByExample(TRightsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRights record);

    int insertSelective(TRights record);

    List<TRights> selectByExample(TRightsExample example);

    TRights selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRights record, @Param("example") TRightsExample example);

    int updateByExample(@Param("record") TRights record, @Param("example") TRightsExample example);

    int updateByPrimaryKeySelective(TRights record);

    int updateByPrimaryKey(TRights record);
}