package com.dahuatech.test.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.test.bean</p>
 * <p>className: Human</p>
 * <p>date: 2023/7/19</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor // 如果只用@AllArgsConstructor注解 将没有空参构造器
@Builder
public class Human {
    private String name;
    private int age;

    // public Human() {
    // }
    //
    // public Human(String name, int age) {
    //     this.name = name;
    //     this.age = age;
    // }
    //
    // public String getName() {
    //     return name;
    // }
    //
    // public void setName(String name) {
    //     this.name = name;
    // }
    //
    // public int getAge() {
    //     return age;
    // }
    //
    // public void setAge(int age) {
    //     this.age = age;
    // }
    //
    // @Override
    // public String toString() {
    //     return "Human{" +
    //             "name='" + name + '\'' +
    //             ", age=" + age +
    //             '}';
    // }
    //
    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Human human = (Human) o;
    //     return age == human.age && Objects.equals(name, human.name);
    // }
    //
    // @Override
    // public int hashCode() {
    //     return Objects.hash(name, age);
    // }

    public void display() {
        log.error("alan");
    }
}
