package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TReceive;
import com.zms.hengjinsuo.bean.TReceiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TReceiveMapper {
    int countByExample(TReceiveExample example);

    int deleteByExample(TReceiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReceive record);

    int insertSelective(TReceive record);

    List<TReceive> selectByExample(TReceiveExample example);

    TReceive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReceive record, @Param("example") TReceiveExample example);

    int updateByExample(@Param("record") TReceive record, @Param("example") TReceiveExample example);

    int updateByPrimaryKeySelective(TReceive record);

    int updateByPrimaryKey(TReceive record);
}