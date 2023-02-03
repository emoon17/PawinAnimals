<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="d-flex justify-content-center align-items-center ">

		<div class="create-box">
			<input id="WriteTitle" type="text" class="write-box write-area"
				style="height: 70px; width: 800px;" placeholder="  제목을 입력하세요">
			<div class="line"></div>
			<textarea id="WriteArea" class="wirte-box write-area border-0"
				style="height: 500px; width: 800px;" placeholder="  내용을 입력하세요"></textarea>


			<%-- 이미지 업로드를 위한 버튼--%>
			<div class="d-flex justify-content-end">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none"
						accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<input type="button" id="fileUpLoadBtn" value="사진 첨부"
						class="btn btn-lg">

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2"></div>
				</div>
			</div>
			<div class="line mt-3"></div>
			<%-- select 박스 만들기 --%>
			<div class="d-flex">
				<label for="status">상황 : </label>
					<select name="status" class="ml-4">
						<option value="상황">--상황--</option>
						<option value="목격">목격</option>
						<option value="실종">실종</option>
						<option value="찾음">찾음</option>
						<option value="입양가능">입양 가능</option>
						<option value="입양완료">입양 완료</option>
					</select>
				
			</div>
		</div>
		<!-- createBox -->


	</div>
</div>

<script>
	$(document).ready(function() {
		// 파일선택 업로드 버튼 누르기 뒤에 있는 file 인풋 누르기
		$('#fileUpLoadBtn').on('click', function() {
			$('#file').click();

		});
	});
</script>
