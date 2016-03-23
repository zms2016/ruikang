package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TContents;
import com.zms.hengjinsuo.bean.TContentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TContentsMapper {
    int countByExample(TContentsExample example);

    int deleteByExample(TContentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TContents record);

    int insertSelective(TContents record);

    List<TContents> selectByExampleWithBLOBs(TContentsExample example);

    List<TContents> selectByExample(TContentsExample example);

    TContents selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TContents record, @Param("example") TContentsExample example);

    int updateByExampleWithBLOBs(@Param("record") TContents record, @Param("example") TContentsExample example);

    int updateByExample(@Param("record") TContents record, @Param("example") TContentsExample example);

    int updateByPrimaryKeySelective(TContents record);

    int updateByPrimaryKeyWithBLOBs(TContents record);

    int updateByPrimaryKey(TContents record);
}