package lambda.entity;

import java.util.Objects;

/**
 * @program: jmm
 * @description: 学生类
 * @Author: xiang
 * @create: 2023/6/5 16:46
 * @Version 1.0
 */
public class Student {

    /**
     * id
     */
    private  int id;

    /**
     * 姓名
     */
    private  String name;

    /**
     * 年龄
     */
    private int  sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && sex == student.sex && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex);
    }
}
