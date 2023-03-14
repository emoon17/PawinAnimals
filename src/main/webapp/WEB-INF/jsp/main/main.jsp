<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div
	class="left-content col-6 d-flex align-items-center justify-content-center">
	<div class="content-box text-center">
		<div class="table-font menu_content font-weight-bold mt-4 text-center" style="height: 50px;" data-user-id="${userId}">아이들의 행방을
			찾아주세요!</div>
		<div class="nav-box  mb-5 d-flex align-items-center justify-content-center">
			<ui class="nav d-flex align-items-center">
				<c:forEach items="${postList}" var="post" varStatus="status">
					<li class="copy-font text-start"><a href="/post/look_for_family_detail_view?postId=${post.id}" class="text-start" >${status.count} . ${post.title}</a></li>
				</c:forEach>
			 </ui>
		</div>
	</div>
</div>

<div
	class="right-content col-6 d-flex align-items-center justify-content-center">
	<div class="content-box text-center">
		<div class="table-font menu_content font-weight-bold mt-4">병원 위치를 확인하세요!</div>
		<div
			class="nav-box d-flex align-items-center justify-content-center mb-5">
			<ui class="nav d-flex align-items-center">
			<c:forEach items="${hospitalList}" var="hospital" varStatus="status">
			<li class="copy-font"><a href="/hospital_list_detail_view?name=${hospital.name}&coordinateX=${hospital.X}&coordinateY=${hospital.Y}&address=${hospital.address}">${status.count} . ${hospital.name}</a></li>
			</c:forEach>
			</ui>
		</div>
	</div>
</div>
