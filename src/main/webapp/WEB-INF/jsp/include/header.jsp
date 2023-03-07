<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<div class=" d-flex align-items-center pt-3">
				<div class="display-4 font-weight-bold pl-4">
					<a href="/main/main_view">Pawin Animals</a>
				</div>
				<div class="login-menu-icon d-flex align-items-center justify-content-end pr-5">
					<ui class="nav font-weight-bold">
					
					<c:if test="${empty userId}">
						<li><a href="/user/signIn_view">로그인</a></li>
						<li><a href="/user/signUp_view">회원가입</a></li>
					</c:if>
					
					<c:if test="${not empty userId}">
						<li>${loginId} 님 환영합니다!</li>
						<li><a href="/user/signOut">로그아웃</a></li>
					</c:if>
					
						<li>
						<input type="checkbox" id="menuicon">
							<label for="menuicon">
								<span></span>
								<span></span>
								<span></span>
							</label>
							<div class="sidebar">
								<div class="menu-tap d-flex m-3">
									<table class="table h-100 w-100">
										<thead>
											<tr>
												<th class="menu_subject font-weight-bold">Menu</th>
												<c:if test="${empty userId}">
												<th class="menu_login_info font-weight-bold"><a href="/user/signIn_view">로그인</a></th>
												<th class="menu_login_info font-weight-bold"><a href="/user/signUp_view">회원가입</a></th>
												</c:if>
												<c:if test="${not empty userId}">
												<th class="menu_login_info font-weight-bold"><a href="#">${loginId}</a></th>
												<th class="menu_login_info font-weight-bold"><a href="/user/signOut">logout</a></th>
												</c:if>
											</tr>
										</thead>
										
										<tbody>
											<tr>
												<td colspan='3' class="menu_content font-weight-bold pt-5"><a href="/post/look_for_family_list_view">가족을 찾고 있습니다 </a></td>
											</tr>
											<tr>
												<td colspan='3' class="menu_content font-weight-bold pt-5"><a href="/post/adopt_review_list_view">입양 후기 리스트</a></td>
											</tr>
											<tr>
												<td colspan='3' class="menu_content font-weight-bold pt-5"><a href="#">반려용품 무료 나눔</a></td>
											</tr>
											<tr>
												<td colspan='3' class="menu_content font-weight-bold pt-5"><a href="/hospital_list_view">동물병원 위치 안내</a></td>
											</tr>
											<!-- <tr>
												<td colspan='3' class="menu_content font-weight-bold pt-5"><a href="#">유기동물 보호 시설</a></td>
											</tr> -->
										</tbody>
									</table>
								</div>
							</div>
						</li>
					</ui>
				</div>
			</div>
			<div class=" d-flex align-items-center justify-content-center ">
				<img src="/static/image/dog.jpg" width="200" height="200">
				<img src="/static/image/cat1.jpg" width="200" height="200">
			</div>