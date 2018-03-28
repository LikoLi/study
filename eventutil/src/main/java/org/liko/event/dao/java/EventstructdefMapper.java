package org.liko.event.dao.java;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.liko.event.model.Eventstructdef;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface EventstructdefMapper extends Mapper<Eventstructdef> {
    @Select("SELECT * FROM eventstructdef ESD WHERE ESD.NAME IN ${eventNames}")
    List<Eventstructdef> selectByNames(@Param("eventNames") String eventNames);

    @Delete("DELETE FROM eventstructdef WHERE id IN ${ids}")
    void deleteByEventId(@Param("ids") String ids);
}