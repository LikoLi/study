package org.liko.event.dao.java;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.liko.event.model.Eventgroupdef;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface EventgroupdefMapper extends Mapper<Eventgroupdef> {
    @Select("SELECT * FROM eventgroupdef EGD WHERE EGD.NAME IN ${groupNames}")
    List<Eventgroupdef> selectByNames(@Param("groupNames") String groupNames);
}