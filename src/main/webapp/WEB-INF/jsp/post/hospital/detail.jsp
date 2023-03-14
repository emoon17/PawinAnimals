<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="container list-box d-flex justify-content-center">
	<div >
		<div class="menu_login_info mt-2 font-weight-bold">위치를 확인 해 보세요!</div>
		<div id="map" style="width:500px;height:400px;" class="mt-3"></div>
		<c:forEach items="${transCoordList}" var="transCoord">
			<div id="name" class="mt-5 mb-2 menu_login_info" data-hospital-xco="${transCoord.x}" 
			data-hospital-yco="${transCoord.y}" data-trans-name="${transCoord.name}"> 상호 명 : ${transCoord.name}</div>
			<div class="menu_login_info mb-4">주소 : ${transCoord.address}</div>
		</c:forEach>
	</div>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=845ed061db5314117764f4349c03d14c"></script>

	<script>
	$(document).ready(function() {
		
	let coordinateX = $('#name').data('hospital-xco');
	let coordinateY = $('#name').data('hospital-yco');
	let name = $('#name').data('trans-name');
	
	//alert(coordinateX);
		let container = document.getElementById('map');
			
		let options = {
			center: new kakao.maps.LatLng(coordinateY, coordinateX),
			level: 3
		};
		let map = new kakao.maps.Map(container, options);
		
		// 마커가 표시 될 위치
		let markerPosition  = new kakao.maps.LatLng(coordinateY, coordinateX);
		
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		
        // 인포윈도우로 장소에 대한 설명을 표시합니다
        let infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + name + '</div>'
        });
        infowindow.open(map, marker);
       
   	  
		
		
	});
	</script>