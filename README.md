# PawinAnimals
-----------
## 기술 정보

### Paltforms & Languages
`java` `Spring Boot` `JavaScript` `CSS` `Bootstrap` `Html` `JQuery` `AWS` `My SQL`
### Tools
`Eclipse IDE` `Visual Studio code` `Tomcat`, `GitHub`
### SNS
##### [naver blog](https://blog.naver.com/hello_world0_07)

## 목차
+ <span style="color:black">[제작 계기](#제작-계기)</span>
+ [개요](#개요)
+ [DB 설계](#DB-설계)
+ [기능 설명](#기능-설명)
+ [프로젝트 사용 방법](#프로젝트-사용-방법)
+ [트러블 이슈](#트러블-이슈)

## 제작 계기
> 현재 우리나라는 반려동물 1500만 시대입니다.
>그로인해 국내 실종하는 반려동물의 수도 기하급수적으로 늘어나고 있습니다.
>예전에는 전단지를 붙였지만 전단지는 그 때 뿐 그 동물의 생김새와 보호자의 번호는 기억이 나지 않습니다.
>그래서 지나가다 실종 반려동물을 마주치면 사이트에 실시간으로 올리며, 정보를 공유하여 실종 가족들에게 도움이 되고, 입양을 기다리는 동물들은 가족을 만날 수 있는 다리 역할이 되는 사이트를 만들고 싶었습니다.

## 개요
#### 이 사이트를 사용하면 이 기능들을 사용하실 수 있습니다.
+ 실종 반려 동물 상태 실시간 확인
+ 여러 사진들과 함께 게시글 올리기
+ 동물병원 위치 확인하기
+ (동영상 넣기)

## DB 설계
#### [DB설계 링크](https://docs.google.com/spreadsheets/d/1G2XHEZBFKvZEbPCXA1HBjAaZSoXaw1s_i66xens9OvU/edit#gid=980509685)
![erd](https://user-images.githubusercontent.com/120003960/224655460-ce281824-8fcb-4dbd-823a-ea6904123455.png)

user는 모든 테이블에 FK로 참조되며, post는 user를 제외한 모든 테이블에 FK로 참조된다.

## 기능 설명
이 프로젝트는 MVC 모델로 처리하였으며, Controller에서 Request를 전달 받아 비즈니스 로직을 처리하여 반환하는 구간을 BO라는 Service구간에서 처리.
<details><summary>회원 가입</summary>
<p>
  <strong><중복확인></strong> <br>
<img src ="https://user-images.githubusercontent.com/120003960/224636731-11f38553-c122-474b-a799-92e62de3ce26.png" width="400" height="400">

View화면에서 회원가입 시 아이디가 중복 됐을 때 빨간 글씨로 "이미 사용중인 ID입니다."를 띄움.
<br>
    
<strong><DB저장></strong> <br>
<img src="https://user-images.githubusercontent.com/120003960/224638301-08ba419e-be97-4d65-b867-4920c166aaae.png" width="700" height="50"><br>
  비밀번호 저장 시 해싱 된 비밀번호를 저장.

 </p>
</details>
  
<details><summary>로그인/ 로그아웃</summary>
  <p>
  로그인하기를 눌렀을 때 loginId, password가 중복이 있는지 확인 후 중복건이 없을 시에만 로그인 후 모든 메뉴 사용권한 획득<br><br>
 로그아웃 시 메뉴 사용 권한 없어짐.
  </p>
  </details>
  
 <details><summary>게시글 올리기</summary>
  <p>
  <img src="https://user-images.githubusercontent.com/120003960/224639687-e5a3c519-fd94-4167-a644-5e97433286a9.png" width="400" height="400"> <br>
  게시글을 올릴 때에는 3장 이하의 사진들을 넣을 수 있으며, 제목, 내용, 상태, 지역, 동물종이 빠짐 없이 넣을 수 있도록 구현.<br>
  </p>
</details>

 <details><summary>게시판 리스트</summary>
  <p>
  <img src="https://user-images.githubusercontent.com/120003960/224786959-76faf3da-a2d0-457c-8cd2-ea9f0b4d6430.png" width="450" height="400"><br>
  유저들이 올린 게시글 리스트를 볼 수 있다<br><br>
  <img src="https://user-images.githubusercontent.com/120003960/224786965-98d6636f-09ce-4015-8772-42ba580d9082.png" width="450" height="400"><br>
    검색한 화면만 볼 수도 있다.
  </p>
 </details>

 <details><summary>게시글 삭제</summary>
  <p>
  
  게시글 삭제할 때는 게시글 글과 사진들, 게시글에 달린 좋아요/입양 내용, 댓글도 함께 삭제하도록 만듬<br>

  </p>
  </details>
 <details><summary>좋아요 / 입양 누르기, 해제하기</summary>
  <p>
  <img src="https://user-images.githubusercontent.com/120003960/224642209-feac2226-3028-45c5-aa01-40f02c007c06.png" width="400" height="400"> <br>
  좋아요를 눌렀을 때는 하트와, 체크표시 색깔이 변하고 좋아요 갯수를 눌렀을 때는 모달로 누른 사람들의 로그인 아이디를 확인 할 수 있다.
<br>

  </p>
  </details>
 <details><summary>댓글 달기 / 삭제하기</summary>
  <p>
  <img src="https://user-images.githubusercontent.com/120003960/224642947-ffb499a1-3c7f-4d6d-a78a-6404460b5cde.png" width="400" height="400"><br>
   댓글을 작성 시 화면 이동 없이 머무르게 되며, 댓글 오른쪽 아이콘을 누르면 삭제가 가능하다.
  </p>
  </details>
  <details><summary>글 상세화면 보기</summary>
  <p>
  <img src="https://user-images.githubusercontent.com/120003960/224643208-f89cad87-23e2-4278-af59-cb637237011f.png" width="400" height="600"><br>
  좋아요와 댓글을 쓰며 유저들끼리 소통 창고가 되는 화면이다.
  </p>
  </details>
   <details><summary>병원 리스트 api</summary>
  <p>
  <img src="https://user-images.githubusercontent.com/120003960/224643713-a51e3807-b819-4349-9f79-fd87dc7d36d7.png" width="400" height="600"><br>
  현재 운영중인 병원들만 있으며, 병원 이름을 검색하여 볼 수도 있다.
</p>
</details>
    
<details><summary>카카오 api</summary>
  <p>
 <img src="https://user-images.githubusercontent.com/120003960/224644286-4a6fb9bb-163a-4416-9461-baff261a6f27.png" width="400" height="400"><br>
 병원 주소 파악을 위한 화면이다.
</p>
 </details>
 
 
## 시연 영상 
> ### 유튜브 주소 클릭 부탁드립니다!
> #### https://youtu.be/9_x5TtqgX6E
 


## 트러블 이슈 
#### 1. 게시글 작성 화면 View에서 작성완료를 눌러 Controller로 요청할 때 file이 null로 Controller에 넘어옴.

원인 : 작성 완료 버튼을 <button></button> 태그로 만들어 type을 지정 하지 않았더니 자동으로 submit으로 넘어가게 되어 ajax랑 같이 작동해서 안 넘어옴.

해결방법 : button type을 button으로 지정하고, ajax의 data를 formData로 넘겨주었더니 file의 값도 넘어옴.

느낀 점 : submit과 ajax가 서로 데이터를 주고받는점에서는 같지만 둘을 같이 사용했을 땐 submit으로 작동이 되는걸 알게되어 다음부터는 ajax를 사용할지 submit을 사용할지 먼저 구상 후에 한 개만 넣는 로직을 짜게 되는 계기가 되었음.

#### 2. 글쓰기 insert 시 imagepathDB에 있는 postId가 null로 들어감 

원인 : imagepathBO 이미지를 DB에 저장하기 위해서는 저장하는 postId가 필요한데, post를 insert할 때 imagepath도 postBO에 가져와 같이 insert를 하고 난 후에야 postId가 DB에 생기기 때문에 postId를 가져올 곳이 없었음.


1) ImagePath DB 칼럼 중 postId 삭제하기 
문제점 : postId를 사진과 mapping할 다른 방안이 없음.

2) useGeneratedKeys 를 사용하여 자동 생성 된 키 가져오기
Controller에서 ajax 요청 데이터를 Post 객체와 MultipartFile로 받고 세션에 있던 userId까지 합쳐 BO로 파라미터를 보냄.
BO에서는 insert시 useGeneratedKeys 로 인해 자동 생성 된 postId의 키를 세팅하는 로직을 구성.
Post객체에 파라미터로 넘어온 userId의 값을 setUserId로 ajax에서 요청 받지 못한 userId를 채워 넣었고, 완성 된 Post자체를 insert하였고 imagepath에 postId를 넣을 때 insert시 자동 생성 된 postId를 꺼낼 수 있게 되어 post.getId로 getting하여 꺼내 활용.

느낀 점: Imagepath에서 postId를 꺼낼 쓸 수 없다고 간단하게 Imagepath에서 postId를 지워버렸을 때 세세한 고민을 하지 않고 무작정 쉽게만 넘어가려고 해버렸다.
imagepath DB를 짰을 때는 postId가 왜 필요한지 생각하면서 테이블을 짜놓고 막상 쉽게 안 되니까 필요한 나무 가지를 잘라버린거였다.
그래도 imagepath에서 postId를 지우고 나니 처음에 제가 왜 그 컬럼을 넣었는지 다시 상기시키게 되었고, 쉽게 생각을 잘라버린 자신을 반성하고 문제가 생겼을 때 원초적인 문제를 생각할 수 있는 계기가 되어 좋은 트러블 이슈였다.

#### 3. 카카오맵 API 구현하기

동물병원 리스트 api를 가져왔을 떄 이미 그 안에 x, y 좌표가 있었기 때문에 그걸 가져와 카카오맵에 x, y좌표를 넣었는데 빈 화면만 나왔다.

원인 : 공공포탈과 카카오맵의 좌표계는 서로 달랐기 때문에 공공포탈의 x, y 좌표를 넣어도 소용이 없음.

해결방법 : proj4j라이브러리를 사용해 공공포탈의 좌표를 카카오맵의 좌표로 좌표계 변환하기.

공공포탈은 BESSEL타원체의 좌표계 EPSG:2097을 사용하였고, 카카오맵은 GRS80타원체의 좌표계 EPSG:5181을 사용하였기 때문에 EPSG:2097을 EPSG:5181로 바꾸는 작업을 하여 좌표계를 변경 함.

느낀 점: 타원체와 좌표계의 개념을 모른 채 로직을 구성하였더니 어느 부분이 잘못 된지 모른 채 로직만 계속 바꿨다.
잘못이 시작 된 원인을 찾아 고민해보니 타원체마다 좌표계가 다르다는 걸 알게 되었다. 
코드만 쓴다고 되는게 아니라 구성하는 부분의 전체적인 개념을 공부하여 알고 있어야 코드 구성을 완전히 할 수 있다는 걸 느끼게 되는 계기였다.
