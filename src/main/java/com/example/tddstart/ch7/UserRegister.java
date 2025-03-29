package com.example.tddstart.ch7;

public class UserRegister {

    private final WeakPasswordChecker weakPasswordChecker;
    private final UserRepository userRepository;

    public UserRegister(WeakPasswordChecker weakPasswordChecker, UserRepository userRepository) {
        this.weakPasswordChecker = weakPasswordChecker;
        this.userRepository = userRepository;
    }

    public void register(String id, String password, String email) {
        if (weakPasswordChecker.checkPassword(password)) {
            throw new WeakPasswordException();
        }
        if (userRepository.existsById(id)) {
            throw new DuplicateIdException();
        }
        userRepository.save(new User(id, password, email));
    }
}
