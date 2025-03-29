package com.example.tddstart.ch7;

public class SpyEmailNotifier implements EmailNotifier {

    private boolean isCalled;
    private String email;

    public boolean isCalled() {
        return isCalled;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void sendRegistrationEmail(String email) {
        this.isCalled = true;
        this.email = email;
    }
}
