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
				pattern="yyyy-MM-dd a hh:mm:ss" />
		</div>

		<%-- 제목 --%>
		<div id="postTitle" class="content-subject mb-2 ml-3">${postview.post.title}</div>

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
				<c:if test="${postview.filedLike eq false}">
					<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
						data-post-id="${postview.post.id}" data-likeadopt-type="like"
						data-user-loginId="${loginId}"> <img
						src="/static/image/heart-icon.png" width="30" height="30"
						alt="filled heart">
					</a>
				</c:if>

				<%-- 좋아요가 해제되어 있을 때 --%>
				<c:if test="${postview.filedLike eq true}">
					<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
						data-post-id="${postview.post.id}" data-likeadopt-type="like"
						data-user-loginId="${loginId}"> <img
						src="/static/image/like-icon.png" width="30" height="30"
						alt="empty heart">
					</a>
				</c:if>

				<span class="content-area ml-3 mr-5"> <a href="#"
					data-toggle="modal" data-target="#likeModal">좋아요
						${postview.likeCount}개 </a></span>

				<%-- 입양 희망자가 해제 되어 있을 때 --%>
				<c:if test="${postview.filedAdopt eq false}">
					<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
						data-post-id="${postview.post.id}" data-likeadopt-type="adopt"
						data-user-loginId="${loginId}"> <img
						src="/static/image/adopt_empty.png" width="45" height="45"
						alt="empty adopt">
					</a>
				</c:if>

				<%-- 입양 희망자가 눌려있을 때 --%>
				<c:if test="${postview.filedAdopt eq true}">
					<a href="#" class="likeAdopt-btn" data-user-id="${userId}"
						data-post-id="${postview.post.id}" data-likeadopt-type="adopt"
						data-user-loginId="${loginId}"> <img
						src="/static/image/adopt.jpg" width="45" height="45" alt="adopt">
					</a>
				</c:if>

				<span class="content-area ml-3 mr-5"><a href="#"
					data-toggle="modal" data-target="#adoptModal"> 입양희망자
						${postview.adoptCount}명 </a></span>
			</div>

		</div>




		<div class="line mb-3"></div>

		<%-- 댓글 목록 --%>
		<div class="card-comment-list ">

			<%-- 댓글내용 --%>
			<c:forEach items="${postview.commentViewList}" var="commentView">
				<div class="card-comment  d-flex justify-content-between m-3">
					<div class="font-weight-bold write-area ">
						${commentView.user.loginId}<span class="ml-3">:</span> <span
							id="content" class="write-area ml-3">${commentView.comment.content}</span>
					</div>

					<%-- <%-- 모달로 댓글 삭제하기 --%>
					<c:if test="${userId eq postview.user.id}">
						<img src="/static/image/delete.png" id="deleteCommentBtn"
							class="commentDelBtn mt-1 more-btn write-area"
							data-toggle="modal" data-target="#modal"
							data-post-id="${postview.post.id}"
							data-comment-content="${commentView.comment.content}"
							width="30px" height="33x">
					</c:if>
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

		<div class="d-flex justify-content-end mt-5">
			<c:if test="${userId eq postview.user.id}">
				<a href="/post/update_view?postId=${postview.post.id}"
					class="write-area mr-2">수정 </a>
				<span class="mr-2 mt-1">|</span>
				<a href="#"><span id="deletePostBtn" class="write-area" data-post-id="${postview.post.id}">삭제</span>
				</a>
			</c:if>
		</div>
	</div>


	<!--댓글 Modal -->
	<div class="modal fade" id="modal">
		<%--...을 눌렀을 때 post-data-id를 모달에 심어놓을거다. --%>
		<%-- modal sm: 작은 모달 창  --%>
		<%-- modal centered: 모달 창 수직으로 가운데 정렬 --%>
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content text-center">
				<%-- 삭제하기 --%>
				<div class="py-3 border-bottom">
					<a href="#" id="deleteCommentBtn"
						class="content-area font-weight-bold">삭제하기</a>
				</div>
				<%-- data-dismiss="modal"추가하면 모달 창 닫힘 --%>
				<div class="py-3 " data-dismiss="modal">
					<a href="#" class="content-area font-weight-bold">취소하기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- like Modal -->
	<div class="modal fade" id="likeModal">
		<%--...을 눌렀을 때 post-data-id를 모달에 심어놓을거다. --%>
		<%-- modal sm: 작은 모달 창  --%>
		<%-- modal centered: 모달 창 수직으로 가운데 정렬 --%>
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content text-center">
				<%-- 누른 사람 아이디 보여지기 --%>
				<c:forEach items="${postview.likeViewList}" var="likeView">
					<div class="py-3 border-bottom">
						<a id="loginId" class="content-area font-weight-bold">${likeView.user.loginId}</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>


	<!-- adopt Modal -->
	<div class="modal fade" id="adoptModal">
		<%--...을 눌렀을 때 post-data-id를 모달에 심어놓을거다. --%>
		<%-- modal sm: 작은 모달 창  --%>
		<%-- modal centered: 모달 창 수직으로 가운데 정렬 --%>
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content text-center">
				<%-- 누른 사람 아이디 보여지기 --%>
				<c:forEach items="${postview.adoptViewList}" var="adoptView">
					<div class="py-3 border-bottom">
						<a id="loginId" class="content-area font-weight-bold">${adoptView.user.loginId}</a>
					</div>
				</c:forEach>
			</div>
		</div>
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
			let loginId = $(this).data('user-loginId');

			//alert(postId);
			//alert(userId);
			//alert(type);

			if (userId == '') {
				alert("로그인 시 이용 가능합니다");
			}
			//ajax
			$.ajax({
				type : 'get',
				url : '/likeAdopt/' + postId,
				data : {
					"type" : type,
				}
				//success
				,
				success : function(data) {
					if (data.code == 1) {
						location.reload(true);

					} else {
						alert(data.errorMessage);
					}
				},
				error : function(e) {
					alert(e + "에러가 발생했습니다.");
				}
			});

		}); // like-btn end

		// 댓글 삭제를 위한 더보기 버튼(...) 클릭
		$('.more-btn').on('click', function(e) {
			e.preventDefault();

			let postId = $(this).data('post-id'); //getting (태그에 있는 걸 얻어오는것)
			//alert(postId);
			let content = $(this).data('comment-content');
			//alert(content);	
			$('#modal').data('post-id', postId); // setting(모달 태그에 data-post-id를 심어놓는것)
			$('#modal').data('comment-content', content);

		}); // 모달에 데이터 심어 놓기

		// 모달의 
		$('#modal #deleteCommentBtn').on('click', function(e) {
			e.preventDefault();

			let postId = $('#modal').data('post-id');
			//alert(postId);
			let content = $('#modal').data('comment-content');
			//alert(content);

			//ajax
			$.ajax({
				//request
				type : "delete",
				url : "/comment/delete",
				data : {
					"postId" : postId,
					"content" : content
				}

				//response
				,
				success : function(data) {
					if (data.code == 1) {
						location.reload(true);
						alert("댓글이 삭제 되었습니다.");
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(jqXHR, textStatus, ErrorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}

			});

		}); // comment delete
		
		// 글 삭제 버튼 눌렀을 때
		$('#deletePostBtn').on('click', function(){
			//alert("d");
			let postId = $(this).data('post-id');
			//alert(postId);
			
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
						location.href="/post/look_for_family_list_view";
					} else{
						alert(data.errorMessage);
					}
				}
				, error:function(e){
					alert("오류가 발생했습니다.");
				}
			});
		}); // post delete
	}); // document end
</script>


