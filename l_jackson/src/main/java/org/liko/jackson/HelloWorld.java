package org.liko.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloWorld {

    @Test
    public void t1() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "{\"name\":\"Mahesh\", \"age\":21}";

        // map json to student
        try {
            Student student = mapper.readValue(jsonStr, Student.class);
            System.out.println(student);

            mapper.enable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
            jsonStr = mapper.writeValueAsString(student);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t2() {
        ObjectMapper mapper = new ObjectMapper();
        Student student = new Student();
        student.setAge(11);
        student.setName("liko");
        try {
            mapper.writeValue(new File("student.json"), student);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            student = mapper.readValue(new File("student.json"), Student.class);
            System.out.println(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t3() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> studentDataMap = new HashMap<>();

        int[] marks = {1, 2, 3};

        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        //java object
        studentDataMap.put("student", student);
        //java string
        studentDataMap.put("name", "Mahesh Kumar");
        //java boolean
        studentDataMap.put("verified", Boolean.FALSE);
        // Array
        studentDataMap.put("marks", marks);

        mapper.writeValue(new File("student.json"), studentDataMap);

    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}