package com.example.tddstart.ch7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker;
    private MemoryUserRepository memoryUserRepository;

    @BeforeEach
    void setup() {
        stubWeakPasswordChecker = new StubWeakPasswordChecker();
        memoryUserRepository = new MemoryUserRepository();
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword_thenRegisterFail() {
        // 암호가 약하다고 응답하도록 설정
        stubWeakPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
           userRegister.register("swim12", "password1","swim12@email.com");
        });
    }

    @DisplayName("동일 아이디를 가진 회원이 있으면 예외 발생")
    @Test
    void duplicateIdExists_thenRegisterFail() {
        // 중복 id 저장
        memoryUserRepository.save(new User("swim12", "password1","swim12@email.com"));

        assertThrows(DuplicateIdException.class, () -> {
           userRegister.register("swim12", "password1","swim12@email.com");
        });
    }
}
