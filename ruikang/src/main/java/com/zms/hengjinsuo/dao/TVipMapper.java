package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bean.TVipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVipMapper {
    int countByExample(TVipExample example);

    int deleteByExample(TVipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TVip record);

    int insertSelective(TVip record);

    List<TVip> selectByExample(TVipExample example);

    TVip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TVip record, @Param("example") TVipExample example);

    int updateByExample(@Param("record") TVip record, @Param("example") TVipExample example);

    int updateByPrimaryKeySelective(TVip record);

    int updateByPrimaryKey(TVip record);
}