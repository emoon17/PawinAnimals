<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container list-box">
	<div class="search-box d-flex align-items-center">
		<!-- 글 서치 -->
		<select id="status" name="status"
			class="content-foot-box ml-4 text-center copy-font"
			style="height: 50px; width: 150px;">
			<option value="">상태</option>
			<option value="목격">목격</option>
			<option value="실종">실종</option>
			<option value="찾음">찾음</option>
			<option value="입양가능">입양 가능</option>
			<option value="입양완료">입양 완료</option>
		</select> <select id="animals" name="animals"
			class="content-foot-box ml-4 text-center copy-font"
			style="height: 50px; width: 150px;">
			<option value="">동물 종</option>
			<option value="고양이">고양이</option>
			<option value="강아지">강아지</option>
			<option value="기타">기타</option>
		</select> <select id="area" name="area"
			class="content-foot-box ml-4 text-center copy-font"
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
		</select> <input type="text" class="titleSearch form-control copy-font"
			id="title" placeholder="  제목을 입력하여주세요"
			style="height: 50px; width: 320px;">
		<button type="button" class="header-btn btn ml-3 copy-font"
			id="serachBtn" style="height: 50px; width: 70px;">검색</button>
		<!-- 글 등록을 눌렀을 때 userId가 널인지 아닌지 확인 후 로그인 창으로 넘기거나, 로그인 되어있으면 글쓰기 화면으로 이동  -->

		<c:if test="${not empty userId}">
			<a href="/post/look_for_family_create_view"><button type="button"
					class="header-btn btn ml-3 copy-font"
					style="height: 50px; width: 80px;" data-user-id="${userId}">글
					등록</button></a>
		</c:if>
		<c:if test="${empty userId}">
			<a href="/user/signIn_view"><button type="button" id="createBtn"
					class="header-btn btn ml-3 copy-font"
					style="height: 50px; width: 80px;" data-user-id="${userId}">글
					등록</button></a>
		</c:if>

	</div>
	<div class="line"></div>

	<!-- 글 목록 -->
	<div id="contentsBox" class="contents-box ">
		<div
			class="contents-parent-box d-flex flex-wrap justify-content-between">
			<c:forEach var='posts' items='${postList}' varStatus="status">
				<!--  var="imagePathView" items="${postView.imagePathList}"  imagePathView.imagePath.imagePath-->
				<article id="postBox" class="post-box">
					<img src="${posts.imagePathList[0].imagePaths.imagePath}" alt="이미지"
						width="300" height="300" class="list-box">
					<div>${posts.post.id}</div>
					<div class="copy-font ml-3 font-weight-bold">
						제목 : <span class="ml-3"> ${posts.post.title}</span>
					</div>
					<div class="copy-font ml-3 font-weight-bold">
						상황 : <span class="ml-3">${posts.post.status}</span>
					</div>
					<div class="copy-font ml-3 font-weight-bold">
						동물 종: <span class="ml-3">${posts.post.animals}</span>
					</div>
					<div class="copy-font ml-3 font-weight-bold">
						지역 : <span class="ml-3">${posts.post.area}</span>
					</div>
				</article>
			</c:forEach>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 글등록 버튼을 눌렀을 때
		$('#createBtn').on('click', function() {
			// 비로그인 시 로그인창으로 이동할 때
			let userId = $(this).data('user-id');
			//alert(userId);
			if (userId == '') {
				alert("로그인 후 사용 가능합니다.");

			}
		});

		// 검색 버튼을 눌렀을 때
		$('#serachBtn').on('click', function() {
			let searchTitle = $('#title').val();
			let searchStatus = $("#status option:selected").val();
			let searchAnimals = $("#animals option:selected").val();
			let searchArea = $("#area option:selected").val();
			//alert(searchTitle);
			//  alert(searchStatus);
			//alert(searchAnimals);
			// alert(searchArea);
		

			// ajax
			$.ajax({
				//request
				type : 'get',
				url : '/post/search_list_view',
				data : {
					"searchTitle" : searchTitle,
					"searchStatus" : searchStatus,
					"searchAnimals" : searchAnimals,
					"searchArea" : searchArea
				}

				//response
				,
				success : function(data) {
					console.log(data);
					
					$('body').html(data);

				},
				error : function(e) {
					alert(e + "오류가 발생했습니다. 다시 시도해주세요.");
				}

			});
		});
	});
</script>