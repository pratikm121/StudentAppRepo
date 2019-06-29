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