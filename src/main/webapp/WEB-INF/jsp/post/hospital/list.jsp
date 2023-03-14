<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container list-box">
	<div class="search-box d-flex align-items-center justify-content-between">
	<span class="write-area font-weight-bold ml-3">우리 동물병원을 검색해서 찾아보세요!</span>
		<!-- 지역 서치 -->
		<div class="d-flex">
		<input type="text" class="titleSearch form-control copy-font mr-3"
			id="address" placeholder="    지역을 입력하세요"
			style="height: 50px; width: 200px;">
		<button type="button" class="header-btn btn mb-2 font-weight-bold copy-font"
			id="serachBtn" style="height: 50px; width: 70px;">검색</button>
		</div>
	</div>
	
	
	<!-- 글 목록 -->
	<div id="contentsBox" class="contents-box ">
		<div class="contents-parent-box d-flex flex-wrap justify-content-between">
			<table class="table h-100 w-100 text-center">
				<thead>
					<tr>
						<th class="menu_login_info font-weight-bold">NO.</th>
						<th class="menu_login_info font-weight-bold">상호</th>
						<th class="menu_login_info font-weight-bold">주소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${hospitalList}" var="hospital" varStatus="status">
					<tr>
						<td class="table-font font-weight-bold">${status.count}</td>
						<td class="table-font font-weight-bold"><a href="/hospital_list_detail_view?name=${hospital.name}&coordinateX=${hospital.X}&coordinateY=${hospital.Y}&address=${hospital.address}">${hospital.name}</a></td>
						<td class="table-font font-weight-bold">${hospital.address}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
