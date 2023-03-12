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

## DB 설계
#### [DB설계 링크](https://docs.google.com/spreadsheets/d/1G2XHEZBFKvZEbPCXA1HBjAaZSoXaw1s_i66xens9OvU/edit#gid=980509685)
![erd](https://user-images.githubusercontent.com/120003960/224560646-ff51a84a-89d4-4f1d-9f17-a6da4481d168.png)

user는 모든 테이블에 FK로 참조되며, post는 user를 제외한 모든 테이블에 FK로 참조된다.

## 기능 설명
이 프로젝트는 MVC 모델로 처리하였으며, Controller에서 Request를 전달 받아 비즈니스 로직을 처리하여 반환하는 구간을 BO라는 Service구간에서 처리.
<details><summary>회원 가입</summary>
<p>
  <strong><중복확인></strong> <br>
  View화면에서 회원가입 시 아이디가 중복 됐는 지 확인을 ajax통신을 통해 Controller에 <b>요청</b>.<br>
DB에 저장 되어있는 loginId를 BO에서 boolean으로 View의 input에 넣은 값과 같은 값을 가져왔는지, 안 가져왔는지 구분하여 Controller에 <b>반환</b>하였고
Controller에서는 가져왔을 땐(중복일 땐) 1번이라는 결과 값을 반환.<br>
View에서 Controller 응답을 1번으로 받았을 때는 “중복 된 아이디입니다”라고 띄워짐.<br>
중복이 없을 때는 회원가입 insert를 했다.<br>
<br>
<strong><DB저장></strong> <br>
  가입하기를 눌렀을 때 View화면에서 ajax로 RestController로 파라미터를 전달하여 <b>요청</b>.<br>
요청받은 파라미터를 db에 저장할 때 해싱 된 비밀번호로 저장 되기 위해 해싱 클래스(EncrypUtils)를 비밀번호를 16진수로 변환하는 로직을 만들어 RestController에서 바로 사용하여 해싱 된 비밀번호가 db에 저장하고 View로 <b>반환</b>.

 </p>
</details>
  
<details><summary>로그인/ 로그아웃</summary>
  <p>
  로그인하기를 눌렀을 때 View화면에서 Server쪽에 loginId, password가 중복이 있는지 확인 <b>요청</b><br>
  요청받은 파라미터 loginInd, password를 DB에 저장 된 열과 동일한지 mapper를 통해 중복건이 있는지 없는지 <b>응답</b><br>
로그인 시에는 HttpSession을 통해 로그인 동안 필요한 정보를 session에 담아두었다가,<br>
 로그아웃 시 session을 remove를 하여 지움
  </p>
  </details>
  
 <details><summary>게시글 올리기</summary>
  <p>
  게시글을 올릴 때에는 Imagepath에 저장되는 Multipart file과 post에 저장되는 글, 내용, 상태, 지역이 함께 게시 되도록 구현.<br>
    View 화면에서 필요한 사진들과 제목, 내용등등을 Server쪽에 ajax를 통해 <b>요청</b>.<br>
    ajax로 요청하기 위해 multipartFile을 올릴 수 있는<u>form 객체</u>를 만들어 필요한 파라미터들(제목, 내용, 사진 등등)을 append시켜 formData로 controller에 넘겨 요청하는 방식으로 진행.<br>
MultipartFile은 여러장을 보내야 하기 때문에  MultiFile을 넣을 리스트를 만들어 준 후<br>
    <b>Array.prototype.slice.call함수</b>로 <u>파일을 배열로 만들어주어</u>  만든 리스트 안에 <b>forEach()함수</b>를 통하여 </u>배열에 있는 각 요소에 대해 하나씩 읽어가면서 배열을 하나씩 넣음</u>.
<br><br>
Sever쪽에선 Request를 받은 후 post DB와 imagepath DB를 합쳐 가져와야 했는데, 저는 각각 BO(Service)를 만들어 postBO에서 postImagepathBO를 가져와 함께 insert를 시키고 해당 값이 들어있는 리스트를 <b>반환</b>.<br>
insert를 시킬 때 imagepath를 저장할 폴더를 만들어야 했기에 file을 저장할 fileManager, 저장될 파일을 리소스 핸들러로 url을 지정하는 class를 만듦.<br>
그리고 imagepathBO에서 이미지를 Multipart 파일 갯수 만큼 fileManager를 통해 file을 폴더를 만들어 폴더 안에 사진을 저장하였고, mapper를 통해 db에 저장.<br>
View화면에서는 Controller에서 응답받은 리스트를 forEach를 통하여 게시판 내용을 보여주었습니다.<br>
  
  </p>
</details>

 <details><summary>게시글 삭</summary>
  <p>
  게시글 삭제할 때는 게시글 insert와 같이 imagepath DB와 post DB를 같이 삭제함과 더불어 게시글에 달린 좋아요/입양 내용, 댓글도 함께 삭제하도록 만듬<br>
View에서 삭제 되어야 할 파라미터들을 명시한 후 ajax로 <b>요청</b>.<br>
요청 받은 파라미터들이 DB의 한 테이블안에 있는게 아니기 때문에 각각의 BO를 만들어 해당 데이터를 삭제하는 메소드들을 만듬.<br>
postImagepathBO는 사진을 삭제하고, likeadoptBO은 좋아요/입양이 눌려진 걸 삭제하는 메소드를 만들어<br> postBO에서 postImagepathBO와 likeadoptBO를 불러와 같이 삭제 되도록 함.

  </p>
  </details>
