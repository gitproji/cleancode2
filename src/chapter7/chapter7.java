package chapter7;

public class chapter7 {

    //////* 오류 처리 *//////

    /* 오류 코드보다 예외를 사용하라 */
    //오류가 발생하면 예외를 던지는 방식이 논리가 오류처리 코드와 뒤섞이지 않기 때문에 호출자 코드가 더 깔끔해진다.

    /* Try-Catch-Finally 문부터 작성하라 */    //->다시 읽어보기
    //try 블록에 들어가는 코드를 실행하면 어느 시점에서든 실행이 중단된 후 catch 블록으로 넘어갈 수 있다.
    //try 블록에서 무슨 일이 생기든지 catch 블록은 프로그램 상태를 일관성 있게 유지해야 한다.
    //TDD를 사용해 필요한 나머지 논리를 추가할 수 있다.
    //
    //먼저 강제로 예외를 일으키는 테스트 케이스를 작성한 후 테스트를 통과하게 코드를 작성하는 방법을 권장
    //그러면 자연스럽게 try 블록의 트랜잭션 범위부터 구현하게 되므로 범위 내에서 트랜잭션 본질을 유지하기 쉬워진다.


    /* 미확인(unchecked) 예외를 사용하라 */   //-> 다시 읽어보기
    //미확인 예외 : Runtime Exception -> 런타임 시점에서 예외 발생 확인
    //checked Exception -> 컴파일 시점에서 예외 확인
    //지금은 안정적인 소프트웨어를 제작하는 요소로 확인된(checked) 예외가 반드시 필요하지는 않다.
    //
    //확인된 예외는 OCP(Open Closed Principle)를 위반한다.
    //하위 단계에서 코드를 변경하면 상위 단계 메서드 선언부를 전부 고쳐야 한다.
    //아주 중요한 라이브러리를 작성한다면 모든 예외를 잡아야한다.
    //하지만 일반적인 애플리케이션은 의존성이라는 비용이 이익보다 크다.


    /* 예외에 의미를 제공하라 */
    //오류 메시지에 실패한 연산 이름과 실패 유형 등의 정보를 담아 예외와 함께 던진다.
    //로깅 기능을 사용한다면 catch 블록에서 오류를 기록하도록 충분한 정보를 넘겨준다


    /* 호출자를 고려해 예외 클래스를 정의하라 */
    //애플리케이션에서 오류를 정의할 때 프로그래머에게 가장 중요한 관심사는 오류를 잡아내는 방법이 되어야 한다.
    //
    //외부 API를 사용할 때는 감싸기 기법이 최선이다.
    //외부 API를 감싸는 것의 장점
    // 1) 외부 라이브러리와 프로그램 사이에서 의존성이 크게 줄어든다.
    // 2) 다른 라이브러리로 갈아타는 비용이 적다.
    // 3) 감싸기 클래스에서 외부 API를 호출하는 대신 테스트 코드를 넣어주는 방법으로 프로그램을 테스트하기도 쉬워진다.
    // 4) 특정 업체가 API를 설계한 방식에 발목 잡히지 않는다.
    //
    //한 예외는 잡아내고 다른 예외는 무시해도 괜찮은 경우라면 여러 예외 클래스를 사용한다.


    /* 정상 흐름을 정의하라 */
    //앞서 지침을 충실히 따른다면 코드 대부분이 깨끗해지지만, 그러다보면 오류 감지가 프로그램 언저리로 밀려난다.
    //외부 API를 감싸 독자적인 예외를 던지고, 코드 위에 처리기를 정의해 중단된 계산을 처리하는 방식이 때로는 적합하지 않을 수 있다.
    //<나쁜 코드> - 예외가 논리를 따라가기 어렵게 만든다.
    try {
        MealExpenses expenses = expenseReporDAO.getMeals(employee.getId());
        m_total += expenses.getTotal();
        } catch(MealExpensesNotFound e) {
        m_total += getMealPerDiem();
    }
    //ExpenseReportDAO를 고쳐 언제나 MealExpense 객체를 반환하고,
    //청구한 식비가 없다면 일일 기본 식비를 반환하는 MealExpense 객체를 반환하는 방식으로 바꿔라
    public class PerDiemMealExpenses implements MealExpenses {
        public int getTotal() {
            //기본값으로 일일 기본 식비를 반환한다.
        }
    }
    //
    //이를 특수 사례 패턴이라 한다. -> 클래스를 만들거나 객체를 조작해 특수 사례를 처리하는 방식
    //클래스나 객체가 예외적인 상황을 캡슐화해서 처리하므로 클라이언트 코드가 예외적인 상황을 처리할 필요가 없어진다.


    /* null을 반환하지 마라 */
    public void registerItem(Item item) {
        if(item != null) {
            ItemRegistry registry = persitentStore.getItemRegistry();
            if(registry != null) {
                Item existing = registry.getItem(item.getID());
                if(existing.getBillingPeriod().hasRetailOwner()) {
                    existing.register(item);
                }
            }
        }
    }
    //위 코드는 null을 반환하는 나쁜 코드다.
    //null을 반환하는 코드는 일거리를 늘릴 뿐만 아니라 호출자에게 문제를 떠넘긴다.
    //메서드에서 null을 반환하고픈 유혹이 든다면 그 대신 예외를 던지거나 특수 사례 객체를 반환한다.
    //사용하려는 외부 API가 null을 반환한다면 감싸기 메서드를 구현해 예외를 던지거나 특수 사례 객체를 반환하는 방식을 고려한다.
    //많은 경우에 특수 사례 객체가 손쉬운 해결책이다.
    //<나쁜 코드>
    List<employee> employees = getEmployees();
    if(employees != null) {
        for(Employee e : employees) {
            totalPay += e.getPay();
        }
    }
    //<좋은 코드> -> 코드도 깔끔해질뿐더러 NullPointerException이 발생할 가능성도 줄어든다.
    public List<Employee> getEmployees() {
        if (.. 직원이 없다면 ..)
            return Collections.emptyList();
    }


    /* null을 전달하지 마라 */
    //메서드에서 null을 반환하는 방식도 나쁘지만 메서드로 null을 전달하는 방식은 더 나쁘다.
    //정상적인 인수로 null을 기대하는 API가 아니라면 메서드로 null을 전달하는 코드는 최대한 피하라.


    //오류 처리를 프로그램 논리와 분리해 독자적인 사안으로 고려하면 튼튼하고 깨끗한 코드를 작성할 수 있다.


}
