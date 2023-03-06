<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="container list-box">
	<c:forEach items="${nameAddressList}" var="nameAddress">
		<div >상호명 : ${nameAddress.name}</div>
		<div class="d-flex justify-content-between">
		<div >주소 : ${nameAddress.address}</div>
		<button id="mapBtn" class="btn" data-hospital-name="${nameAddress.name}" data-hospital-address="${nameAddress.address}">지도를 보고 싶으시다면 눌러주세요</button>
		</div>
	</c:forEach>
	<div id="map" style="width:500px;height:400px;"></div>
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=845ed061db5314117764f4349c03d14c&libraries=services"></script>
	
	<script>
	$(document).ready(function() {
		$('#mapBtn').on('click', function(e) {
			
		e.preventDefault();
		let name = $(this).data('hospital-name');
		let address = $(this).data('hospital-address');
		alert(name);
		alert(address);
	
		let mapContainer = document.getElementById('map');
			
		let mapOption  = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};

		
		// 지도를 생성합니다    
		let map = new kakao.maps.Map(mapContainer, mapOption); 

		// 주소-좌표 변환 객체를 생성합니다
		let geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		    	 let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        let marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        let infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + name + '</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
			}); 
		});
	});
	</script>