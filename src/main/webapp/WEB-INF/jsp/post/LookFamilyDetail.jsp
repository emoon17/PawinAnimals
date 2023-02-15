<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- <h1>${postView[0].post.id}</h1> --%>
<div class="container list-box">
		<%-- 제목 --%>
		<div class="content-subject mb-3">
			${postView[0].post.title}
		</div>

		<%--사진 이미지 --%>
		<div class="card-img">
		<!-- 시간 남으면 슬라이스로 볼 수 있게하기 -->
			<img src="${postView[0].imagePathList[0].imagePaths.imagePath}" class="w-100 mb-4" alt="본문 이미지" >
			<img src="${postView[0].imagePathList[1].imagePaths.imagePath}" class="w-100 mb-4" alt="본문 이미지">
			<img src="${postView[0].imagePathList[2].imagePaths.imagePath}" class="w-100 mb-4" alt="본문 이미지" >
		</div>
		<%-- 글 내용 --%>
		<div class="content-area">
			<span class="m-2">${postView[0].post.content}</span>
		</div>
		<%-- 로그인 아이디 --%>
		<div class="mt-2">
			<span class="font-weight-bold m-2 content-area">${postView[0].user.loginId}</span>
		</div>
		<%-- 좋아요 --%>
		<div class="like-adopt d-flex align-items-center mt-3">
			<%-- 좋아요가 되어있을 때 --%>
		<%-- 	<c:if test="${card.filledLike eq true}"> --%>
				<a href="#" class="like-btn" data-user-id="${userId}"
					data-post-id="${card.post.id}"> <img
					src="/static/image/heart-icon.png"
					width="30" height="30" alt="filled heart">
				</a>
		<%-- 	</c:if> --%>
			<%-- 좋아요가 해제되어 있을 때 --%>
			<c:if test="${card.filledLike eq false}">
				<a href="#" class="like-btn" data-user-id="${userId}"
					data-post-id="${card.post.id}"> <img
					src="/static/image/like-icon.png"
					width="18" height="18" alt="empty heart">
				</a>
			</c:if>
			<span class="content-area ml-3"> 좋아요 ${card.likeCount}개 </span>
		</div>

		<%-- 글 내용 --%>
		<div class="card-comment-desc border-bottom">
			<div class="ml-3 mb-1 font-weight-bold">댓글</div>
		</div>

		<%-- 댓글 목록 --%>
		<div class="card-comment-list m-2">

			<%-- 댓글내용 --%>
			<%-- <c:forEach items="${card.commentList}" var="commentView"> --%>
			<div class="card-comment m-1 ">
				<span class="font-weight-bold ">${commentView.user.loginId}:
				</span> <span> ${commentView.comment.content}</span>

				<%-- 댓글 삭제 버튼 --%>
				<a href="#" class="commentDelBtn"><img
					src="https://www.iconninja.com/files/603/22/506/x-icon.png"
					width="10px" height="10px"></a>
			</div>
			<%-- 	</c:forEach> --%>
		</div>
	</div>
	<%-- 모달로 글 수정, 삭제하기 --%>
	<c:if test="${userId eq card.user.id}">
		<a href="#" class="more-btn" data-toggle="modal" data-target="#modal"
			data-post-id="${card.post.id}"> 글 수정, 삭제하기 </a>
	</c:if>

