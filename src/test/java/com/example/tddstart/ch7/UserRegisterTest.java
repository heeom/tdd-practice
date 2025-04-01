package com.example.tddstart.ch7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker;
    private MemoryUserRepository memoryUserRepository;
    private SpyEmailNotifier emailNotifier;

    @BeforeEach
    void setup() {
        stubWeakPasswordChecker = new StubWeakPasswordChecker();
        memoryUserRepository = new MemoryUserRepository();
        emailNotifier = new SpyEmailNotifier();
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, emailNotifier);
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

    @DisplayName("동일 아이디를 가진 회원이 없으면 가입 성공")
    @Test
    void duplicateIdNotExists_thenRegisterSuccess() {

        userRegister.register("swim12", "password1","swim12@email.com");

        boolean result = memoryUserRepository.existsById("swim12");
        assertEquals(true, result);
    }

    @DisplayName("회원가입 성공하면 가입 안내 메일을 발송")
    @Test
    void registerSuccess_thenSendEmail() {
        userRegister.register("swim12", "password1","swim12@email.com");

        assertTrue(emailNotifier.isCalled());

        assertEquals(
                "swim12@email.com",
                emailNotifier.getEmail()
        );
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendEmail() {
        EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);
        UserRegister register = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, mockEmailNotifier);
        register.register("swim12", "password1","swim12@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        then(mockEmailNotifier).should().sendRegistrationEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("swim12@email.com", realEmail);
    }
}
