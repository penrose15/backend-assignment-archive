package codestatePrac;
//오브젝트와 import에 대한 내용 같이 들어 있다


import backjoon.Main; // 다른 패키지 내의 클래스를 사용하기 위해 작성한다
//import backjoon.*; 해당 패키지의 모든 클래스를 패키지명 없이 쓸 수 있다

public class ObjectPrac { //extends Object 는 모든 클래스가 자동적으로 상속받고 있다

    //아무런 상속을 받지 않는 클래스에 자바가 자동적으로 붙여준다,
    Main m = new Main();
    //backjoon.Main m = new backjoon.Main(); 이렇게 해도 된다.

}

class ObjectPrac2 extends ObjectPrac{

    //objectPrac 클래스를 상속받고 있다
}