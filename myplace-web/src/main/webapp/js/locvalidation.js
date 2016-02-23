function login_retrieve_location()
    { 
       if (navigator.geolocation)
       {
          navigator.geolocation.getCurrentPosition(login_geolocation_properties); 
       }
      
    }

	 function login_geolocation_properties(position)
    {
       document.login.latitude.value=position.coords.latitude;
       document.login.longitude.value=position.coords.longitude;
    }

 function reg_retrieve_location()
    { 
       if (navigator.geolocation)
       {
          navigator.geolocation.getCurrentPosition(reg_geolocation_properties); 
       }
      
    }

	 function reg_geolocation_properties(position)
    {
       document.register.latitude.value=position.coords.latitude;
       document.register.longitude.value=position.coords.longitude;
    }

 function retrieve_search_location()
    {
       if (navigator.geolocation)
       {
          navigator.geolocation.getCurrentPosition(search_geolocation_properties); 
       }else {
           var content = 'Error: Your browser does not support this functionality.';
			alert(content);
       }
      
    }

	 function search_geolocation_properties(position)
    {
       document.search.bLat.value=position.coords.latitude;
       document.search.bLong.value=position.coords.longitude;
    }
