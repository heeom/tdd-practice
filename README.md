## 🚀 TDD Practice (Test-Driven Development)

- 클린 코더스의 [TDD 강의](https://www.youtube.com/watch?v=wmHV6L0e1sU) 테스트 주도 개발(TDD)을 실습하기 위한 프로젝트
- TDD(Test-Driven Development, 테스트 주도 개발)의 3가지 법칙을 기반으로 TDD 사이클(**Red → Green → Refactor**)을 연습한다.

## 📌 TDD 핵심 원칙
1. **Red** – 실패하는 테스트 작성  
2. **Green** – 테스트를 통과하는 최소한의 코드 작성  
3. **Refactor** – 중복 제거 및 코드 개선  

## 📌 TDD 원칙과 팁

- 어떤 테스트를 먼저 작성해야 할까?
    - most simple, degenerate test case first (쉬운 테스트부터)
- 최소한의 코드로
    - little golf game
- 테스트 코드가 점점 더 구체화될 수록 프로덕션 코드는 더 제네릭(범용화)하게 된다

## 📌 TDD의 이점

- Debugging Time
    - 디버깅 시간을 줄여 준다
- Design Documents
    - TDD의 3가지 법칙을 잘 따르면 설계문서를 얻게 된다
- Decoupling
    - 모든 코드라인을 테스트하는 유일한 방법은  테스트코드에서 그 코드들에 접근하는 것이다. → 테스트를 먼저 작성하면 테스트를 작성할 수 없는 프로덕션 코드는 없다
    - TDD로 구현하면 보다 decouple된 시스템을 갖게된다
- Courage to Change

## Prime factors and word wrap example

### prime factors

- As the tests get more specific, the code gets more generic
- 어떤 특별한 경우만 커버하는 테스트가 되면 안된다.
- 테스트 케이스가 증가되면 증가될수록 프로덕션코드는 보다 더 제네럴해져야 한다.

### word wrap

- Getting stuck
    - 현재 실패하고 있는 테스트를 성공시키기 위해 점진적으로 할 수 있는 일이 없고
    - 테스트를 성공시키기 위해서는 아주 많은 양의 프로덕션 코드를 작성해야 하고
    - 극단의 경우 전체 알고리즘을 다시 작성해야만 하는 경우
- Getting stuck 이 발생하는 경우는
    - 잘못된 테스트를 작성했거나
    - 프로덕션 코드를 너무 구체적으로(too specific not generic) 하게 작성했거나
    - 둘 다이거나
- Getting Unstuck
    - most degenerate case를 먼저 작성하고
    - 이 복잡한 퀴즈를 아주 작은 스텝씩 올라가자
    - 각 경우에서 해당 테스트를 통과시키도록 specific한 fix를 하는것이 아니라 production code를 일반화하여 테스트가 통과되도록 하자