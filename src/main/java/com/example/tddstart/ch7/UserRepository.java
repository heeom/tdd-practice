package com.example.tddstart.ch7;

public interface UserRepository {

    void save(User user);

    boolean existsById(String id);
}
