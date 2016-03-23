package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TOrderdetail;
import com.zms.hengjinsuo.bean.TOrderdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOrderdetailMapper {
    int countByExample(TOrderdetailExample example);

    int deleteByExample(TOrderdetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOrderdetail record);

    int insertSelective(TOrderdetail record);

    List<TOrderdetail> selectByExample(TOrderdetailExample example);

    TOrderdetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOrderdetail record, @Param("example") TOrderdetailExample example);

    int updateByExample(@Param("record") TOrderdetail record, @Param("example") TOrderdetailExample example);

    int updateByPrimaryKeySelective(TOrderdetail record);

    int updateByPrimaryKey(TOrderdetail record);
}