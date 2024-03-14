# be-homework-controller

* @RequestParam : 요청의 파라미터에 연결할 매개변수에 붙는 어노테이션이다.


```
@PostMapping
public ResponseEntity postMethod(@RequestParam(name="쿼리스트링 이름", required=true/false) 파라미터타입 매개변수명) {...}
```

대충 이런식으로 작성된다
required의 디폴트 값은 true이다

