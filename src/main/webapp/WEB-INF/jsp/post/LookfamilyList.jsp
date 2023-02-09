<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container list-box">
	<div class="search-box d-flex align-items-center">
	<!-- 글 서치 -->
		<select id="status" name="status"
			class="content-foot-box ml-4 text-center copy-font"
			style="height: 50px; width: 150px;">
			<option value="목격">목격</option>
			<option value="실종">실종</option>
			<option value="찾음">찾음</option>
			<option value="입양가능">입양 가능</option>
			<option value="입양완료">입양 완료</option>
		</select> 
		
		<select id="animals" name="animals"
			class="content-foot-box ml-4 text-center copy-font"
			style="height: 50px; width: 150px;">
			<option value="고양이">고양이</option>
			<option value="강아지">강아지</option>
			<option value="기타">기타</option>
		</select> 
		
		<select id="area" name="area"
			class="content-foot-box ml-4 text-center copy-font"
			style="height: 50px; width: 150px;">
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
		
		<input type="text" class="titleSearch form-control copy-font" id="title" placeholder="  제목을 입력하여주세요" style="height: 50px; width: 320px;">
		
		<button type="button" class="header-btn btn ml-3 copy-font" id="serachBtn" style="height: 50px; width: 70px;">검색</button>
		<!-- 글 등록을 눌렀을 때 userId가 널인지 아닌지 확인 후 로그인 창으로 넘기거나, 로그인 되어있으면 글쓰기 화면으로 이동  -->
		<a href="/post/look_for_family_create_view"><button type="button" class="header-btn btn ml-3 copy-font" style="height: 50px; width: 70px;">글 등록</button></a> 
	</div>
	<div class="line"></div>
	
	<!-- 글 목록 -->
	<div class="contents-box bg-info">
		<div class="contents-parent-box d-flex flex-wrap justify-content-between">
			<article class="post-box">
                <img src="/static/image/footer2.jpg" alt="이미지" width="300" height="300"> 
                	<div class="copy-font ml-3">상황 : </div>
                    <div class="copy-font ml-3">지역 : </div>
                    <div class="copy-font ml-3">제목 :</div>
                    <div class="copy-font ml-3">동물 종 :</div>
                   
            </article>
            <article class="list-box">
                <img src="/static/image/footer2.jpg" alt="이미지" width="300" height="300"> 
                	<div class="font-weight-bold"></div>
                    <div class="media-info-text"></div>
                    <div class="media-info-text"></div>
            </article>
            <article class="list-box">
                <img src="/static/image/footer2.jpg" alt="이미지" width="300" height="300"> 
                	<div class="font-weight-bold"></div>
                    <div class="media-info-text"></div>
                    <div class="media-info-text"></div>
            </article>
            <article class="list-box">
                <img src="/static/image/footer2.jpg" alt="이미지" width="300" height="300"> 
                	<div class="font-weight-bold"></div>
                    <div class="media-info-text"></div>
                    <div class="media-info-text"></div>
            </article>
		</div>
		
	</div>
</div>