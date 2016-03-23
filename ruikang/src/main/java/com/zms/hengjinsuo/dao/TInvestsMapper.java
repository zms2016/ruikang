package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TInvestsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TInvestsMapper {
    int countByExample(TInvestsExample example);

    int deleteByExample(TInvestsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TInvests record);

    int insertSelective(TInvests record);

    List<TInvests> selectByExample(TInvestsExample example);

    TInvests selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TInvests record, @Param("example") TInvestsExample example);

    int updateByExample(@Param("record") TInvests record, @Param("example") TInvestsExample example);

    int updateByPrimaryKeySelective(TInvests record);

    int updateByPrimaryKey(TInvests record);
}