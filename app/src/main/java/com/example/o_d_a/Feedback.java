package com.example.o_d_a;

public class Feedback {

    String fEmail,Message;

    public Feedback(){}

    public Feedback(String fEmail, String message) {
        this.fEmail = fEmail;
        this.Message = message;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
}
