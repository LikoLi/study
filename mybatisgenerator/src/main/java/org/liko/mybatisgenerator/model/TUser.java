package org.liko.mybatisgenerator.model;

import javax.persistence.*;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "t_user")
public class TUser {
    @Id
    @Column(name = "USER_ID")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer userId;

    @Column(name = "USER_NAME")
    @ColumnType(jdbcType = JdbcType.CHAR)
    private String userName;

    @Column(name = "USER_PASSWORD")
    @ColumnType(jdbcType = JdbcType.CHAR)
    private String userPassword;

    @Column(name = "USER_EMAIL")
    @ColumnType(jdbcType = JdbcType.CHAR)
    private String userEmail;

    public static final String TABLE = "t_user";

    public static final String f_userId = "userId";

    public static final String f_userName = "userName";

    public static final String f_userPassword = "userPassword";

    public static final String f_userEmail = "userEmail";

    /**
     * @return USER_ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return USER_PASSWORD
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * @return USER_EMAIL
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }
}