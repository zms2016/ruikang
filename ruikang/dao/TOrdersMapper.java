package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TOrders;
import com.zms.hengjinsuo.bean.TOrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOrdersMapper {
    int countByExample(TOrdersExample example);

    int deleteByExample(TOrdersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOrders record);

    int insertSelective(TOrders record);

    List<TOrders> selectByExample(TOrdersExample example);

    TOrders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOrders record, @Param("example") TOrdersExample example);

    int updateByExample(@Param("record") TOrders record, @Param("example") TOrdersExample example);

    int updateByPrimaryKeySelective(TOrders record);

    int updateByPrimaryKey(TOrders record);
}