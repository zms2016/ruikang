package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TProductTypes;
import com.zms.hengjinsuo.bean.TProductTypesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProductTypesMapper {
    int countByExample(TProductTypesExample example);

    int deleteByExample(TProductTypesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProductTypes record);

    int insertSelective(TProductTypes record);

    List<TProductTypes> selectByExample(TProductTypesExample example);

    TProductTypes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProductTypes record, @Param("example") TProductTypesExample example);

    int updateByExample(@Param("record") TProductTypes record, @Param("example") TProductTypesExample example);

    int updateByPrimaryKeySelective(TProductTypes record);

    int updateByPrimaryKey(TProductTypes record);
}