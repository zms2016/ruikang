package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TVisit;
import com.zms.hengjinsuo.bean.TVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVisitMapper {
    int countByExample(TVisitExample example);

    int deleteByExample(TVisitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TVisit record);

    int insertSelective(TVisit record);

    List<TVisit> selectByExample(TVisitExample example);

    TVisit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TVisit record, @Param("example") TVisitExample example);

    int updateByExample(@Param("record") TVisit record, @Param("example") TVisitExample example);

    int updateByPrimaryKeySelective(TVisit record);

    int updateByPrimaryKey(TVisit record);
}