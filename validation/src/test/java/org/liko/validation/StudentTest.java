package org.liko.validation;

import sun.util.resources.LocaleData;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class StudentTest implements Serializable {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> List<String> validate(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<String>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }

        return messageList;
    }

    private static Student getBean() {
        Student bean = new Student();
        bean.setName("fdas");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        bean.setBirthday(calendar.getTime());
        bean.setEmail("fasd@qq.com");
        bean.setFriendName(null);
        bean.setWeight(new BigDecimal(60));
        bean.setSize("afd");
        bean.setSpellName("Acn");
        return bean;
    }

    public static void main(String[] args) {
        Student xiaoming = getBean();
        List<String> validate = validate(xiaoming);
        for (String msg : validate) {
            System.out.println(msg);
        }
        System.out.println(xiaoming.getFriendName());
    }
}
