package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TProductProvider;
import com.zms.hengjinsuo.bean.TProductProviderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProductProviderMapper {
    int countByExample(TProductProviderExample example);

    int deleteByExample(TProductProviderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProductProvider record);

    int insertSelective(TProductProvider record);

    List<TProductProvider> selectByExample(TProductProviderExample example);

    TProductProvider selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProductProvider record, @Param("example") TProductProviderExample example);

    int updateByExample(@Param("record") TProductProvider record, @Param("example") TProductProviderExample example);

    int updateByPrimaryKeySelective(TProductProvider record);

    int updateByPrimaryKey(TProductProvider record);
}