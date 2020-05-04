package com.kong.ds02.mapper3;

import com.kong.ds02.model.Pig;
import com.kong.ds02.model.PigExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PigMapper {
    long countByExample(PigExample example);

    int deleteByExample(PigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pig record);

    int insertSelective(Pig record);

    List<Pig> selectByExample(PigExample example);

    Pig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pig record, @Param("example") PigExample example);

    int updateByExample(@Param("record") Pig record, @Param("example") PigExample example);

    int updateByPrimaryKeySelective(Pig record);

    int updateByPrimaryKey(Pig record);
}