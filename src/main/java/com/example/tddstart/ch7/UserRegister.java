package com.example.tddstart.ch7;

public class UserRegister {

    private final WeakPasswordChecker weakPasswordChecker;

    public UserRegister(WeakPasswordChecker weakPasswordChecker) {
        this.weakPasswordChecker = weakPasswordChecker;
    }

    public void register(String id, String password, String email) {
        if (weakPasswordChecker.checkPassword(password)) {
            throw new WeakPasswordException();
        }
    }
}
