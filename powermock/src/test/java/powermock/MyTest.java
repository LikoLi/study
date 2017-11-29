package powermock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyTest {
    @Test
    public void test() {
        String[] arg = null;
        for (String s : arg) {
            System.out.println(s);
        }
    }

    @Test
    public void test1() {
        System.out.println((int) 0.1D);
    }

    @Test
    public void test2() {
        List<Person> personList = new ArrayList();
        Person li = new Person("li", 32);
        Person fji = new Person("fji", 23);
        Person dga = new Person("dga", 54);
        System.out.println();
        personList.add(li);
        personList.add(fji);
        personList.add(dga);
        personList.sort(Comparator.comparing(Person::getAge));

        System.out.println(personList);

    }

    @Test
    public void test3() {
        T3 t3 = new T3("liko", "li");
        method1(t3.getA(), t3.getB());

        t3 = new T3(null, null);
        method1(t3.getA(), t3.getB());
    }

    public void method1(String a, String b) {
        System.out.println(a + ":" + b);
    }
}

class T3 {
    String a;
    String b;

    public T3(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
