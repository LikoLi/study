package org.liko.validation;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Student implements Serializable {

    @NotNull(message = "名字不能为空")
    private String name;

    @DecimalMax(value = "100.00", message = "体重有些超标哦")
    @DecimalMin(value = "60.00", message = "多吃点饭吧")
    private BigDecimal weight;

    private String friendName;

    @AssertTrue(message = "不能没朋友")
    private Boolean isHaveFriend() {
        return friendName == null ? false : true;
    }

    @Future(message = "生日必须在当前时间之前")
    private Date birthday;

    @Pattern(regexp = "^(.+)@(.+)$", message = "邮箱格式不合法")
    private String email;

    @Size(max = 3, min = 2, message = "size between 2 and 3")
    private String size;

    @CheckCase(value = CaseMode.LOWER, message = "名字的拼音需要小写")
    private String spellName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }
}
