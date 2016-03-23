package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRightTypesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRightTypesMapper {
    int countByExample(TRightTypesExample example);

    int deleteByExample(TRightTypesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRightTypes record);

    int insertSelective(TRightTypes record);

    List<TRightTypes> selectByExample(TRightTypesExample example);

    TRightTypes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRightTypes record, @Param("example") TRightTypesExample example);

    int updateByExample(@Param("record") TRightTypes record, @Param("example") TRightTypesExample example);

    int updateByPrimaryKeySelective(TRightTypes record);

    int updateByPrimaryKey(TRightTypes record);
}