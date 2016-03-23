package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TFinanical;
import com.zms.hengjinsuo.bean.TFinanicalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFinanicalMapper {
    int countByExample(TFinanicalExample example);

    int deleteByExample(TFinanicalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TFinanical record);

    int insertSelective(TFinanical record);

    List<TFinanical> selectByExample(TFinanicalExample example);

    TFinanical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TFinanical record, @Param("example") TFinanicalExample example);

    int updateByExample(@Param("record") TFinanical record, @Param("example") TFinanicalExample example);

    int updateByPrimaryKeySelective(TFinanical record);

    int updateByPrimaryKey(TFinanical record);
}