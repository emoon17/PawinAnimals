<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<form id="signInForm" action="/user/sign_in" method="post">
		<div class="input-boxs justify-content-center col-12">
			<input type="text" id="loginId" name="loginId" placeholder="아이디"
				style="height: 85px; width: 500px;"
				class="login-info form-control pl-3"> <input type="password"
				id="password" name="password" placeholder="비밀번호"
				style="height: 85px; width: 500px;"
				class="login-info form-control pl-3 mt-4">
			<div class="login_btn">
				<button type="submit" class="user-btn btn mt-5 font-weight-bold"
					style="width: 500px; height: 85px;">로그인</button>
			</div>
		</div>
	</form>
</div>