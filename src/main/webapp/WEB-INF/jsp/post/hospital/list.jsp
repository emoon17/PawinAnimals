<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container list-box">
	<div class="search-box d-flex align-items-center justify-content-between">
	<span class="write-area font-weight-bold ml-3">우리 지역 동물병원을 찾아보세요!</span>
		<!-- 지역 서치 -->
		<div>
		<select id="area" name="area"
			class="content-foot-box text-center copy-font"
			style="height: 50px; width: 150px;">
			<option value="">지역</option>
			<option value="강원도">강원도</option>
			<option value="광주광역시">광주광역시</option>
			<option value="경기도">경기도</option>
			<option value="경상남도">경상남도</option>
			<option value="경상북도">경상북도</option>
			<option value="대전광역시">대전광역시</option>
			<option value="부산광역시">부산광역시</option>
			<option value="서울특별시">서울특별시</option>
			<option value="세종특별자치시">세종특별자치시</option>
			<option value="인천광역시">인천광역시</option>
			<option value="울산광역시">울산광역시</option>
			<option value="전라남도">전라남도</option>
			<option value="전라북도">전라북도</option>
			<option value="충청남도">충청남도</option>
			<option value="충청북도">충청북도</option>
		</select>
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
						<th class="menu_login_info font-weight-bold">지역</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="table-font font-weight-bold">1</td>
						<td class="table-font font-weight-bold">설동이네</td>
						<td class="table-font font-weight-bold">서울특별시</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
