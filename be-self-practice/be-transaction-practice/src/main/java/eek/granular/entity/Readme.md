### flush()
* 영속성 컨텍스트의 변경 내용을 DB에 반영하는 것을 말한다.
* Transaction commit이 일어날 때 flush가 동작하는데 이때 쓰기 지연 저장소에 쌓아놨던 INSERT, UPDATE, DELETE SQL들이 DB에 날라간다.
  * 영속성 컨텍스트를 비우는 것이 아님!
  * 영속성 컨텍스트의 변경 사항들과 DB의 상태를 맟추는 작업이다.
