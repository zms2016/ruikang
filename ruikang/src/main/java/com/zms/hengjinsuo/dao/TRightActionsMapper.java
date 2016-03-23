package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRightActions;
import com.zms.hengjinsuo.bean.TRightActionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRightActionsMapper {
    int countByExample(TRightActionsExample example);

    int deleteByExample(TRightActionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRightActions record);

    int insertSelective(TRightActions record);

    List<TRightActions> selectByExample(TRightActionsExample example);

    TRightActions selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRightActions record, @Param("example") TRightActionsExample example);

    int updateByExample(@Param("record") TRightActions record, @Param("example") TRightActionsExample example);

    int updateByPrimaryKeySelective(TRightActions record);

    int updateByPrimaryKey(TRightActions record);
}