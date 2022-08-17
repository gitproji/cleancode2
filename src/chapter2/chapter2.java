package chapter2;

///////* 이름을 잘 짓는 규칙 *///////

/* 1. 의도를 분명히 밝혀라 */

// 존재 이유, 수행 기능, 사용 방법이 드러나도록 이름을 지정해야 한다.
//<나쁜예시>
//int d; //아무런 의미도 드러나지 않는다.
//
//<좋은예시>
//int elapsedTimeInDay; //의도가 드러난다.
//int daysSinceCreation;

//코드 맥락이 코드 자체에 명시적으로 드러나야 한다.
//<나쁜예시>
//public List<int[]> getThem() {
//    List<int[]> list1 = new ArrayList<int[]>();
//    for(int[] x : theList)  //theList에 무엇이 들었는가?
//        if(x[0] == 4)       //왜 0번째 값이고 4는 무슨 의미?
//            list1.add(x);
//    return list1;           //list1을 어떻게 사용하는가?
//}

//<좋은예시> - 지뢰찾기 게임을 만든다고 가정
//public List<int[]> getFlaggedCells() {
//    List<int[]> flaggedCells = new ArrayList<int[]>();
//    for(int[] cell : gameBoard)
//        if(cell[STATUS_VALUE] == FLAGGED)
//            flaggedCells.add(x);
//    return flaggedCells;
//}
//단순성을 변하지 않았지만, 이름을 바꾸면서 코드가 명확해졌다.

//더 나아가 int 배열을 사용하는 대신, 칸을 간단한 클래스로 만들어도 된다.
//isFlagged라는 명시적인 함수를 사용해 FLAGGED라는 상수를 감춰도 좋다.
//public List<Cell> getFlaggedCells() {
//    List<Cell> flaggedCells = new ArrayList<Cell>();
//    for(Cell cell : gameBoard)
//        if(cell.isFlagged())
//            flaggedCells.add(cell);
//    return flaggedCells;
//}


/* 2. 그릇된 정보를 피하라 */

// - 널리 쓰이는 의미가 있는 단어를 다른 의미로 사용하면 안된다.
// - 약어 사용에 주의
// - 특수한 의미가 있는 단어 사용x (ex. List 등)
// - 서로 흡사한 이름을 사용하지 않도록 주의
// - 유사한 개념은 유사한 표기법으로 사용(일관성)
// - l과 1, O과 0 구분이 어려움


/* 3. 의미 있게 구분하라 */

//동일한 범위 안에서 다른 두 개념에 같은 이름을 사용하지 못한다.
//컴파일러를 통과할지라도 연속된 숫자를 덧붙이거나 불용어를 추가하는 방식은 적절하지 않다.
//이름이 달라야 한다면 의미도 달라져야 한다.
//
//<나쁜예시> - 연속된 숫자를 덧붙인 이름
//public static void copyChars(char a1[], char a2[]) {
//    for(int i = 0; i < a1.length; i++) {
//        a2[i] = a1[i];
//    }
//}
//함수 인수 이름으로 source와 destination을 사용한다면 코드 읽기가 훨씬 더 쉬워진다.

//불용어를 추가한 이름은 아무런 정보도 제공하지 못할 뿐더러 중복이다.
//customerInfo는 customer와, accountDate는 account와, theMessage는 message와 구분이 안된다.


/* 4. 발음하기 쉬운 이름을 사용하라 */

//발음하기 어려운 이름은 토론하기도 어렵다.
//새로운 개발자가 들어오면 변수를 설명해줘야 한다.

//<나쁜예제>
//class DtaRcrd102 {
//    private Date genymdhms;
//    private Date modymdhms;
//    private final String pszqint = "102";
//    /*...*/
//};
//
//class Customer {
//    private Date generationTimestamp;
//    private Date modificationTimestamp;
//    private final String recordId = "102";
//    /*...*/
//};


/* 5. 검색하기 쉬운 이름을 사용하라 */

//문자 하나를 사용하는 이름과 상수는 텍스트 코드에서 쉽게 눈에 띄지 않는다는 문제점이 있다
//문자 하나를 검색하면 해당 문자가 들어가는 부분이 모두 검색되기 때문에 검색이 어렵다
//이러한 관점에선 긴 이름이 짧은 이름보다 좋고, 검색하기 쉬운 이름이 상수보다 좋다
//
// - 간단한 메서드에서 로컬 변수만 한 문자를 사용한다. 이름 길이는 범위 크기에 비례해야 한다
// - 변수나 상수를 코드 여러 곳에서 사용한다면 검색하기 쉬운 이름이 바람직하다
//
//<나쁜예제>
//for(int j=0; i<34; j++) {
//    s += (t[j]*4)/5;
//}
//
//<좋은예제>
//int realDaysPerIdealDay = 4;
//const int WORK_DAYS_PER_WEEK = 5;
//int sum = 0;
//for(int j=0; j<NUMBER_OF_TASKS; j++) {
//    int realTaskDays = taskEstimate[j] * realDaysPerIdealDay;
//    int realTaskWeeks = (realTaskDays / WORK_DAYS_PER_WEEK);
//    sum += realTaskWeeks;
//}
//나쁜예제와 비교해서 sum은 최소한 검색이 가능하고, 상수의 이름은 길지만 찾기 쉽다


/* 6. 인코딩을 피하라 */

//<인코딩을 피해야 하는 경우>
//@ 헝가리식 표기법 : 변수 및 함수의 이름 인자 앞에 데이터 타입을 명시하는 코딩 규칙
// - 변수, 함수, 클래스 이름이나 타입을 바꾸기가 어려워지며, 읽기도 어려워진다
// ex) PhoneNumber phoneString; //타입이 바뀌어도 이름은 바뀌지 않는다

//@ 멤버 변수 접두어 : 멤버 변수에 m_ 접두어
// - 사람들은 접두어를 무시하고 이름을 해독하는 방식을 재빨리 익힌다
// ex) m_des -> desciption

//<인코딩이 필요한 경우>
//인터페이스 클래스 이름은 IShapeFactory나 ShapeFactoryImp로 접두어나 접미어를 붙이고
//구현 클래스 이름은 ShapeFactory로 지정한다


/* 7. 자신의 기억력을 자랑하지 마라 */

//루프에서 반복 횟수를 세는 i, j, k를 제외하곤 문자 하나만 사용하는 변수는 사용하지 마라.
//나중에 다시 보거나 독자가 볼 때 헷갈리지 않는 변수명으로 지정해야 한다.


/* 8. 클래스 이름 */

//클래스 이름과 객체 이름은 명사나 명사구가 적합하다. 동사는 사용하지 않는다.
// ex) Customer, WikiPage, AddressParser 등


/* 9. 메서드 이름 */

//메서드 이름은 동사나 동사구가 적합하다.
//ex) postPayment, deletePage, save 등

//접근자, 변경자, 조건자는 javabean 표준에 따라  값 앞에 get, set, is를 붙인다.
//ex) getName, setName, isPosted 등

//생성자를 중복정의할 때는 정적 팩토리 메서드를 사용한다
//메서드 인수를 설명하는 이름을 사용한다
//Complex fulcrumPoint = mew Complex(23.0); -> Complex fulcrumPoint = Complex.FromRealNumber(23.0);


/* 10. 기발한 이름은 피하라 */

//재미난 이름보다 명료한 이름을 선택하라


/* 11. 한 개념에 한 단어를 사용하라 */

//추상적인 개념 하나에 단어 하나를 선택해 이를 고수한다
//예를 들어, 똑같은 메서드를 클래스마다 fetch, retrieve, get으로 제각각 부르면 혼란스럽다
//일관성있는 단어를 사용하라


/* 12. 말장난을 하지 마라 */

//한 단어를 두 가지 목적으로 사용하지 마라
//예를 들어, 지금까지 구현한 add 메서드는 모두가 기존 값 두개를 더하거나 이어서 새로운 값을 만든다고 가정하자
//새로 작성하는 메서드는 집합에 값 하나를 추가한다고 했을 때, 기존 add 메서드와 맥락이 다르므로 inset나 append라는 이름이 적당하다


/* 13. 해법 영역에서 가져온 이름을 사용하라 */

//코드를 읽을 사람도 프로그래머라는 사실을 명심한다
//그러므로 전산 용어, 알고리즘 이름, 패턴 이름, 수학 용어 등을 사용해도 괜찮다
//기술 개념에는 기술 이름이 가장 적합한 선택이다


/* 14. 문제 영역에서 가져온 이름을 사용하라 */

//적절한 '프로그래머 용어'가 없다면 문제 영역에서 이름을 가져온다
//문제 영역 개념과 관련이 깊은 코드라면 문제 영역에서 이름을 가져와야 한다


/* 15. 의미 있는 맥락을 추가하라 */

//대다수의 이름이 스스로 의미가 분명하지 않다
//그러므로 접두어를 추가해 맥락을 분명하게 하거나 클래스를 생성하여 맥락을 좀 더 분명하게 한다.
//함수를 끝까지 읽어보고 나서야 맥락을 유추할 수 있는 코드는 피하라.


/* 16. 불필요한 맥락을 없애라 */

//이름에 불필요한 맥락을 추가하지 않도록 주의한다
//'고급 휘발유 충전소(Gas Station Deluxe)'라는 애플리케이션을 짠다고 해서 모든 클래스 이름을 GSD로 시작하는 것은 바람직하지 않다


public class chapter2 {
}
