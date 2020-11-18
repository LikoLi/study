package org.liko.event.model;

import javax.persistence.*;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "eventfielddef")
public class Eventfielddef {
    /**
     * 自增id
     */
    @Id
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer id;

    /**
     * 事件Id
     */
    @Column(name = "eventId")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer eventid;

    /**
     * 属性名
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String name;

    /**
     * 是否必填<>noneed=0=否&need=1=是
     */
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer required;

    /**
     * 属性类型<>int=int=int&double=double=double&String=String=String&boolean=boolean=boolean&short=short=short&long=long=long&binary=binary=binary&obj=obj=Object&map=map=map<String,String>&list=list=list<string>&map4obj=map4obj=map<string,obj>&list4obj=list4obj=list<obj>
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String type;

    /**
     * 对应的元素类,当类型为list,map才使用
     */
    @Column(name = "elementId")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer elementid;

    /**
     * 成员备注
     */
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String comment;

    public static final String TABLE = "eventfielddef";

    public static final String f_id = "id";

    public static final String f_eventid = "eventid";

    public static final String f_name = "name";

    public static final String f_required = "required";

    public static final String f_type = "type";

    public static final String f_elementid = "elementid";

    public static final String f_comment = "comment";

    /**
     * 否
     */
    public static final Integer c_required_noneed = 0;

    /**
     * 是
     */
    public static final Integer c_required_need = 1;

    /**
     * int
     */
    public static final String c_type_int = "int";

    /**
     * double
     */
    public static final String c_type_double = "double";

    /**
     * String
     */
    public static final String c_type_String = "String";

    /**
     * boolean
     */
    public static final String c_type_boolean = "boolean";

    /**
     * short
     */
    public static final String c_type_short = "short";

    /**
     * long
     */
    public static final String c_type_long = "long";

    /**
     * binary
     */
    public static final String c_type_binary = "binary";

    /**
     * Object
     */
    public static final String c_type_obj = "obj";

    /**
     * map<String,String>
     */
    public static final String c_type_map = "map";

    /**
     * list<string>
     */
    public static final String c_type_list = "list";

    /**
     * map<string,obj>
     */
    public static final String c_type_map4obj = "map4obj";

    /**
     * list<obj>
     */
    public static final String c_type_list4obj = "list4obj";

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
     * 获取事件Id
     *
     * @return eventId - 事件Id
     */
    public Integer getEventid() {
        return eventid;
    }

    /**
     * 设置事件Id
     *
     * @param eventid 事件Id
     */
    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    /**
     * 获取属性名
     *
     * @return name - 属性名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性名
     *
     * @param name 属性名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取是否必填<>noneed=0=否&need=1=是
     *
     * @return required - 是否必填<>noneed=0=否&need=1=是
     */
    public Integer getRequired() {
        return required;
    }

    /**
     * 设置是否必填<>noneed=0=否&need=1=是
     *
     * @param required 是否必填<>noneed=0=否&need=1=是
     */
    public void setRequired(Integer required) {
        this.required = required;
    }

    /**
     * 获取属性类型<>int=int=int&double=double=double&String=String=String&boolean=boolean=boolean&short=short=short&long=long=long&binary=binary=binary&obj=obj=Object&map=map=map<String,String>&list=list=list<string>&map4obj=map4obj=map<string,obj>&list4obj=list4obj=list<obj>
     *
     * @return type - 属性类型<>int=int=int&double=double=double&String=String=String&boolean=boolean=boolean&short=short=short&long=long=long&binary=binary=binary&obj=obj=Object&map=map=map<String,String>&list=list=list<string>&map4obj=map4obj=map<string,obj>&list4obj=list4obj=list<obj>
     */
    public String getType() {
        return type;
    }

    /**
     * 设置属性类型<>int=int=int&double=double=double&String=String=String&boolean=boolean=boolean&short=short=short&long=long=long&binary=binary=binary&obj=obj=Object&map=map=map<String,String>&list=list=list<string>&map4obj=map4obj=map<string,obj>&list4obj=list4obj=list<obj>
     *
     * @param type 属性类型<>int=int=int&double=double=double&String=String=String&boolean=boolean=boolean&short=short=short&long=long=long&binary=binary=binary&obj=obj=Object&map=map=map<String,String>&list=list=list<string>&map4obj=map4obj=map<string,obj>&list4obj=list4obj=list<obj>
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取对应的元素类,当类型为list,map才使用
     *
     * @return elementId - 对应的元素类,当类型为list,map才使用
     */
    public Integer getElementid() {
        return elementid;
    }

    /**
     * 设置对应的元素类,当类型为list,map才使用
     *
     * @param elementid 对应的元素类,当类型为list,map才使用
     */
    public void setElementid(Integer elementid) {
        this.elementid = elementid;
    }

    /**
     * 获取成员备注
     *
     * @return comment - 成员备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置成员备注
     *
     * @param comment 成员备注
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}