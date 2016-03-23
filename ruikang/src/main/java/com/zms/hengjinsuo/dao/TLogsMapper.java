package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TLogs;
import com.zms.hengjinsuo.bean.TLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLogsMapper {
    int countByExample(TLogsExample example);

    int deleteByExample(TLogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLogs record);

    int insertSelective(TLogs record);

    List<TLogs> selectByExample(TLogsExample example);

    TLogs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLogs record, @Param("example") TLogsExample example);

    int updateByExample(@Param("record") TLogs record, @Param("example") TLogsExample example);

    int updateByPrimaryKeySelective(TLogs record);

    int updateByPrimaryKey(TLogs record);
}