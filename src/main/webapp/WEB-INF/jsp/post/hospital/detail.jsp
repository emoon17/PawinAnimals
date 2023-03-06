<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

	
	<c:forEach items="${transCoordList}" var="transCoord">
		<div data-coordinate-x="${transCoord.x}">${transCoord.x}</div>
		<div data-coordinate-y="${transCoord.y}">${transCoord.y}</div>
	</c:forEach>
	
	<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=845ed061db5314117764f4349c03d14c"></script>
	
	<script>
	let coordinateX = $(this).data('coordinate-x');
	let coordinateY = $(this).data('coordinate-y');
	
		let container = document.getElementById('map');
			
		let options = {
			center: new kakao.maps.LatLng("126.839645", "37.560149"),
			level: 3
		};

		let map = new kakao.maps.Map(container, options);
	</script>