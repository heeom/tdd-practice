package com.example.tddstart.ch7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker;
    private MemoryUserRepository memoryUserRepository;
    private SpyEmailNotifier emailNotifier;

    @Mock
    private WeakPasswordChecker weakPasswordCheckerMock;

    @Mock
    private EmailNotifier emailNotifierMock;

    private UserRegister userRegisterMock = new UserRegister(weakPasswordCheckerMock, memoryUserRepository, emailNotifierMock);


    @BeforeEach
    void setup() {
        stubWeakPasswordChecker = new StubWeakPasswordChecker();
        memoryUserRepository = new MemoryUserRepository();
        emailNotifier = new SpyEmailNotifier();
        userRegister = new UserRegister(stubWeakPasswordChecker, memoryUserRepository, emailNotifier);
        userRegisterMock = new UserRegister(weakPasswordCheckerMock, memoryUserRepository, emailNotifierMock);
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

        // 사용한 인자 검증
        String realEmail = captor.getValue();
        assertEquals("swim12@email.com", realEmail);
    }

    // 대역객체가 기대하는 대로 상호작용했는지 확인하는 것이 모의 객체의 주요 기능이다. -> Mockito를 이용하면 모의 객체가 기대한 대로 불렸는지 검증 가능하다.
    @DisplayName("약한 암호인 경우 가입에 실패")
    @Test
    void whenWeakPasswordThenRegisterFail() {
        given(weakPasswordCheckerMock.checkPassword("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegisterMock.register("swim12", "pw","swim12@email.com");
        });
    }

    @DisplayName("회원 가입시 암호검사 수행함")
    @Test
    void checkPassword() {
        userRegisterMock.register("swim12", "password1","swim12@email.com");

        then(weakPasswordCheckerMock).should().checkPassword(anyString());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegisterMock.register("swim12", "password1","swim12@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class); // 모의 객체의 메서드를 호출할 때 전달한 객체를 담는 기능
        then(emailNotifierMock).should() // 모의 객체의 메서드가 호출됐는지 확인할 때
                .sendRegistrationEmail(captor.capture()); // 메서드를 호출할 떄 전달한 인자가 ArgumentCaptor에 담긴다

        assertEquals("swim12@email.com", captor.getValue()); // 보관한 값은 getValue로 꺼내서 인자 검증시 사용할 수 있다
    }
}
