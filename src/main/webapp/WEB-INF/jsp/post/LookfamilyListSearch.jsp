<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 글 목록 -->
<div id="contentsBox" class="contents-box ">
	<div class="contents-parent-box d-flex flex-wrap justify-content-between">
		<c:forEach var='keyword' items='${keywordList}'>
			<article id="postBox" class="post-box">
			<a href="/post/look_for_family_detail_view?postId=${keyword.post.id}">
				<img src="${keyword.imagePathView[0].imagePaths.imagePath}"
					alt="이미지" width="300" height="300" class="list-box">
				<div>${keyword.post.id}</div>
				<div class="copy-font ml-3 font-weight-bold">
					제목 : <span class="ml-3"> ${keyword.post.title}</span>
				</div>
				<div class="copy-font ml-3 font-weight-bold">
					상황 : <span class="ml-3">${keyword.post.status}</span>
				</div>
				<div class="copy-font ml-3 font-weight-bold">
					동물 종: <span class="ml-3">${keyword.post.animals}</span>
				</div>
				<div class="copy-font ml-3 font-weight-bold">
					지역 : <span class="ml-3">${keyword.post.area}</span>
				</div>
				</a>
			</article>
		 </c:forEach> 
	</div>
</div>
</div>
