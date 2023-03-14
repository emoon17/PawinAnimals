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
		<div class="contents-parent-box d-flex flex-wrap ">

			<!--  var="imagePathView" items="${postView.imagePathList}"  imagePathView.imagePath.imagePath-->
			<c:forEach var='posts' items='${postList}' varStatus="status">
				<c:forEach items='${posts.imagePathList}' var="image">
					<article id="postBox" class="post-box"
					data-post-id="${posts.post.id}" data-toggle="modal"
					data-target="#postModal" data-post-content="${posts.post.content}"
					 data-post-image="${image.imagePaths.imagePath}">
					 
				
					<a href="#"> <img
						src="${posts.imagePathList[0].imagePaths.imagePath}" alt="이미지"
						width="300" height="300" class="list-box"
						data-post-id="${posts.post.id}">
						<%-- <div>${posts.post.id}</div> --%>

						<div class="copy-font ml-3 font-weight-bold">
							제목 : <span class="ml-3"> ${posts.post.title}</span>
						</div>
						<div class="copy-font ml-3 font-weight-bold">
							상황 : <span class="ml-3" id="status" data-post-status="${posts.post.status}">${posts.post.status}</span>
						</div>
						<div class="copy-font ml-3 font-weight-bold">
							동물 종: <span class="ml-3">${posts.post.animals}</span>
						</div>
					</a>
				</article>
					<!-- post Modal -->
					<div class="modal fade" id="postModal">
						<div class="modal-dialog modal-lg modal-dialog-centered">
							<div class="modal-content">
								
									<div class="py-3">
										<img src="${image.imagePaths.imagePath}" class="w-100" alt="본문 이미지" id="imageBox">
									</div>
									<div id="contentBox" class="py-3 border-bottom content-area ml-4">
										${posts.post.content}</div>
							
								<c:if test="${userId eq posts.post.userId}">
									<div class="py-3 border-bottom text-center">
										<a href="#" id="deletePostBtn"
											class="content-area font-weight-bold">삭제하기</a>
									</div>
									<div class="py-3 border-bottom text-center">
										<a href="/post/review_update_view?postId=${posts.post.id}" id="updatePostBtn" class="content-area font-weight-bold">수정하기</a>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
	</div>
</div>


			

	


<script>
	$(document).ready(function() {

		$('#serachBtn').on('click', function() {
			let searchTitle = $('#title').val();
			let searchAnimals = $("#animals option:selected").val();
			let searchStatus = $('#status').data('post-status');
			//alert(searchStatus);

			// ajax
			$.ajax({
				//request
				type : 'get',
				url : '/post/adopt_search_list_view',
				data : {
					"searchTitle" : searchTitle,
					"searchAnimals" : searchAnimals,
					"searchStatus" : searchStatus
				}

				//response
				,
				success : function(data) {
					console.log(data);

					$('#contentsBox').html(data);

				},
				error : function(e) {
					alert(e + "오류가 발생했습니다. 다시 시도해주세요.");
				}

			});
		});
		$('.post-box').on('click', function(e) {
			e.preventDefault();

			let postId = $(this).data('post-id'); //getting (태그에 있는 걸 얻어오는것)
			//alert(postId);
			let image = $(this).data('post-image');
			//alert(image);
			$('#postModal').data('post-id', postId);
			$('#postModal #contentBox').text($(this).data('post-content'));
			//$('#postModal  #imageBox').html($(this).data('post-image'));
			//$('#postModal #imageBox img').attr({ src: image });
			$('#postModal #imageBox').attr("src", image);
		});

		$('#postModal #deletePostBtn').on('click', function(e) {
			e.preventDefault();
			
			let postId = $('#postModal').data('post-id');
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