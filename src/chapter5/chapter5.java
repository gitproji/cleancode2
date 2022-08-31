package chapter5;

import java.io.InputStream;
import java.net.Socket;

public class chapter5 {

    //////* 형식 맞추기 *//////

    /* 형식을 맞추는 목적 */
    //코드 형식은 의사소통의 일환으로 매우 중요하다
    //오랜 시간이 지나 원래 코드의 흔적을 더 이상 찾아보기 어려울 정도로 코드가 바뀌어도
    //맨 처음 잡아놓은 구현 스타일과 가독성 수준은 유지보수 용이성과 확장성에 계속 영향을 미친다.


    /* 원활한 소통을 장려하는 코드 형식 */
    //1. 적절한 행 길이를 유지하라
    //200줄 정도인 파일로도 커다란 시스템을 구축할 수 있다.

    //2. 신문 기사처럼 작성하라
    //이름만 보고도 올바른 모듈을 살펴보고 있는지 아닌지를 판단할 정도로 신경 써서 지어라
    //첫 부분은 고차원 개념과 알고리즘을 설명한다
    //아래로 내려갈수록 의도를 세세하게 묘사한다
    //마지막에는 가장 저차원 함수와 세부 내역이 나온다.

    //3. 개념은 빈 행으로 분리하라
    //빈 행은 새로운 개념을 시작한다는 시각적 단서다. 코드를 읽어 내려가다 보면 빈 행 바로 다음 줄에 눈길이 멈춘다.
    //빈 행이 없다면 가독성이 현저히 떨어진다

    //4. 세로 밀집도
    //세로 밀집도는 연관성을 의미한다. 즉, 서로 밀접한 코드 행은 세로로 가까이 놓아야 한다.

    //5. 수직 거리
    //서로 밀접한 개념은 세로로 가까이 둬야 한다.
    //연관성이 깊은 두 개념이 멀리 떨어져 있으면 코드를 읽는 사람이 소스 파일과 클래스를 여기저기 뒤지게 된다.
    //
    //변수는 사용하는 위치에 최대한 가까이 선언한다.
    //인스턴스 변수는 클래스 맨 처음에 선언한다.
    public class test {

        int iv; // 인스턴스 변수
        static int cv; // 클래스 변수

        void method() {
            int lv; // 지역 변수
        }
    }
    //
    //한 함수가 다른 함수를 호출한다면 두 함수는 세로로 가까이 배치한다.
    //또 가능하다면 호출하는 함수를 호출되는 함수보다 먼저 배치한다.
    //친화도가 높을수록 코드를 가까이 배치한다.
    static public void assertTrue() {}

    static public void assertTrue(boolean condition) {}

    static public void assertFalse() {}

    static public void assertFalse(boolean condition) {}

    //6. 세로 순서
    //호출되는 함수는 호출하는 함수보다 나중에 배치한다.
    //가장 중요한 개념을 가장 먼저 표현한다.
    //세세한 사항은 가장 마지막에 표현한다.
    public void analyzeFile(File javaFile) throws Exception {
        ...
        measureLine(line);
        ...
    }

    public  void measureLine(String line) {
        ...
    }


    //7. 가로 형식 맞추기
    //20자에서 60자 사이인 행이 총 행 수의 40%에 달한다.
    //10자 미만은 30% 정도로 보인다. 프로그래머는 명백하게 짧은 행을 선호한다.
    //필자는 120자 정도로 행 길이를 제한함.

    //8. 가로 공백과 밀집도
    //가로 공백을 사용해 밀접한 개념과 느슨한 개념을 표현한다.
    private void measureLice(String line, int count) {}
    //함수명과 괄호는 서로 밀접 -> 공백 x
    //매개변수는 공백으로 분리해서 별개인 것을 보여준다.
    //
    //연산자 수선순위를 강조하기 위해서도 공백을 사용
    (-b + a) / (a*2)
    //연산자같은 경우엔 안타깝게도 나중에 도구에서 없애는 경우가 흔하다

    //9. 가로 정렬
    //변수를 선언할 때 가로 정렬해서 선언하면 변수 유형은 무시하고 변수 이름부터 읽게 된다.
    //<나쁜 코드>
    private Socket      socket;
    private InputStream input;
    private long        requestProgress;
    private boolean     hasError;
    //
    //<좋은 코드>
    private Socket socket;
    private InputStream input;
    private long requestProgress;
    private boolean hasError;
    //
    //마찬가지로, 할당문에서도 할당 연산자는 보이지 않고 오른쪽 피연산자에 눈이 간다.
    //<나쁜 코드>
    this.context =  context;
    socket =        s;
    input =         s.getInputStream();
    //
    //<좋은 코드>
    this.context = context;
    socket = s;
    input = s.getInputStream();
    //
    //정렬하지 않으면 오히려 중대한 결함을 찾기 쉽다.
    //따라서 정렬은 사용하지 마라.

    //10. 들여쓰기
    //들여쓰기한 파일은 구조가 한눈에 들어오지만, 들여쓰기 하지 않은 코드는 열심히 분석해야한다.
    //<나쁜 코드>
    if(i>9) { return ""; }
    //
    //<좋은 코드>
    if(i>9) {
        return "";
    }

    //11. 가짜 범위
    //빈 while문이나 for문은 최대한 피하고, 피하지 못할 때는 빈블록을 올바로 들여쓰고 괄호로 감싼다.
    //빈 while문을 쓸 땐 새 행에다가 세미콜론을 제대로 들여써서 넣어줘라


    /* 팀 규칙 */
    //팀은 한 가지 규칙에 합의해야 하고 모든 팀원은 그 규칙을 따라야 한다.

}
