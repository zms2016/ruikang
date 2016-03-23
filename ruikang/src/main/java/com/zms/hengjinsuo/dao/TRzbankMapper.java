package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TRzbankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRzbankMapper {
    int countByExample(TRzbankExample example);

    int deleteByExample(TRzbankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRzbank record);

    int insertSelective(TRzbank record);

    List<TRzbank> selectByExample(TRzbankExample example);

    TRzbank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRzbank record, @Param("example") TRzbankExample example);

    int updateByExample(@Param("record") TRzbank record, @Param("example") TRzbankExample example);

    int updateByPrimaryKeySelective(TRzbank record);

    int updateByPrimaryKey(TRzbank record);
}