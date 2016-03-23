package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TFundpool;
import com.zms.hengjinsuo.bean.TFundpoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFundpoolMapper {
    int countByExample(TFundpoolExample example);

    int deleteByExample(TFundpoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TFundpool record);

    int insertSelective(TFundpool record);

    List<TFundpool> selectByExample(TFundpoolExample example);

    TFundpool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TFundpool record, @Param("example") TFundpoolExample example);

    int updateByExample(@Param("record") TFundpool record, @Param("example") TFundpoolExample example);

    int updateByPrimaryKeySelective(TFundpool record);

    int updateByPrimaryKey(TFundpool record);
}