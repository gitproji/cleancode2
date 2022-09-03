package chapter6;

public class chapter6 {

    //////* 객체와 자료 구조 *//////

    /* 자료 추상화 */
    //구현을 감추려면 추상화가 필요하다.
    //추상 인터페이스를 제공해 사용자가 구현을 모른 채 자료의 핵심을 조작할 수 있어야 한다.
    //<추상적인 클래스>
    public interface Point {
        double getX();
        double getY();
        void setCartesian(double x, double y);
    }

    //추살 클래스 또 다른 예시 => 자동차 연료 상태를 백분율이라는 추상적인 개념으로 알려주어 정보가 어디서 오는지 전혀 드러나지 않는다.
    public interface Vehicle {
        double getPercentFuelRemaining();
     }

     //인터페이스나 조회/설정 함수만으로는 추상화가 이뤄지지 않는다
    //개발자는 객체가 포함하는 자료를 표현할 가장 좋은 방법을 고민해야 한다!


    /* 자료/객체 비대칭 */
    //객체는 추상화 뒤로 자료를 숨긴 채 자료를 다루는 함수만 공개한다.
    //자료구조는 자료를 그대로 공개하며 별다른 함수는 제공하지 않는다.

    //객체 지향 코드에서 어려운 변경은 절차적인 코드에서 쉬우며, 절차적인 코드에서 어려운 변경은 객체 지향 코드에서 쉽다.

    //////////////다시 읽어보기////////////////////


    /* 디미터 법칙 */
    //디미터 법칙은 잘 알려진 휴리스틱으로, 모듈은 자신이 조작하는 객체의 속사정을 몰라야 한다는 법칙이다.
    //클래스 C의 메서드 f는 다음과 같은 객체의 메서드만 호출해야 한다
    //클래스 C, f가 생성한 객체, f 인수로 넘어온 객체, C 인스턴스 변수에 저장된 객체

    //1) 기차 충돌
    final String outputDir = ctxt.getOprions().getScratchDir().getAbsolutePath();
    //<보완>
    Options opts = ctxt.getOptions();
    File scratchDir = opts.getScratchDir();
    final String outputDir = scratchDir.getAbsolutePath();
    //위 방식이 객체라면 디미터 법칙을 위반한 것이다. (자료 구조라면 적용x)
    //<좋은 코드>
    final String outputDir = ctxt.options.scratchDir.absolutePath;

    //2) 잡종 구조
    //절반은 객체, 절반은 자료 구조
    //새로운 함수는 물론이고 새로운 자료 구조도 추가하기 어렵다.

    //3) 구조체 감추기
    //객체는 뭔가를 하라고 말해야지 속을 드러내라고 하면 안된다.
    //<나쁜 코드>
    ctxt.getAbsolugePathOfScratchDirectoryOption();
    //<좋은 코드>
    BufferedOutputStream bos = ctxt.createScratchFileStream(classFileName);


    /* 자료 전달 객체 */
    //자료 구조체의 전형적인 형태는 공개 변수만 있고 함수가 없는 클래스다. (자료 전달 객체 : DTO)
    //좀 더 일반적인 형태는 bean구조다.
    //
    //활성 레코드는 DTO의 특수한 형태다
    //데이터베이스 테이블이나 다른 소스에서 자료를 직접 변환한 결과다.
    //활성 레코드에 비즈니스 구ㅠ칙 메서드를 추가해 이런 자료 구조를 객체로 취급하는 것은 바람직하지 않다.


    //객체는 동작을 공개하고 자료를 숨긴다 => 새 동작을 추가하기 어렵다
    //자료구조는 별다른 동작 없이 자료를 노출한다. => 새 자료 구조를 추가하기 어렵다.
}
