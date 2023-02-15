<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- <h1>${postView[0].post.id}</h1> --%>
<c:forEach items='${postView}' var="postview">
	<div class="container list-box">
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
					<a href="#" class="like-btn" data-user-id="${userId}"
						data-post-id="${card.post.id}"> <img
						src="/static/image/heart-icon.png" width="30" height="30"
						alt="filled heart">
					</a>
					<%-- 	</c:if> --%>
					<%-- 좋아요가 해제되어 있을 때 --%>
					<c:if test="${postviews.filledLike eq false}">
						<a href="#" class="like-btn" data-user-id="${userId}"
							data-post-id="${postview.post.id}"> <img
							src="/static/image/like-icon.png" width="30" height="30"
							alt="empty heart">
						</a>
					</c:if>
	
					<span class="content-area ml-3 mr-5"> 좋아요
						${postviews.likeCount}개 </span>
	
					<%-- 입양 희망자가 없을 때 --%>
					<a href="#" class="adopt-btn" data-user-id="${userId}"
						data-post-id="${postview.post.id}"> <img
						src="/static/image/adopt_empty.png" width="45" height="45"
						alt="filled heart">
					</a> <span class="content-area ml-3 mr-5"> 입양희망자 ${postviews.Count}명 </span>
						
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
		<div class="card-comment-list m-2 d-flex justify-content-between">

			<%-- 댓글내용 --%>
			<%-- <c:forEach items="${card.commentList}" var="commentView"> --%>
			<div class="card-comment ">
				<span class="font-weight-bold write-area "> 댓글쓴이
					${commentView.user.loginId}:</span> <span class="write-area ml-3">
					댓글 내용${commentView.comment.content}</span>

			</div>
			<%-- 댓글 삭제 버튼 --%>
			<a href="#" class="commentDelBtn mt-1"><img
				src="/static/image/delete.png" width="30px" height="33x"></a>
			<%-- 	</c:forEach> --%>
		</div>
	</div>
</c:forEach>


