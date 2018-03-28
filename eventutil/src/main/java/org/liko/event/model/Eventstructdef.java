package org.liko.event.model;

import javax.persistence.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "eventstructdef")
public class Eventstructdef {
    /**
     * 自增id
     */
    @Id
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer id;

    /**
     * 事件组id
     */
    @Column(name = "groupId")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer groupid;

    /**
     * 事件名
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String name;

    /**
     * 事件备注
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String comment;

    public static final String TABLE = "eventstructdef";

    public static final String f_id = "id";

    public static final String f_groupid = "groupid";

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
     * 获取事件组id
     *
     * @return groupId - 事件组id
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * 设置事件组id
     *
     * @param groupid 事件组id
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取事件名
     *
     * @return name - 事件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置事件名
     *
     * @param name 事件名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取事件备注
     *
     * @return comment - 事件备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置事件备注
     *
     * @param comment 事件备注
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}