<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
 <head>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta charset="utf-8">
  <style>
   html, body {
    height: 100%;
    margin: 0;
    padding: 0;
   }
   #map {
    height: 100%;
   }
     </style>
      <script>
    var map;
    var request = new Request();
    var lat = request.getParameter("lat");
    var lnt = request.getParameter("lnt");
    function initMap() {
    var loc = new google.maps.LatLng(lat, lnt);
    var search = request.getParameter("search");
     
     map = new google.maps.Map(document.getElementById('map'), {
       center: loc,
       zoom: 17,
       mapTypeId: google.maps.MapTypeId.ROADMAP
     });
    
     
     var service = new google.maps.places.PlacesService(map);
     service.nearbySearch({
       location: loc,
       radius: 1000,
       name: [search]
     }, processResults);
   
    }
    
    function processResults(results, status, pagination) {
     if (status != google.maps.places.PlacesServiceStatus.OK) {
      return;
     } 
     else {
      createMarkers(results);
      
      if (pagination.hasNextPage) {
       pagination.nextPage();
      }
     }
    }
   
    function createMarkers(places) {
     var bounds = new google.maps.LatLngBounds();
     var placesList = document.getElementById('places');
    
     for (var i = 0, place; place = places[i]; i++) {
      var marker = new google.maps.Marker({
       map: map,
       title: place.name,
       position: place.geometry.location
      });
      markerListener(marker);
      bounds.extend(place.geometry.location);
     }
     
     map.fitBounds(bounds);
    }
    
    function markerListener(localmarker){
     google.maps.event.addListener(localmarker, 'click', function(){
      var content = localmarker.title;
      var infowindow = new google.maps.InfoWindow({content:content});
      infowindow.open(map,localmarker);
     });
    }
    function Request(){
    	 var requestParam ="";
    	 
    	 //getParameter ���
    	  this.getParameter = function(param){
    	  //���� �ּҸ� decoding
    	  var url = unescape(location.href); 
    	  //�Ķ���͸� �ڸ���, �ٽ� &�׺��ڸ� �߶� �迭�� �ִ´�. 
    	   var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&"); 
    	 
    	   for(var i = 0 ; i < paramArr.length ; i++){
    	     var temp = paramArr[i].split("="); //�Ķ���� �������� ����
    	 
    	     if(temp[0].toUpperCase() == param.toUpperCase()){
    	       // ������� ��ġ�� ��� ������ ����
    	       requestParam = paramArr[i].split("=")[1]; 
    	       break;
    	     }
    	   }
    	   return requestParam;
    	 }
    	}
   </script>
    <title>����</title>
 </head>
 <body>
<div id=map></div>
 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDVKgGnEEWBIXqs-5soLMff5isECobjpUc&signed_in=true&libraries=places&callback=initMap" async defer></script>
 </body>
</html> 
