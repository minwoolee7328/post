# post
Use Case 
![유스케이스](https://github.com/minwoolee7328/post/assets/131872877/c2a6d5d1-eacd-4802-a509-3d3bf90d6638)

1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
	수정 API의 request는 @PathVariable를 사용해 쿼리스트링으로 게시물의 id 값과 password를 받았고
	@RequestBody 로 수정내용을 받았습니다.
	쿼리스트링으로 password를 받은 이유는 @RequestBody로 받은 내용들을 사용해 바로 update를 하기 위해서였고
	그과정에서 RequestBody에 password도 있다면 password도 변경을 해야해서 입니다.
	쿼리스트링으로 password를 받는다 해도 사용자에게 표시되기때문에 괜찮다고 생각했습니다.

	삭제 API의 request는 @PathVariable를 사용해 쿼리스트링으로 게시물의 id 값과 password를 받았습니다. 
	
	- apiChange 브랜치로 내용변경 =
	수정, 삭제 에서 쿼리스트링으로 password를 받던것을 변경해 더이상 password를 받지않고 @RequestBody를 이용해
	password를 json 데이터로 받게 변경 하였습니다.
	수정한 api 명세서도 업로드 하였습니다.

2. 어떤 상황에 어떤 방식의 request를 써야하나요?
	게시물을 수정, 삭제, 선택한 게시물을 조회 할땐 @PathVariable 를 사용해 어떠한 게시물으 선택했는지를 알려주도록
  하고, 게시물을 생성할땐 @RequestBody 와 같이 Json 데이터를 서버에서 확인 할 수 있도록 해주는 request를 사용합니다.


3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
   	게시물을 생성, 전체조회, 선택조회 에서는 필요한 데이터를 알맞은 방법으로 잘 가지고 왔다고 생각하지만
	수정 과 삭제에서 비밀번호 데이터를 쿼리스트링으로 받아오는것은 좀 잘못된 방법이라고 생각합니다. 

4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
	강의를 통해 완성한 예시를 활용하여 본 과제도 나누어봤습니다.
	결과적으로 알게된 점은 Repository는 클라이언트에서 받은 데이터를 활용해 데이터베이스를 쿼리로 관리 하거나
	클라이언트가 필요로 하는 데이터를 보내주기 위한 곳인것 같습니다.
	Controller 는 클라이언트가 데이터를 어떠한 형식으로  보냈는지 판단해 그에 맞게 서비스가 행동하도록 길을 
	알려주는것 같습니다.
	마지막으로 Service는 Controller와 Repository를 활용해 사용자에게 적절한 데이터를 조회시켜주는 역할인것 같습니다.

API 명세서

![api](https://github.com/minwoolee7328/post/assets/131872877/affd915b-2632-4e4c-8163-ae22f8357c19)


수정된 api
![image](https://github.com/minwoolee7328/post/assets/131872877/74dee124-36df-4e18-b36d-8b7ef48f85a2)
