package com.zms.hengjinsuo.dao;

import com.zms.hengjinsuo.bean.TRightUser;
import com.zms.hengjinsuo.bean.TRightUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRightUserMapper {
    int countByExample(TRightUserExample example);

    int deleteByExample(TRightUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRightUser record);

    int insertSelective(TRightUser record);

    List<TRightUser> selectByExample(TRightUserExample example);

    TRightUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRightUser record, @Param("example") TRightUserExample example);

    int updateByExample(@Param("record") TRightUser record, @Param("example") TRightUserExample example);

    int updateByPrimaryKeySelective(TRightUser record);

    int updateByPrimaryKey(TRightUser record);
}