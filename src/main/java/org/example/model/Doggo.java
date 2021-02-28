package org.example.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Doggo implements Serializable {

    private static final long serialVersionUID = -1319522973317518581L;

    private String name;
    private String breed;
    private int age;
    private int id;

    public Doggo() {
        this.id = -1;
    }

    public Doggo(String name, String breed, int age, int id) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
