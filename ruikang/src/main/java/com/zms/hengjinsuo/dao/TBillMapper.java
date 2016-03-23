package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TBill;
import com.zms.hengjinsuo.bean.TBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBillMapper {
    int countByExample(TBillExample example);

    int deleteByExample(TBillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TBill record);

    int insertSelective(TBill record);

    List<TBill> selectByExample(TBillExample example);

    TBill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TBill record, @Param("example") TBillExample example);

    int updateByExample(@Param("record") TBill record, @Param("example") TBillExample example);

    int updateByPrimaryKeySelective(TBill record);

    int updateByPrimaryKey(TBill record);
}