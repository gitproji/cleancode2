package chapter4;

public class chapter4 {

    //////* 주석 *//////

//    주석은 실패한 코드를 만회하기 위해 사용하는 것이다.
//    코드는 변화하고 진화하기 때문에 오래된 주석일수록 완전히 그릇될 가능성이 커진다.
//    그러므로 주석을 가능한 줄이도록 꾸준히 노력해야 한다.

    /* 주석은 나쁜 코드를 보완하지 못한다 */
    //모듈의 짜임새가 엉망이면 주석을 달기보다 코드를 정리하라


    /* 코드를 의도로 표현하라 */
    //예시) 직원에게 복지 혜택을 받을 자격이 있는지 검사
    //<나쁜 코드>
    //if((employee.flags & HOURLY_FLAG) && (employee.age > 65))
    //<좋은 코드>
    //if(employee.isEligibleForFullBenefits())


    /* 좋은 주석 */
    //1) 법적인 주석
    //회사가 정립한 구현 표준에 맞춰 법적인 이유로 특정 주석을 넣어야할 때
    //소스 파일 첫머리에 들어가는 계약 조건이나 법적인 정보 등

    //2) 정보를 제공하는 주석
    //반환값 -> 함수 이름에 정보를 담는 편이 더 좋다
    //정규표현식 설명 -> 클래스를 만들어 코드를 옮겨주면 주석 필요 x

    //3) 의도를 설명하는 주석
    //

    //4) 의도를 명료하게 밝히는 주석
    //반환값이 표준 라이브러리나 변경하지 못하는 코드에 속한다면 의미를 명료하게 밝히는 주석이 유용하다

    //5) 결과를 경고하는 주석
    //예) 특정 테스트 케이스를 꺼야하는 이유를 설명하는 주석
    //      - 여유 시간이 충분하지 않다면 실행하지 마십시오

    //<좋은 코드>
    public static SimpleDateFormat makeStandardHttpDateFormat() {
        //SimpleDateFormat은 스레드에 안전하지 못하다.
        //따라서 각 인스턴스를 독립적으로 생성해야 한다.
        SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM  yyyy HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df;
    }

    //6) TODO 주석
    //앞으로 할 일을 남기는 것

    //7) 중요성을 강조하는 주석
    String listItemContent = match.group(3).trim();
    //여기서 trim은 정말 중요하다. trim 함수는 문자열에서 시작 공백을 제거한다.
    //문자열에 시작 공백이 있으면 다른 문자열로 인식되기 때문이다.
    new ListItemWidget(this, listItemContent, this.level + 1);
    return buildList(text.substring(match.end()));

    //8) 공개 API에서 Javadocs
    //Javadocs 역시 그릇된 정보를 전달할 가능성이 존재한다.


    /* 나쁜 주석 */
    //1) 주절거리는 주석
    //이해가 안 되어 다른 모듈까지 뒤져야 하는 주석은 독자와 제대로 소통하지 못하는 주석이다.

    //2) 같은 이야기를 중복하는 주석
    //코드와 중복된 내용의 주석은 달지마라

    //3) 오해할 여지가 있는 주석
    //오해의 여지가 없도록 엄밀하게 주석을 달아야 한다.

    //4) 의무적으로 다는 주석
    //모든 함수에 Javadocs를 달거나 모든 변수에 주석을 달아야 한다는 규칙은 어리석다.

    //5) 이력을 기록하는 주석
    //소스 코드 관리 시스템이 있으므로 이제는 완전히 제거하는 편이 좋다.

    //6) 있으나 마나 한 주석
    //너무 당연한 사실을 언급하며 새로운 정보를 제공하지 못하는 주석은 달지마라

    //7) 무서운 잡음
    //문서를 제공해야 한다는 잘못된 욕심으로 잡음이 탄생할 수 있다.

    //8) 함수나 변수로 표현할 수 있다면 주석을 달지 마라
    //<나쁜 코드>
    //전역 목록 <smodule>에 속하는 모듈이 우리가 속한 하위 시스템에 의존하는가?
    //if (smodule.getDependSubsystems().contains(subSysMod.getSubSystem()))
    //
    //<좋은코드>
    //ArrayList moduleDependees = smodule.getDependSubsystemss();
    //String ourSubSystem = subSysMod.getSubStstem();
    //if(moduleDependees.contains(ourSubSystem))

    //9) 위치를 표시하는 주석
    // Actions //////////////////////
    //와 같은 배너는 아주 드물게 사용하는 편이 좋다.

    //10) 닫는 괄호에 다는 주석
    //while() { } //while 같은 주석은 작고 캡슐화된 함수에는 잡음일 뿐이다.

    //11) 공로를 돌리거나 저자를 표시하는 주석
    //소스 코드 관리 시스템은 누가 언제 무엇을 추가했는지 기억하기 때문에 쓸모없다

    //12) 주석으로 처리한 코드
    //소스 코드 관리 시스템에 기록이 되기 때문에 코드를 삭제해버려도 좋다

    //13) HTML 주석
    //HTMl 주석은 읽기 어렵다. 쓰지마라

    //14) 전역 정보
    //주석을 달아야 한다면 근처에 있는 코드만 기술하라

    //15) 너무 많은 정보
    //주석에다 흥미로운 역사나 관련 없는 정보를 장황하게 늘어놓지 마라

    //16) 모호한 관계
    //주석에서 설명하는 부분이 무엇인지 명확하게 해야한다

    //17) 함수 헤더
    //짧고 한 가지만 수행하며 이름을 잘 붙인 함수가 주석으로 헤더를 추가한 함수보다 훨씬 좋다

    //18) 비공개 코드에서 Javadocs
    //공개 API는 Javadocs가 유용하지만 공개하지 않을 코드라면 Javadocs는 쓸모가 없다





}
