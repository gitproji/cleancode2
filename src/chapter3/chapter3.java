package chapter3;

public class chapter3 {

    //////* 읽기 쉽고 이해하기 쉬운 함수 *//////

    /* 1. 작게 만들어라 */
    //들여쓰기 수준은 1단이나 2단을 넘어서면 안 된다

    /* 2. 한 가지만 해라 */
    //지정된 함수 이름 아래에서 추상화 수준이 하나인 단계만 수행한다면 그 함수는 한 가지 작업만 한다

    //<나쁜 코드> - declarations, initializations, sieve 세 섹션으로 나눠진다
    public static int[] generatePrimes(int maxValue) {
        if (maxValue >= 2) {
            int s = maxValue + 1;
            boolean[] f = new boolean[s];
            int i;

            for (i=0; i<s; i++)
                f[i] = true;
            f[0] = f[1] = false;

            int j;
            for(i=2; i<Math.sqrt(s) + 1; i++) {
                if(f[i]) {
                    for(j=2*i; j<s; j+=i)
                        f[j] = false;
                }
            }

            int count = 0;
            for(i=0; i<s; i++) {
                if(f[i])
                    count++;
            }

            int[] primes = new int[count];

            for(i=0, j=0; i<s; i++) {
                if(f[i])
                    primes[j++] = i;
            }
            return primes;
        }
        else
            return new int[0];
    }

    /* 3. 함수 당 추상화 수준은 하나로 */
    //추상화 수준이란?
    // 해당 코드를 읽으면서 파악할 수 있는 정보의 수준.
    // 즉, 해당 코드로 더 자세한 정보를 알 수 있으면 추상화 더 낮아진다고 할 수 있다
    //
    //높은 추상화 수준 : getHtml()이라는 함수는 HTML을 가져오는 것은 알겠는데, 어떤 것이랑 연관되어 있는지 알 수 없기 때문에 추상화 수준이 높다고 할 수 있다
    //보통 추상화 수준 : String pagePathName = PathParser.render(pagepath);는 PathParser객체의 render 함수를 이용해 pagePathName을 가져올 수 있다는 정보를 유추할 수 있기 때문에 추상화 수준이 보통이라고 할 수 있다
    //낮은 추상화 수준 : .append("\n")는 바로 어떤 의미인지 유추 가능하기 때문에 추상화 수준이 낮다고 할 수 있다
    //
    //한 함수 내에 추상화 수준을 섞으면 코드를 읽는 사람이 헷갈린다
    //특정 표현이 근본 개념인지 아니면 세부사항인지 구분하기 어렵기 때문이다

    //위에서 아래로 코드 읽기 : 내려가기 규칙
    //코드는 위에서 아래로 이야기처럼 읽혀야 좋다
    //위에서 아래로 TO문단을 읽어내려 가듯이 코드를 구현하면 추상화 수준을 일관되게 유지하기 쉬워진다

    //<나쁜 코드>
    public static String testableHtml(PageData pageData, boolean includeSuitSetup ) {
        //...
        if(suiteSetup != null) {
            WikiPagePath pagePath = suiteSetup.getPageCrawler().getFullPath(suiteSetup);
            String pagePathName = PathParser.render(pagePath);
            buffer.append("!include -setup .")
                    .append("\n");
        }
        //...
    }


    /* 4. Switch 문 */
//    switch문은 작게 만들기 어렵고, n가지 처리를 한다.
//    switch문을 완전히 피할 방법은 없지만 각 switch문을 저차원 클래스에 숨기고 절대로 반복하지 않는 방법이 있다(다형성 이용)

    //<나쁜 코드>
    public Money calculatePay(Employee e) throws InvalidEmployeeType {
        switch (e.type) {
            case COMMISSIONED:
                return calculateCommissionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
            default:
                throw new InvalidEmployeeType(e.type);
        }
    }
    //문제 1. 함수가 길다
    //문제 2. 한 가지 작업만 수행하지 않는다
    //문제 3. SRP(Single Responsibility : 단일 책임 원칙)를 위반한다 (코드를 변경할 이유가 여럿이다)
    //문제 4. OCP(Open Closed Principle : 개방 폐쇄 원칙)를 위반한다 (새 직원 유형을 추가할 때마다 코드를 변경하기 때문)

    //<좋은 코드> - switch문을 추상 팩토리에 숨긴다
    public abstract class Employee {
        public abstract boolean isPayday();
        public abstract Money calculatePay();
        public abstract void deliverPay(Money pay);
    }
    //-------------------------
    public interface EmployeeFactory {
        public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
    }
    //--------------------------
    public EmployeeFactoryImpl implements EmployeeFactory {
        public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
            switch (r.type) {
                case COMMISSIONED:
                    return calculateCommissionedPay(e);
                case HOURLY:
                    return calculateHourlyPay(e);
                case SALARIED:
                    return calculateSalariedPay(e);
                default:
                    throw new InvalidEmployeeType(r.type);
            }
        }
    }


    /* 5. 서술적인 이름을 사용하라 */
    //함수 이름을 정할 때는 여러 단어가 쉽게 읽히는 명명법을 사용한다.
    //그런 다음, 여러 단어를 사용해 함수 기능을 잘 표현하는 이름을 선택한다.

    //서술적인 이름을 사용하면 개발자 머릿속에서도 설계가 뚜렷해지므로 코드를 개선하기 쉬워진다
    //(Setup page : 설정 페이지, Teardown page : 해제 페이지)
    //testableHtml -> SetupTeardownIncluder

    //일관성 있어야 한다 - 모듈 내에서 함수 이름은 같은 문구, 명사, 동사를 사용한다.
    //includeSetupAndTeardownPages, includeSetupPages, includeSuiteSetupPage, includeSetupPage
    //Teardown도 마찬가지!!


    /* 6. 함수 인수 */
    //함수에서 이상적인 인수 개수는 0개이고, 4개 이상은 특별한 이유가 있어도 사용하면 안 된다.
    //bad : includeSetupPageInto(newPageContent) - 함수이름과 인수 사이에 추상화 수준이 다르다. 게다가 별로 중요하지 않은 세부사항인 StringBuffer를 알아야한다.

    //인수에 질문을 던지는 경우 : boolean fileExists("MyFile")
    //인수를 뭔가로 변환해 결과를 반환하는 경우 : InputStream fileOpen("MyFile")
    //이벤트 함수 : passwordAttemptFailedNtimes(int attempts)
    //이벤트 함수는 이벤트라는 사실이 코드에 명확히 드러나야 하므로 이름과 문맥을 주의해서 선택한다.
    //위 세 경우가 아니라면 단항 함수(인수 1개)는 가급적 피해라

    //입력 인수를 변환하는 함수라면 변환 결과는 반환값으로 돌려주도록 해라.
    //void transform(StringBuffer pageText)     (x)
    //StringButter transform(StringBuffer in)   (o)

    //플래그 인수 사용하지 마라. (부울 값)
    //함수가 한꺼번에 여러 가지를 처리한다고 대놓고 공표하는 셈이다.

    //한 값을 표현하는 것이 아니라면(ex. 좌표) 이항 함수(인수 2개)는 이해하기 어렵다.
    //단항 함수로 바꿀 수 있다면 바꿔라

    //삼항 함수는 이항 함수보다 훨씬 더 이해하기 어렵고, 문제가 2배로 늘어난다.

    //인수가 2~3개 필요하다면 일부를 독자적인 클래스 변수로 선언할 가능성을 짚어보자

    //단항 함수는 함수와 인수가 동사/명사 쌍을 이뤄야 한다.   write(name)
    //더 나은 이름 : writeField(name) - name이 field라는 사실도 드러난다.
    //키워드 추가
    //assertEquals(expected, actual) -> assertExpectedEqualsActual(expected, actual)


    /* 7. 부수 효과를 일으키지 마라 */
    //<나쁜 코드>
    public class UserValidator {
        private Cryptographer cryptographer;

        public boolean checkPassword(String userName, String password) {
            User user = UserGateway.findByName(userName);
            if (user != User.NULL) {
                String codedPhrase = user.getPhraseEncodedByPassword();
                String phrase = cryptographer.decrypt(codedPhrase, password);
                if ("Valid Password".equals(phrase)) {
                    Session.initialize();
                    return true;
                }
            }
            return false;
        }
    }
    //checkPassword는 암호를 확인하는 함수인데 세션을 초기화한다.
    //그래서 함수 이름만 보고 함수를 호출하는 사용자는 사용자를 인증하면서 기존 세션 정보를 지워버릴 위험에 처한다.
    //함수 이름을 checkPasswordAndInitializeSession으로 하는 것이 훨씬 좋다.
    //물론 위 예시는 함수가 한 가지만 한다는 규칙도 위반했다.

    //출력 인수는 appendFooter(s) -> report.appendFooter()


    /* 8. 명령과 조회를 분리하라 */
    //함수는 뭔가를 수행하거나 뭔가에 답하거나 둘 중 하나만 해야 한다.
    //객체 상태를 변경하거나 아니면 객체 정보를 반환

    //<나쁜 코드>
    public boolean set(String attribute, String value);
    //함수 이름이 attribute인 속성을 찾아 값을 value로 설정한 후 성공하면 true 실패하면 false를 반환한다.
    if(set("username","unclebob")) {}
    //username이 unclebob으로 설정되어있는지 확인하는 코드인지 username을 unclebob으로 설정한다는 코드인지 구분하기 어렵다.

    //<좋은 코드> - 설정하는 함수와 존재를 확인하는 함수를 나눈다.
    if(attributeExists("username")) {
        setAttribute("username", "unclebob");
    }


    /* 9. 오류 코드보다 예외를 사용하라 */
    //오류 코드를 반환하면 호출자는 오류 코드를 곧바로 처리해야 한다는 문제에 부딪히기 때문에 명령/조회 분리 규칙을 미묘하게 위반한다.
    //반면 예외를 사용하면 오류 처리 코드가 원래 코드에서 분리되므로 코드가 깔끔해진다.
    //<나쁜 코드>
    if(deletePage(page) == E_OK) {}

    //<좋은 코드>
    //try/catch 블록은 별도 함수로 뽑아내는 편이 좋다. (정상 동작과 오류 처리 동작을 분리)
    public void delete(Page page) { //예외 처리 하는 함수 - 이 또한 한가지 작업만 해야한다.
        try {
            deletePageAndReferences(page);
        }
        catch (Exception e) {}
    }
    public void deletePageAndReferences(Page page) throws Exception{ //실제로 페이지를 제거하는 함수
        deletePage(page);
    }


    /* 10. 반복하지 마라 */
    //코드를 부모 클래스로 몰아 중복을 없앤다.
    //메소드로 만들어서 중복을 없앤다.


    /* 11. 구조적 프로그래밍 */
    //return문은 하나여야 하고, 루프 안에서 break나 continue를 사용해선 안 되며 goto는 절대로 안 된다.
    //함수를 작게 만든다면 return, break, continue를 여러 차례 사용해도 괜찮다.


    /* 12. 함수를 짜는 방법 */
    //처음엔 위에 소개한 규칙을 어긴 상태의 코드를 짜겠지만, 그 코드를 빠짐없이 테스트 하는 단위 테스트 케이스를 만들고 수정하는 과정을 거치면
    //모든 규칙을 따르는 함수가 얻어질 것이다. 물론 항상 단위 테스트를 통과한다.

}
