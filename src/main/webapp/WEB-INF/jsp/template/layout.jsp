<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pawin Animals</title>
<!-- jquery : bootstrap, datepicker -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  

        <!-- bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
        <!-- css -->
        <link rel="stylesheet" type="text/css" href="/static/css/pawinStyle.css">
        
        <!-- 폰트 -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
        
</head>
<body>
	<div id="wrap">
		<header class="intro-bg">
			<div class=" d-flex align-items-center pt-3">
				<div class="display-4 font-weight-bold pl-4">
					<a href="/main/main_view">Pawin Animals</a>
				</div>
				<div class="login-menu-icon d-flex align-items-center justify-content-end pr-5">
					<ui class="nav font-weight-bold">
						<li><a href="#">로그인</a></li>
						<li><a href="#">회원가입</a></li>
						<li><a href="#">메뉴</a></li>
					</ui>
				</div>
			</div>
			<div class=" d-flex align-items-center justify-content-center ">
				<img src="/static/image/dog.jpg" width="200" height="200">
				<img src="/static/image/cat1.jpg" width="200" height="200">
			</div>
		</header>
		<section class="contents d-flex">
		
			<div class="left-content col-6 d-flex align-items-center justify-content-center">
				<div class="content-box text-center">
					<div class="content-subject font-weight-bold mt-4">아이들의 꽃길을 보러오세요!</div>
					<div class="nav-box d-flex align-items-center justify-content-center mb-5">
						<ui class="nav">
							<li class="content-font"><a href="#">${status.count}. ${post.title} 제목 10개만</a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 한둘셋넷다여일여아열한둘셋넷다</a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
						</ui>
					</div>	
				</div>
			</div>
			
			<div class="right-content col-6 d-flex align-items-center justify-content-center">
				<div class="content-box text-center">
					<div class="content-subject font-weight-bold mt-4">반려용품 무료 나눔합니다!</div>
					<div class="nav-box d-flex align-items-center justify-content-center mb-5">
						<ui class="nav">
							<li class="content-font"><a href="#">${status.count}. ${post.title} 제목 10개만</a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 한둘셋넷다여일여아열한둘셋넷다</a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
							<li class="content-font"><a href="#">${status.count}. ${post.title} 반복문 제목 </a></li>
						</ui>
					</div>	
				</div>
			</div>
		
		</section>
		<footer class="d-flex align-items-end justify-content-center">
			<div class="copy-font mb-5">Copyright © Pawin Animals 2023</div>
		</footer>
	</div>
</body>
</html>