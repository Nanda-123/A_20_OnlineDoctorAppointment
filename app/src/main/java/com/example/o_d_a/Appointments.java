package com.example.o_d_a;

public class Appointments {
    String Name,Number,Email,Age,BloodGrp,City,Gender,Address;

    public Appointments(){}

    public Appointments(String name, String number, String email, String age, String bloodGrp, String city, String gender, String address) {
        Name = name;
        Number = number;
        Email = email;
        Age = age;
        BloodGrp = bloodGrp;
        City = city;
        Gender = gender;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBloodGrp() {
        return BloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        BloodGrp = bloodGrp;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
