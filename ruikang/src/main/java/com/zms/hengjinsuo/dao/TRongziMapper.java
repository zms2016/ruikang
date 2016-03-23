package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRongziExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRongziMapper {
    int countByExample(TRongziExample example);

    int deleteByExample(TRongziExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRongzi record);

    int insertSelective(TRongzi record);

    List<TRongzi> selectByExample(TRongziExample example);

    TRongzi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRongzi record, @Param("example") TRongziExample example);

    int updateByExample(@Param("record") TRongzi record, @Param("example") TRongziExample example);

    int updateByPrimaryKeySelective(TRongzi record);

    int updateByPrimaryKey(TRongzi record);
}