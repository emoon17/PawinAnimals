<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty userId}">
	<div class="container">

		<div class="d-flex justify-content-center align-items-center ">

			<c:forEach items='${postView}' var="postview">
				<div class="create-box">
					<input id="writeTitle" type="text" class="write-box write-area p-2"
						style="height: 70px; width: 800px;" name="title"
						value="${postview.post.title}" placeholder="  제목을 입력하세요">
					<div class="line"></div>
					<textarea id="writeArea" class="wirte-box write-area border-0 p-2"
						style="height: 500px; width: 800px;" name="content"
						placeholder="  내용을 입력하세요">${postview.post.content}</textarea>

					<%-- 이미지 업로드를 위한 버튼--%>
					<div class="d-flex justify-content-end">
						<div class="file-upload d-flex">

							<input type="file" id="file" name="file" class="d-none"
								accept=".gif, .jpg, .png, .jpeg" multiple="multiple">

							<div class="d-flex justify-content-between align-items-center">
								<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
								<div id="fileName1" style="height: 10px;"
									class="copy-font text-center ml-2"></div>

								<input type="button" id="fileUpLoadBtn" value="사진 첨부"
									name="images" class="write-area btn btn-lg mr-3 ml-2">

							</div>

						</div>
					</div>
					<div class="line mt-3"></div>
					<%-- select 박스 만들기 --%>
					<div class="mt-2 d-flex align-items-center justify-content-end mr-3">
						<div class="copy-font">
							<span class="ml-2">상황: </span> <select id="status" name="status"
								class="content-foot-box ml-4 text-center">
								<option value="입양완료">입양완료</option>
							</select>
						</div>
						<div class="copy-font">
							<span class="ml-2">동물 종: </span> <select id="animals"
								name="animals" class="content-foot-box ml-4 text-center">
								<option value="고양이">고양이</option>
								<option value="강아지">강아지</option>
								<option value="기타">기타</option>
							</select>
						</div>

					</div>
					<div class="line mt-2"></div>

				</div><!-- createBox -->

				
			<!-- createBox -->
		</div>

		<div class="post-div d-flex justify-content-end ">
			<form id="fileUploadForm" action="/post/post_create" method="post"
				enctype="multipart/form-data">
				<button type="button" id="updatePostBtn"
					class="post-btn btn btn-lg write-area font-weight-bold"
					data-post-id="${postview.post.id}">수정 완료</button>
			</form>
		</div>

	</div>
	</c:forEach>
</c:if>

<script>
$(document).ready(function() {
	// 파일 버튼을 눌렀을 때
	$('#fileUpLoadBtn').on('click', function(e) {
		$('#file').click();
	});
	
	let textFileList = new Array(); //이미지 이름을 담아놓을 배열
	let inputFileList = new Array();     // 이미지 파일을 담아놓을 배열
	
	// 파일 유효성 확인 및 업로드 된 파일 이름 노출하기
	$('#file').on('change', function(e) {
		
		let fileName = e.target.files[0].name; // 파일이름
		let files = e.target.files; // 선택된 파일
	    let filesArr = Array.prototype.slice.call(files);    //아규먼트를 배열에 담아놓는다.
	    
	    // 파일 추가
	    filesArr.forEach(function(f) { 
	    　　　　inputFileList.push(f);    // 이미지 파일을 배열에 담는다.
	    　　});
	    
		//확장자 유효성 확인.
		let ext = fileName.split(".").pop().toLowerCase();
		if (ext != 'jpg' && ext != 'jepg' && ext != 'gif' && ext != 'png') {
			alert("파일 형식에 맞지 않습니다. \n가능 파일: jpg, jepg, gif, png ");
			// 인풋 파일 제거 , 파일 이름 비우기
			files.clear(); //인풋파일 제거
			$('#fileName1').text('');
			
		}
	
		// 유효성 검사 통과 시 파일 저장
		// 이미지 이름을 배열에 담는다.
		 for(let i= 0; i<filesArr.length; i++){
			 textFileList.push(filesArr[i].name);  
         }
		
		 for (let i = 0; i < textFileList.length; i++){
			$('#fileName1').text(textFileList);// 배열에서 파일 이름을 꺼내서 보여준다.
		 } 
		 
		 if (textFileList.length > 1) {
				alert("이미지는 최대 1개까지 업로드 가능합니다.");
				$('#fileName1').text(''); // 한 개 이상 추가 시 없애버림
				files = null;
				textFileList.length = 0;
				inputFileList.length = 0;
				$('#file').val(''); //인풋파일 제거
				return;
		}	
			
	});
	// 제목 글 넣기
	$('#writeTitle').keyup(function() {
		
		let writeTitle = $('#writeTitle').val();
		if (writeTitle.length > 15) {
			alert("제목은 15글자를 초과할 수 없습니다.");
			$('#writeTitle').val('');
			
		} 
		
	});
	
	// 수정 버튼을 눌렀을 때
	$('#updatePostBtn').on('click', function() {
		 
		let postId = $(this).data('post-id');
		let writeTitle = $('#writeTitle').val();
		let writeArea = $('#writeArea').val();
		let file = $('#file').val();
	 	let status = $("#status option:selected").val();
		let animals = $("#animals option:selected").val();
		//alert(postId);
		
		let formData = new FormData($('#fileUploadForm')[0]);  // 폼 객체
		   
		for (let i = 0; i < inputFileList.length; i++) {
		　　　　formData.append("files", inputFileList[i]);// 배열에서 이미지들을 꺼내 폼 객체에 담는다.
			 console.log(inputFileList[i])
		}
		
		formData.append("postId", postId);		 
		formData.append("title", writeTitle);		 
		formData.append("content", writeArea);		 
		formData.append("status", status);		 
		formData.append("animals", animals);		 
		  
		if (writeTitle == ''){
			alert("제목을 입력하여주세요.");
			return;
		}
		  
		if (writeArea == ''){
			alert("내용을 입력하여주세요.");
			return;
		}
		
		if (file == ''){
			alert("사진을 선택하여주세요");
			return;
		}
		
		 //ajax
		 $.ajax({
			//request
			type:'put'
			, url:'/post/post_update'
		    , data: formData
		    , enctype:"multipart/form-data"
	 		, processData:false
	 		, contentType:false
	 		
	 		// response
	 		, success:function(data){
	 			if (data.code == 1) {
	 				location.href="/post/adopt_review_list_view";
	 			}
	 		}
			, error:function(e){
				alert("오류가 발생했습니다.");
			}
	 			  
		});  
		 
	});
});
</script>