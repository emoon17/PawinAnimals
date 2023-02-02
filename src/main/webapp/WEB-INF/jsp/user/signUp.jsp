<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container">

		<div class="input-boxs col-12">
			<div class="d-flex">
			<!-- 아이디 -->
				<input type="text" id="loginId" placeholder="아이디"
					style="height: 85px; width: 417px;"
					class="login-info form-control pl-3">
				<button type="button" class="check-btn btn ml-3 font-weight-bold"
					id="loginCheckBtn">중복</button>
			</div>

			<div id="userIdCheckLength" class="login-info text-danger d-none">아이디가
				비었습니다.</div>
			<div id="userIdFormat" class="login-info text-danger d-none">영어와
				숫자만 가능합니다.</div>
			<div id="idCheckDuplicated" class="login-info text-danger d-none">이미
				사용중인 ID입니다.</div>
			<div id="idCheckOk" class="login-info text-success d-none">사용
				가능한 ID 입니다.</div>
			
			<!-- 이름 -->
			<input type="text" id="name"
				placeholder="이름" style="height: 85px; width: 500px;"
				class="login-info form-control pl-3 mt-4">
			<div id="nameCheckLength" class="login-info text-danger d-none">이름이
				비었습니다.</div>

			<!-- 비밀번호 -->
			<input type="password" id="password" 
				placeholder="비밀번호" style="height: 85px; width: 500px;"
				class="login-info form-control pl-3 mt-4">
			<div id="passwordVal" class="login-info text-danger d-none">소문자,
				숫자만 혼합 사용하여 8글자만 가능합니다.</div>

			<input type="password" id="passwordCheck" 
				placeholder="비밀번호 재입력" style="height: 85px; width: 500px;"
				class="login-info form-control pl-3 mt-4">
			<div id="passwordCheckVal" class="login-info text-danger d-none">입력하신
				비밀번호와 일치하지 않습니다.</div>
			
			<!-- 전화번호 -->
			<input type="text" id="phoneNumber" 
				placeholder="전화번호 ex) 010-1111-2222"
				style="height: 85px; width: 500px;"
				class="login-info form-control pl-3 mt-4">
			<div id="phoneCheckLength" class="login-info text-danger d-none">전화번호를
				입력해주세요.</div>

			<div class="login_btn">
				<button type="button" class="user-btn btn mt-5 font-weight-bold"
					style="width: 500px; height: 85px;">회원가입</button>
			</div>
		</div>
	
	<div class="login-info text-center mt-5">
		계정이 있으신가요? <a href="/user/sign_in_view"
			class="logInOut_click font-weight-bold ml-4">로그인</a>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		// 아이디 중복확인 버튼을 눌렀을 때
		$('#loginCheckBtn').on('click', function(){
			//alert("dd");
			
			//validation
			let loginId = $('#loginId').val().trim();
			
			//초기화
			$('#userIdCheckLength').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			$('#userIdFormat').addClass('d-none');
			
			let regId = /^[A-Za-z]{1}[A-Za-z0-9_-]{3,19}$/ // 반드시 영문으로 시작 숫자+언더바/하이픈 허용 4~20자리
			if (loginId.length < 4){
				$('#userIdCheckLength').removeClass('d-none') // 경고문구 노출
				return;
			} 
			
			if (!regId.test(loginId)) {
				$('#userIdFormat').removeClass('d-none');
			}
			
			// ajax
			$.ajax({
				//request
				type:"post"
				, url:"/user/is_duplicated_id"
				, data:{"loginId":loginId}
				//response
				,success:function(data){
					if (data.code == 1) {
						// 성공
						// 중복
						$('#idCheckDuplicated').removeClass('d-none');
					} else {
						// 실패
						$('#idCheckOk').removeClass('d-none');
					}
				}
				, error:function(e){
					alert("중복확인에 실패하였습니다.");
				}
				
			});
		});  // -- 아이디 중복확인
		
		$('.user-btn').on('click', function(){
			
			//validation
			let name = $('#name').val().trim();
			let password = $('#password').val();
			let passwordCheck = $('#passwordCheck').val();
			let phoneNumber = $('#phoneNumber').val().trim();
			//alert(phoneNumber);
			//alert(name);
			// 초기화
			$('#nameCheckLength').addClass('d-none');
			$('#phoneCheckLength').addClass('d-none');
			$('#userIdCheckLength').addClass('d-none');
			$('#passwordVal').addClass('d-none');
			$('#passwordCheckVal').addClass('d-none');
			
			if ($('#idCheckOk').hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요.");
				return;
			}
			
			if (name == ''){
				$('#nameCheckLength').removeClass('d-none');
				return;
			}
			
			let regPw = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/; 
			if (!regPw.test(password)){
				$('#passwordVal').removeClass('d-none');
				return;
			}
			
			if (password != passwordCheck) {
				$('#passwordCheckVal').removeClass('d-none');
				return;
			}
			
			if (phoneNumber == ''){
				$('#phoneCheckLength').removeClass('d-none');
				return;
			}
			
			// ajax
			$.ajax({
   					//request
   					type:"post"
   					, url:"/user/sign_up"
   					, data:{
   						"name":name,
   						"loginId":loginId,
   						"password":password,
   						"phoneNumber":phoneNumber}
   				
				//response
				, success:function(data){
					if (data.code == 1){
						alert("가입을 환영합니다. 로그인 해주세요.");
						location.href="/user/signIn_view";
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e){
					alert("가입에 실패하였습니다.")
				}
			});
		
			
		}); // -- 회원가입 서브밋
	});
</script>






