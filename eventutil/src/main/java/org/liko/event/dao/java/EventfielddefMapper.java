package org.liko.event.dao.java;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.liko.event.model.Eventfielddef;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface EventfielddefMapper extends Mapper<Eventfielddef> {
    @Delete("DELETE FROM eventfielddef WHERE eventId IN ${ids}")
    void deleteByIds(@Param("ids") String ids);
}