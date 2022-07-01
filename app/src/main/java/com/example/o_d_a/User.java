package com.example.o_d_a;

public class User {
    private String name;
    private String email;
    private String password;
    private String address;
    private String age;
    private String number;

    public User(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public User(String name, String email, String password, String address, String age, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.age = age;
        this.number = number;
    }


}
