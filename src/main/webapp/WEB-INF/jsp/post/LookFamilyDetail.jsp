<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%-- <h1>${postView[0].post.id}</h1> --%>
<c:forEach items='${postView}' var="postview">
	<div class="container list-box">

		<div
			class="d-flex align-itmes-end justify-content-end font-weight-bold">
			<fmt:formatDate value="${postview.post.createdAt}"
				pattern="yyyy-mm-dd a hh:mm:ss" />
		</div>

		<%-- 제목 --%>
		<div class="content-subject mb-2 ml-3">${postview.post.title}</div>

		<div class="line mb-5"></div>
		<%--사진 이미지 --%>
		<div class="card-img">
			<!-- 시간 남으면 슬라이스로 볼 수 있게하기 -->
			<c:forEach items='${postview.imagePathList}' var="image">
				<img src="${image.imagePaths.imagePath}" class="w-100 mb-4"
					alt="본문 이미지">
			</c:forEach>
		</div>

		<%-- 글 내용 --%>
		<div class="content-area">
			<span class="m-2">${postview.post.content}</span>
		</div>

		<div class="line mb-2"></div>

		<%-- 로그인 아이디 --%>
		<div class="mt-2">
			<span class="font-weight-bold m-1 menu_login_info">
				${postview.user.loginId}</span>
		</div>
		<%-- 좋아요 --%>
		<div class="d-flex justify-content-between">
			<div class="like-adopt d-flex align-items-center mt-3">
				<%-- 좋아요가 되어있을 때 --%>
				<%-- 	<c:if test="${card.filledLike eq true}"> --%>
				<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
					data-post-id="${postview.post.id}"> <img
					src="/static/image/heart-icon.png" width="30" height="30"
					alt="filled heart">
				</a>
				<%-- 	</c:if> --%>
				<%-- 좋아요가 해제되어 있을 때 --%>
				<c:if test="${postviews.filledLike eq false}">
					<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
						data-post-id="${postview.post.id}"
						data-likeadopt-type="like"> <img
						src="/static/image/like-icon.png" width="30" height="30"
						alt="empty heart">
					</a>
				</c:if>

				<span class="content-area ml-3 mr-5"> 좋아요
					${postviews.likeCount}개 </span>

				<%-- 입양 희망자가 없을 때 --%>
				<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
					data-post-id="${postview.post.id}"> <img
					src="/static/image/adopt_empty.png" width="45" height="45"
					alt="filled heart" data-likeadopt-type="adopt">
				</a> <span class="content-area ml-3 mr-5"> 입양희망자
					${postviews.Count}명 </span>

			</div>
			<%-- 모달로 글 수정, 삭제하기 --%>
			<%-- <c:if test="${userId eq d.user.id}"> --%>
			<a href="#" class="more-btn write-area mt-3" data-toggle="modal"
				data-target="#modal" data-post-id="${postviews.post.id}"> 수정|삭제
			</a>
			<%-- </c:if> --%>
		</div>
		<div class="line mb-3"></div>

		<%-- 댓글 목록 --%>
		<div class="card-comment-list ">

			<%-- 댓글내용 --%>
			<c:forEach items="${postview.commentViewList}" var="commentView">
				<div class="card-comment  d-flex justify-content-between m-3">
					<div class="font-weight-bold write-area ">
						${commentView.user.loginId}<span class="ml-3">:</span> <span
							class="write-area ml-3">${commentView.comment.content}</span>
					</div>

					<%-- 댓글 삭제 버튼 --%>
					<a href="#" class="commentDelBtn mt-1"><img
						src="/static/image/delete.png" width="30px" height="33x"></a>
				</div>
			</c:forEach>
			<%--댓글 달기 --%>
		</div>
		<c:if test="${not empty userId}">
			<div class="comment-write d-flex border-top mt-4">
				<input type="text"
					class="form-control border-0 create-box mr-2 write-area"
					placeholder="댓글 달기" />
				<button type="button" class="comment-btn btn btn-light write-area"
					data-post-id="${postview.post.id}">등록</button>
			</div>
		</c:if>
	</div>
</c:forEach>
<script>
	$(document).ready(function() {

		//댓글 게시 버튼 눌렀을 때
		$('.comment-btn').on('click', function() {

			let postId = $(this).data('post-id');
			//alert(postId);
			// 지금 클릭 된 게시버튼 근처에 있는 형제의 input을 가져온다. : siblings
			let content = $(this).siblings('input').val();
			if (content == '') {
				alert("댓글을 입력하여주세요.");
			}

			//ajax
			$.ajax({
				type : 'post',
				url : '/comment/create',
				data : {
					"postId" : postId,
					"content" : content
				}

				,
				success : function(data) {
					if (data.code == 1) {
						alert("댓글을 등록하였습니다.");
						document.location.reload();
					} else {
						alert(data.errormessage);
					}
				},
				error : function(e) {
					alert("오류가 발생했습니다.")
				}
			});
		}) // comment-btn end

		$('.likeAdopt-btn').on('click', function(e) {
			e.preventDefault();
			let postId = $(this).data('post-id');
			let userId = $(this).data('user-id');
			let type = $(this).data('likeadopt-type');

			//alert(postId);
			//alert(userId);
			alert(type);

			if (userId == '') {
				alert("로그인 시 이용 가능합니다");
			}
			//ajax
			$.ajax({
				type : 'get'
				, url : '/likeAdopt/' + postId
				, data:{"userId":userId, "type":type}
				//success
				, success : function(data) {
					if (data.code == 1) {
						location.reload(true);
					} else {
						alert("오류발생")
					}
				}
				, error:function(e){
					alert(e + "에러가 발생했습니다.");
				}
			});

		}); // like-btn end
	}); // document end
</script>


