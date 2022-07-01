package com.example.o_d_a;

public class Users {

    String fName, fEmail,fAge, fAddress, fNumber;


    public Users(String fName, String fEmail, String fAge, String fAddress,String fNumber) {
        this.fName = fName;
        this.fEmail = fEmail;
        this.fAge = fAge;
        this.fAddress=fAddress;
        this.fNumber=fNumber;
    }

    public Users(){}

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfAge() {
        return fAge;
    }

    public void setfAge(String fAge) {
        this.fAge = fAge;
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public String getfNumber() {
        return fNumber;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }
}
