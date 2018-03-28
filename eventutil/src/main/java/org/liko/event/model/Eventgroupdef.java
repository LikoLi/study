package org.liko.event.model;

import javax.persistence.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "eventgroupdef")
public class Eventgroupdef {
    /**
     * 自增id
     */
    @Id
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer id;

    /**
     * 组名
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String name;

    /**
     * 组备注
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String comment;

    public static final String TABLE = "eventgroupdef";

    public static final String f_id = "id";

    public static final String f_name = "name";

    public static final String f_comment = "comment";

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取组名
     *
     * @return name - 组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     *
     * @param name 组名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取组备注
     *
     * @return comment - 组备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置组备注
     *
     * @param comment 组备注
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}