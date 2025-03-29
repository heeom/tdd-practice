package com.example.tddstart.ch7;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

    private boolean weak;

    public void setWeak(boolean isWeak) {
        this.weak = isWeak;
    }

    @Override
    public boolean checkPassword(String password) {
        return weak;
    }
}
