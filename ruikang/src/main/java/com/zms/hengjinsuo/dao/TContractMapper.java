package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TContractMapper {
    int countByExample(TContractExample example);

    int deleteByExample(TContractExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TContract record);

    int insertSelective(TContract record);

    List<TContract> selectByExample(TContractExample example);

    TContract selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TContract record, @Param("example") TContractExample example);

    int updateByExample(@Param("record") TContract record, @Param("example") TContractExample example);

    int updateByPrimaryKeySelective(TContract record);

    int updateByPrimaryKey(TContract record);
}