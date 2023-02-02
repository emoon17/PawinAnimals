<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<form id="signInForm" action="/user/sign_in" method="post">
		<div class="input-boxs justify-content-center col-12">
			<input type="text" id="loginId" name="loginId" placeholder="아이디"
				style="height: 85px; width: 500px;"
				class="login-info form-control pl-3">
			<div id="userIdCheckNone" class="login-info text-danger d-none">아이디가 비었습니다.</div>
			
			 <input type="password"
				id="password" name="password" placeholder="비밀번호"
				style="height: 85px; width: 500px;"
				class="login-info form-control pl-3 mt-4">
			<div id="passwordCheckNone" class="login-info text-danger d-none">비밀번호가 비었습니다.</div>
			
			<div class="login_btn">
				<button type="submit" class="user-btn btn mt-5 font-weight-bold"
					style="width: 500px; height: 85px;">로그인</button>
			</div>
		</div>
	</form>
</div>

<script>	

	$(document).ready(function() {
		//로그인 버튼을 눌렀을 때
		$('#signInForm').on('submit', function(e){
			//submit 기능 중단
			e.preventDefault();
			//alert("dd");
			
			//validation
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val();
			//alert(loginId);
			
			
			$('#userIdCheckNone').addClass('d-none');// 초기화
			$('#passwordCheckNone').addClass('d-none');
			if (loginId == '') {
				$('#userIdCheckNone').removeClass('d-none');
				return;
			}
			
			if (password == '') {
				$('#passwordCheckNone').removeClass('d-none');
				return;
			}
			
			let url = $(this).attr('action'); // form태그의 action속성
			let params = $(this).serialize(); // form태그 name 값들 가져오기
			
			$.post(url, params) //request
			.done(function(data) {
				if(data.code == 1){
					location.href = "/main/main_view";
				} else {
					alert(data.errorMessage);
				}
			});
		});
	});

</script>