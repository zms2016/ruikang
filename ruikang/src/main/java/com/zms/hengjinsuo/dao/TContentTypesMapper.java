package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TContentTypes;
import com.zms.hengjinsuo.bean.TContentTypesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TContentTypesMapper {
    int countByExample(TContentTypesExample example);

    int deleteByExample(TContentTypesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TContentTypes record);

    int insertSelective(TContentTypes record);

    List<TContentTypes> selectByExample(TContentTypesExample example);

    TContentTypes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TContentTypes record, @Param("example") TContentTypesExample example);

    int updateByExample(@Param("record") TContentTypes record, @Param("example") TContentTypesExample example);

    int updateByPrimaryKeySelective(TContentTypes record);

    int updateByPrimaryKey(TContentTypes record);
}