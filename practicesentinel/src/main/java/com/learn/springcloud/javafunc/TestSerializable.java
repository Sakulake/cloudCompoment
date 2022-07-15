package com.learn.springcloud.javafunc;

import com.learn.springcloud.javafunc.serialize.Student;

import java.io.*;

/**
 * @author sdw
 * @version 1.0
 * @className TestSerializable
 * @date 2022/01/11 17:59:43
 * @description
 */

public class TestSerializable {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("A");
        student.setScore("S");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/mac/Desktop/testdata/student.txt"));
            objectOutputStream.writeObject(student);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(("/Users/mac/Desktop/testdata/student.txt")));
            Student student1 = (Student) objectInputStream.readObject();
            System.out.println(student1.getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
