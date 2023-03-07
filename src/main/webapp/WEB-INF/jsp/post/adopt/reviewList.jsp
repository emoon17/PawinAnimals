<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container list-box">

	<div class="search-box d-flex align-items-center justify-content-end">
		<!-- 글 서치 -->
		<div id="logoFont" class="font-weight-bold text-start">아이들의 꽃길을
			구경하세요!</div>
		<select id="animals" name="animals"
			class="content-foot-box text-center copy-font"
			style="height: 50px; width: 150px;">
			<option value="">동물 종</option>
			<option value="고양이">고양이</option>
			<option value="강아지">강아지</option>
			<option value="기타">기타</option>
		</select> <input type="text" class="titleSearch form-control copy-font"
			id="title" placeholder="  제목을 입력하여주세요"
			style="height: 50px; width: 320px;">
		<button type="button" class="header-btn btn ml-3 copy-font"
			id="serachBtn" style="height: 50px; width: 70px;">검색</button>

		<!-- 글 등록을 눌렀을 때 userId가 널인지 아닌지 확인 후 로그인 창으로 넘기거나, 로그인 되어있으면 글쓰기 화면으로 이동  -->
		<c:if test="${not empty userId}">
			<a href="/post/adopt_review_view"><button type="button"
					class="header-btn btn ml-3 copy-font"
					style="height: 50px; width: 80px;" data-user-id="${userId}">글
					등록</button></a>
		</c:if>
		<c:if test="${empty userId}">
		</c:if>
	</div>
	<div class="line"></div>

	<!-- 글 목록 -->
	<div id="contentsBox" class="contents-box ">
		<div
			class="contents-parent-box d-flex flex-wrap justify-content-between">
			
				<!--  var="imagePathView" items="${postView.imagePathList}"  imagePathView.imagePath.imagePath-->
			<c:forEach var='posts' items='${postList}' varStatus="status">
				<article id="postBox" class="post-box">
					<a href="/post/adopt_review_list_view?postId=${posts.post.id}" data-toggle="modal" data-target="#postModal"> <img
						src="${posts.imagePathList[0].imagePaths.imagePath}" alt="이미지"
						width="300" height="300" class="list-box"> <%-- <div>${posts.post.id}</div> --%>
						<div class="copy-font ml-3 font-weight-bold">
							제목 : <span class="ml-3"> ${posts.post.title}</span>
						</div>
						<div class="copy-font ml-3 font-weight-bold">
							동물 종: <span class="ml-3">${posts.post.animals}</span>
						</div>
					</a>
				</article>
		</div>
	</div>
</div>

<!--adopt post Modal -->
<div class="modal fade" id="postModal">
	<%--...을 눌렀을 때 post-data-id를 모달에 심어놓을거다. --%>
	<%-- modal sm: 작은 모달 창  --%>
	<%-- modal centered: 모달 창 수직으로 가운데 정렬 --%>
	<div class="modal-dialog modal-lg modal-dialog-centered">
		<div class="modal-content">
			<c:forEach items='${posts.imagePathList}' var="image">
				<div class="py-3">
				   <img src="${image.imagePaths.imagePath}" class="w-100"
							alt="본문 이미지">
				</div>
			</c:forEach>
				
			<div class="py-3 border-bottom content-area ml-4">
				   ${posts.post.content}
				   
			</div>

			<%-- 삭제하기 --%>
			<c:if test="${userId eq posts.user.id}">
				<div class="py-3 border-bottom text-center">
					<a href="#" id="deletePostBtn"
						class="content-area font-weight-bold" data-post-id="${posts.post.id}">삭제하기</a>
				</div>
			</c:if>
		</div>
	</div>
</div>
</c:forEach>

<script>
	$(document).ready(function() {
		// 글 삭제 버튼 눌렀을 때
		$('#deletePostBtn').on('click', function(){
			//alert("d");
			let postId = $(this).data('post-id');
			alert(postId);
			
			//ajax
			$.ajax({
				//request
				type:"delete"
				, url:"/post/post_delete"
				, data:{"postId":postId}
				//response
				, success:function(data){
					if (data.code == 1){
						alert("게시글이 삭제되었습니다.");
						location.href="/post/adopt_review_list_view";
					} else{
						alert(data.errorMessage);
					}
				}
				, error:function(e){
					alert("오류가 발생했습니다.");
				}
			});
		}); // post delete
		
		
	}); 
</script>