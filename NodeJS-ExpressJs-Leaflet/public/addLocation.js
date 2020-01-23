
	var mymap = L.map('map').setView([10.0369,-84.1587], 14);

	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox/streets-v11'
	}).addTo(mymap);

	var popup = L.popup();

	function onMapClick(e) {
		var location = e.latlng;
		document.getElementById('long_location').value = location.lng.toString();
		document.getElementById('lat_location').value = location.lat.toString();
		popup
			.setLatLng(e.latlng)
			.setContent("You clicked the map at " + e.latlng.toString())
			.openOn(mymap);
			
	}

	mymap.on('click', onMapClick);


	var l = JSON.parse(localStorage.getItem(localStorage.key(1)));

	for (var i = 0; i < l.length; i++){
        L.marker([l[i].lat_location, l[i].long_location]).addTo(mymap)
	}
