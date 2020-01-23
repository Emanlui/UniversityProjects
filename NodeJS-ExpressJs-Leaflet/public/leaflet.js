var waypoints = new Array();

				
			var r = JSON.parse(localStorage.getItem(localStorage.key(0)));
			var tmp = L.latLng(r[0].latStart, r[0].longStart);
            waypoints = waypoints.concat(tmp);

            var l = JSON.parse(localStorage.getItem(localStorage.key(1)));
            console.log(l);

			for (var i = 0; i < l.length; i++){
			    
                var tmp = L.latLng(l[i].lat_location, l[i].long_location);
                waypoints = waypoints.concat(tmp);
			}

			var tmp = L.latLng(r[0].latFinish, r[0].longFinish);
            waypoints = waypoints.concat(tmp);


            console.log(waypoints);
// Crea el mapa y setea la vista en esa coordenada en especifico.
            map = L.map('map').setView([10.0369,-84.1587], 14);
           
            // Este es el template del mapa.
            L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {
              maxZoom: 20,
              attribution: '&copy; Openstreetmap France | &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);

            // Permite crear la ruta y tambien las direcciones
            L.Routing.control({waypoints}).addTo(map);
