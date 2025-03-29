package com.example.tddstart.ch7;

import java.util.HashMap;

public class MemoryUserRepository implements UserRepository {

    HashMap<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        if (users.containsKey(user.getId())) {
            throw new DuplicateIdException();
        }

        users.put(user.getId(), user);
    }

    @Override
    public boolean existsById(String id) {
        return users.containsKey(id);
    }
}
