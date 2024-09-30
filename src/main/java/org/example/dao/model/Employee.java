package org.example.dao.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Employee {
    protected int id;
    protected static String name;
    protected static String password;

    public Employee() {
    }

    public Employee(String name, String password ) {
        super();
        this.name = name;
        this.password = password;
    }

    public Employee(int id, String name, String password ) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id) {

        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}