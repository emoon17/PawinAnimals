<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<div class="d-flex justify-content-center align-items-center ">


		<div class="create-box">
			<input id="WriteTitle" type="text" class="write-box write-area p-2"
				style="height: 70px; width: 800px;" placeholder="  제목을 입력하세요">
			<div class="line"></div>
			<textarea id="WriteArea" class="wirte-box write-area border-0 p-2"
				style="height: 500px; width: 800px;" placeholder="  내용을 입력하세요"></textarea>

			<%-- 이미지 업로드를 위한 버튼--%>
			<div class="d-flex justify-content-end">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>

					<form id="fileUploadForm" action="/imagePath/upload_image" method="post">
					<input type="file" id="file" name="file1" class="d-none"
						accept=".gif, .jpg, .png, .jpeg" multiple="multiple">


						<div class="d-flex justify-content-between align-items-center">
							<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
							<div id="fileName1" style="height: 10px;"
								class="copy-font text-center ml-2"></div>
							<input type="button" id="fileUpLoadBtn" value="사진 첨부"
								class="write-area btn btn-lg mr-3 ml-2">
						</div>
					</form>
				</div>
			</div>
			<div class="line mt-3"></div>
			<%-- select 박스 만들기 --%>
			<div class="mt-2 d-flex align-items-center">
				<div class="copy-font w-100">
					<span class="ml-2">상황: </span> <select id="status" name="status"
						class="content-foot-box ml-4 text-center">
						<option value="상황">--상황--</option>
						<option value="목격" selected>목격</option>
						<option value="실종">실종</option>
						<option value="찾음">찾음</option>
						<option value="입양가능">입양 가능</option>
						<option value="입양완료">입양 완료</option>
					</select>
				</div>
				<div class="w-100 copy-font">
					<span class="ml-2">동물 종: </span> <select id="animals"
						name="animals" class="content-foot-box ml-4 text-center">
						<option value="상황">--동물 종--</option>
						<option value="목격" selected>고양이</option>
						<option value="실종">강아지</option>
						<option value="찾음">기타</option>
					</select>
				</div>
				<div class="copy-font w-100">
					<span class="ml-2">지역 선택: </span> <select id="area" name="area"
						class="content-foot-box ml-4 text-center">
						<option value="상황">--지역 선택--</option>
						<option value="목격" selected>강원도</option>
						<option value="입양가능">광주광역시</option>
						<option value="실종">경기도</option>
						<option value="찾음">경상남도</option>
						<option value="입양가능">경상북도</option>
						<option value="입양완료">대전광역시</option>
						<option value="입양완료">부산광역시</option>
						<option value="입양가능">서울특별시</option>
						<option value="입양완료">세종특별자치시</option>
						<option value="입양완료">인천광역시</option>
						<option value="입양완료">울산광역시</option>
						<option value="입양완료">전라남도</option>
						<option value="입양완료">전라북도</option>
						<option value="입양가능">충청남도</option>
						<option value="입양가능">충청북도</option>
					</select>
				</div>

			</div>
			<div class="line mt-2"></div>

		</div>

		<!-- createBox -->


	</div>

	<div class="post-div d-flex justify-content-end ">
		<button id="postBtn"
			class="post-btn btn btn-lg write-area font-weight-bold ">작성
			완료</button>
	</div>

</div>

<script>
	$(document).ready(function() {
		// 파일선택 업로드 버튼 누르기 뒤에 있는 file 인풋 누르기
		$('#fileUpLoadBtn').on('click', function() {
			$('#file').click();
		});
		
		let textFileList = new Array(); //이미지 이름을 담아놓을 배열
		// 파일 유효성 확인 및 업로드 된 파일 이름 노출하기
		$('#file').on('change', function(e) {
			//alert("dd");
			let fileName = e.target.files[0].name; // 파일이름
			//fileName = fileName.replace(',', '');
			//let fileNameArr = Array.prototype.slice.call(fileName);
			
			//alert(fileName);

			//확장자 유효성 확인.
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != 'jpg' && ext != 'jepg' && ext != 'gif' && ext != 'png') {
				alert("파일 형식에 맞지 않습니다. \n가능 파일: jpg, jepg, gif, png ");
				// 인풋 파일 제거 , 파일 이름 비우기
				$('#file').val(''); //인풋파일 제거
				$('#fileName').text('');
				
			}
			// 유효성 검사 통과 시
			let file = $('#file').val();
			textFileList.push(fileName);    // 이미지 이름을 배열에 담는다.
			for (let i = 0; i < textFileList.length; i++){
				$('#fileName1').text(textFileList);// 배열에서 파일 이름을 꺼내서 보여준다.
					
			}
			if (textFileList.length > 3) {
				alert("이미지는 최대 3개까지 업로드 가능합니다.");
				$('#fileName1').text(''); // 세개 이상 추가 시 없애버림
				$('#file').val('');
				//alert(file);
				textFileList.length = 0;
				return;
			}	
			
		});
		
		let inputFileList = new Array();     // 이미지 파일을 담아놓을 배열 (업로드 버튼 누를 때 서버에 전송할 데이터)


		// 사진 버튼 눌렀을 때 업로드 수행
		$('#fileUpLoadBtn').on('click', function() {
		　console.log("inputFileList: " + inputFileList);
		
		　　let formData = new FormData($('#fileUploadForm')[0]);  // 폼 객체

		　　for (let i = 0; i < inputFileList.length; i++) {
		　　　　formData.append("files", inputFileList[i]);// 배열에서 이미지들을 꺼내 폼 객체에 담는다.
		　}
		 $.ajax({
		　　　　type:"post"
		　　　　, url:"/imagePath/upload_image"
		　　　　, data:formData
		　　　//　, enctype:"multipart/form-data"  // 업로드를 위한 필수 파라미터
		　　　　, processData: false   // 업로드를 위한 필수 파라미터
		　　　　, contentType: false   // 업로드를 위한 필수 파라미터
		　　　　
		　　　　//response
			 , success:function(data) {
				if (data.code == 1) {
					// 성공
					alert("사진 등록");
				}	
			}
			, error:function(e){
				alert("error" + e);
			} 
		　　});
				
		});
		
		// 작성완료 눌렀을 때
		$('#postBtn').on('click', function(){
			
		   let WriteTitle = $('#WriteTitle').val();
		   let WriteArea =  $('#WriteArea').val();
		   let file = $('#file').val();
		   let status = $("select[name=status] option:selected").text();
		   let animals = $("select[name=animals] option:selected").text();
		   let area = $("select[name=area] option:selected").text();
		 
		  
		   if (WriteTitle == ''){
				alert("제목을 입력하여주세요.");
				return;
		   }
		   if (WriteArea == ''){
				alert("내용을 입력하여주세요.");
				return;
		   }
		   if (file == ''){
				alert("사진을 선택하여주세요");
				return;
		   }
		   
		   if (status == ''){
				alert("상황을 선택하여주세요.");
				return;
		   }
		   if (animals == ''){
				alert("상황을 선택하여주세요.");
				return;
		   }
		   if (area == ''){
				alert("상황을 선택하여주세요.");
				return;
	  	   }
		   //post
		   
		　 　 
		});
		
	});
</script>