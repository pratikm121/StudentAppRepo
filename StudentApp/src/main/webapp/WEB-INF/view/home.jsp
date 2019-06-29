<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
.bgimg {
  margin-top : 20px;
  background-image: url("https://cdn.studentloanhero.com/wp-content/uploads/private-student-loan-forgiveness-640x300.jpg"); /* The image used */
  background-color: #cccccc; /* Used if the image is unavailable */
  height: 500px; /* You must set a specified height */
  background-position: center; /* Center the image */
  background-repeat: no-repeat; /* Do not repeat the image */
  background-size: cover; /* Resize the background image to cover the entire container */
    
}
</style>

<title>CGI</title>
</head>
<body>
	<div id="container1" class="container bgimg" >
		  <div class="form-row text-center">
		  	<h1 class="text-center">${welcome}</h1>
		  </div>
	
		  <div class="form-row">
		    <div class="form-group col-md-3">
		      <label for="firstName">First Name</label>
		      <input type="text" class="form-control" id="firstName" max="45" placeholder="First Name">
		    </div>
		    <div class="form-group col-md-3">
		      <label for="lastName">Last Name</label>
		      <input type="text" class="form-control" id="lastName" max="45" placeholder="Last Name">
		    </div>
		  </div>
		  <div class="form-row">
		  	<div class="form-group col-md-6">
			  <label for="address">Address</label>
			  <input type="text" class="form-control" id="address" max="250" placeholder="1234 Main St">
		  	</div>
		  </div>		  
		  <div class="form-row">
		    <div class="form-group col-md-3">
		      <label for="city">City</label>
		      <select id="city" class="form-control">
		        <option selected>Choose...</option>
		        <option>Assen</option>
		        <option>Coevorden</option>
		        <option>Almere</option>
		        <option>Emmeloord</option>
		        <option>Leeuwarden</option>
		        <option>Sloten</option>
		        <option>Gendt</option>
		        <option>Staverden</option>
		        <option>Groningen</option>
		        <option>Winschoten</option>
		        <option>Roermond</option>
		        <option>Maastricht</option>
		        <option>Breda</option>
		        <option>Tilburg</option>
		        <option>Amsterdam</option>
		        <option>Volendam</option>
		        <option>Zwolle</option>
		        <option>Rotterdam</option>
		        <option>The Hague</option>
		        <option>Utrecht</option>
		        <option>Nieuwegein</option>
		        <option>Goes</option>
		      </select>
		    </div>
		    <div class="form-group col-md-3">
		      <label for="state">State</label>
		      <select id="state" class="form-control">
		        <option selected>Choose...</option>
		        <option>Drenthe</option>
		        <option>Flevoland</option>
		        <option>Friesland</option>
		        <option>Gelderland</option>
		        <option>Groningen</option>
		        <option>Limburg</option>
		        <option>North Brabant</option>
		        <option>North Holland</option>
		        <option>Overijssel</option>
		        <option>South Holland</option>
		        <option>Utrecht</option>
		        <option>Zeeland</option>
		      </select>
		    </div>
		  </div>
		  <div class="form-row">
		    <div class="form-group col-md-2">
		      <label for="zipCode">Postcode</label>
		      <input type="text" class="form-control" id="zipCode">
		    </div>
		    <div class="form-group col-md-2">
		      <label for="dob">Date of Birth</label>
		      <input type="text" class="form-control" id="datepicker">
		    </div>
		    
		  </div>
		  <div class="form-row">
		  	<div class="form-group col-md-4">
		  		<button type="submit" id = "submitData" class="btn btn-primary">Submit</button>
		  		<button type="submit" id = "loadData" class="btn btn-primary">Load Data</button>
		  	</div>		  	
		  </div>
		  
	</div>
	<br>
	<div id = "success" style="display:none" class="alert alert-success alert-dismissible fade show col-md-3" role="alert">
	</div>
	
	<div id = "danger" class="alert alert-danger alert-dismissible fade show col-md-4" role="alert">
	</div>
	
	<div id="container2" class="container">		
		<br>
		<div id = "searchDiv" class="input-group">
		  	<div class="col-md-3">
		  		<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
      			<input id="search" type="text" class="form-control" name="search" placeholder="Search">
		  	</div>		  	
		</div>
		
		<br>		
		<table id = "student_table" class="table table-striped">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">First Name</th>
		      <th scope="col">Last Name</th>
		      <th scope="col">Address</th>
		      <th scope="col">Date of Birth</th>
		      <th scope="col">Delete</th>
		    </tr>
		  </thead>
		  <tbody id = "tbody">
		  </tbody>
		</table>
		
		<br>
		<button type="button" id = "deleteData" class="btn btn-danger">Delete Row</button>
	</div>
	
	
</body>

<script>

$(document).ready(function(){
	
	$("#danger").css("display", "none");
	$("#success").css("display", "none");
	$("#container2").css("display", "none");
	
	$("#search").keyup(function(){
		search_table($(this).val());
	});
	
	function search_table(value){
		$("#tbody tr").each(function(){
			var found = false;
			$(this).each(function(){
				if($(this).text().toLowerCase().indexOf(value.toLowerCase()) >=0){
					found = true;
				}
			});
			if(found == true){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	}
	
	$("#deleteData").click(function(){
		var result = $("tr:has(:checked)");
	    var json = result.map(function () {
	        return [$(this).children().slice(0).map(function () {
	            return $(this).text().trim()
	        }).get()]
	    }).get();
	    
	    json.forEach(function(obj) { 
	    	console.log('You Selected Id ='+obj[0]); 
	    	$.ajax({
      	        type: "DELETE",
      	        url: "http://localhost:9090/cgi/student/" + obj[0],
      	      	contentType: 'application/json',
      	    	success: function( response, textStatus, jQxhr ){
      	    		$('#success').empty();
	    			$('#success').append(response);
	    			$("#success").delay(2).show(3500).delay(300).hide(2);
      	    	},
      	    	error: function( jqXhr, textStatus, errorThrown ){
      	    		$('#danger').empty();
	    			$('#danger').append(errorThrown);
	    			$("#danger").delay(2).show(3500).delay(300).hide(2);
      	    	}
      	  	});
	    });       
    });
	
	$("#loadData").click(function(){	
		$("#container2").delay(2).show();		
		$.ajax({
  	        type: "GET",
  	        url: "http://localhost:9090/cgi/student",
  	      	contentType: 'application/json',
  	    	success: function( response, textStatus, jQxhr ){
  	    		var trHTML = '';
  	    		$('#tbody').empty();
  	    		if(Array.isArray(response)){
  	    			$.each(response, function (i, item) {
  	  	              trHTML += '<tr><td>' + item.id + '</td><td>' + item.firstName + '</td><td>' + item.lastName 
  	  	              + '</td><td>' + item.address + '</td><td>' + item.dateOfBirth + '</td>'
  	  	              +'<td><input type=checkbox name=record></td>'
  	  	              +'</tr>';
  	  	          	});
  	  	          	$('#tbody').append(trHTML);
  	    		}else{
  	    			$("#container2").hide();
  	    			$('#danger').empty();
  	  	    		$('#danger').append(response);
  	  	    		$("#danger").delay(2).show(3500).delay(300).hide(2);
  	    		}
  	    		
  	          	
  	    	},
  	    	error: function( jqXhr, textStatus, errorThrown ){
  	       	 	$("student-table").hide();
  	    	}
  	  	});
	});
	
	$( "#datepicker" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: 'yy-mm-dd'
	});	
	
  	$("#submitData").click(function(){
  		if($("#firstName").val().length == 0){
  			$('#danger').empty();
	    	$('#danger').append('First Name cannot be blank.');
	    	$("#danger").show(5500).hide(2);
  		}else if($("#lastName").val().length == 0){
  			$('#danger').empty();
	    	$('#danger').append('Last Name cannot be blank.');
	    	$("#danger").show(5500).hide(2);
  		}else if($("#address").val().length == 0 || $( "#city" ).val().length == 0 || $( "#zipCode" ).val().length == 0 || $( "#state" ).val().length == 0){
  			$('#danger').empty();
	    	$('#danger').append('Address fields cannot be blank.');
	    	$("#danger").show(5500).hide(2);
  		}else if($( "#datepicker" ).datepicker({ dateFormat: 'yyyy-MM-dd' }).val().length == 0){
  			$('#danger').empty();
	    	$('#danger').append('Date of birth cannot be blank.');
	    	$("#danger").show(5500).hide(2);
  		}else{
  			var student = {
  	  				firstName: $("#firstName").val(),
  	  	  			lastName: $("#lastName").val(),
  	  	  			address: $("#address").val() + " " + $( "#city" ).val() +" " + $( "#zipCode" ).val() +" " + $( "#state" ).val(),
  	  	  			dateOfBirth : $( "#datepicker" ).datepicker({ dateFormat: 'yyyy-MM-dd' }).val()
  	  		}
  	  		
  	  		 $.ajax({
  	  	        type: "POST",
  	  	        url: "http://localhost:9090/cgi/student",
  		    	contentType: 'application/json',
  	  	      	data: JSON.stringify( student ) ,
  	  	    	success: function( data, textStatus, jQxhr ){
  	  	    		$('#success').empty();
  	  	    		$('#success').append(data);
  	  	    		$("#success").delay(2).show(3500).delay(300).hide(2);
  	  	    	},
  	  	    	error: function( jqXhr, textStatus, errorThrown ){
  	  	    		$('#danger').empty();
  	  	    		$('#danger').append(errorThrown);
  	  	    		$("#danger").delay(2).show(3500).delay(300).hide(2);
  	  	    	}
  	  	  	});
  		}
  		
  	});
});
</script>
</html>