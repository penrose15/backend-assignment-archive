### practice jwt and oauth2

flow

1. 사용자가 client의 서비스에 접근
2. client는 사용자에게 접근 권한을 요청
3. client는 사용자를 Authorization server에 연결, Authorization server는 사용자에게 client가 리소스에 접근할 권한에 대해 허용여부를 직접 질의한다.
4. Authorization server는 client에게 엑서스 토큰과 교환할 수 있는 authorization code를 전달한다.
5. client는 authorization code를 이용하여 엑서스 토큰을 요청한다.
6. Authorization server는 authorization code를 확인하고 client에게 엑서스 토큰을 전달한다.
7. client는 Authorization server가 준 엑서스 토큰을 이용하여 Resource Server에게 필요한 리소스를 요청한다. 
8. Resource server는 엑서스 토큰을 확인 후 client에게 리소스를 전달한다
9. client는 전달받은 리소스를 이용해 사용자에게 서비스를 제공한다
