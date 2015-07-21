package com.panqd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
class Student implements Serializable {

    private String name;
    private int age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

}

public class SerialTest {
    public static void main(String[] args) throws Exception {
        Student s = new Student();
        s.setName("KingDomPan");
        s.setAge(25);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                new File("D:\\panqdq\\object.txt")));
        oos.writeObject(s);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("D:\\panqdq\\object.txt")));
        Student ss = (Student) ois.readObject();
        ois.close();
        System.out.println(ss);

    }
}
