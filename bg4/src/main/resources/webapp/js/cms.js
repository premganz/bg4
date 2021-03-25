  var greet='';
		
    var greet='';
	$(document).ready(function(){
	  $("#fileselect2").click(function(){
		  var msg= 'Select  '+ $(this).attr('id')+'   '+ $(this).val();
	  $("#myEdit").html(msg);
	  $("#myTextArea").val("new text");
	
	  });
	});
	
	
	$(document).ready(function(){
	$("#fileselect1").click(function(){
	    $.ajax({
	    	url: "http://localhost:8089/admin/list2/"+$(this).val(),async: false, 
	    	dataType: 'json',
	    	success: function(data,textStatus){
	    		console.log(data);
	        //$("#myTextArea").val(JSONstringify(data));
	       
	        $("#fileName").prop("background-color", "PINK");
	       // $("#fileName").val(JSONstringify(data));
	        //tinyMCE.get('mytextarea').setContent(JSONstringify(data));
	        var len = data.length;
            $("#fileselect2").empty();
            var i = 0;            
            var options='';
            for (;data[i];) {
            	console.log(data[i]);
              options += '<option value="' + data[i]+ '">' + data[i]+ '</option>';
              i++;
            }
            $("#fileselect2").html(options);
	       //$('#fileselect2').append(['a','b']);
	    },
	    
	    error:function(exception){alert('Exeption:'+exception);}
	    
	    });
	   
	});
	});
	
	
	$(document).ready(function(){
		$("#fileselect2").click(function(){
			console.log($("#fileselect2").val());
		    $.ajax({
		    	url: "http://localhost:8089/admin/list3/"+$("#fileselect1").val()+"/"+$("#fileselect2").val(),
		    	async: false, 
		    	dataType: 'json',
		    	success: function(data,textStatus){
		        var len = data.length;
	            $("#fileselect3").empty();
	            var i = 0;            
	            var options='';
	            for (;data[i];) {
	            	console.log(data[i]);
	              options += '<option value="' + data[i]+ '">' + data[i]+ '</option>';
	              i++;
	            }
	            $("#fileselect3").html(options);
		       //$('#fileselect2').append(['a','b']);
		    },
		    
		    error:function(exception){alert('Exeption:'+exception);}
		    
		    });
		   
		});
		});
	
    
		$(document).ready(function(){
		$("#fetch1").click(fetchStaged);
		});
		
		var fetchFunction=function(){
			
		    $.ajax(
		    	{url: "http://localhost:8089/admin/content/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val(),async: false, 
		    	success: function(result){
		    		console.log(result);
		    		//tinyMCE.activeEditor.setContent(result);
		    		tinyMCE.get('mytextarea').setContent(result);
		    		$("#fileName").prop("background-color", "PINK");
		    	}
		    	});
		}
		var fetchStaged=function(){
			
		    $.ajax(
		    	{url: "http://localhost:8089/admin/contentStaging/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val(),async: false, 
		    	success: function(result){
		    		console.log(result);
		    		//tinyMCE.activeEditor.setContent(result);
		    		tinyMCE.get('mytextarea').setContent(result);
		    		$("#fileName").prop("background-color", "PINK");
		    	}
		    	});
		    $.ajax(
			    	{url: "http://localhost:8089/admin/contentStagingMeta/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val(),async: false, 
			    	success: function(result){
			    		console.log(result);
			    		//tinyMCE.activeEditor.setContent(result);
			    		$("#mytextarea3").val(result);
			    	}
			    	});
		    
		}
		
		$(document).ready(function(){
			$("#fileselect3").click(fetchFunction);
			
		});
		
		$(document).ready(function(){
			$("#createfile").click(function(){
				$.ajax({
					url:"http://localhost:8089/admin/content/createFile/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileName").val(),
					success:function(data, textStatus){
						console.log(data);
						
					}
				})
			})
			
		});
		
		
		
		function JSONstringify(json) {
		    if (typeof json != 'string') {
		        json = JSON.stringify(json, undefined, '\t');
		    }

		    json = json.replace(/\"/g, "");
		    json = json.replace(/\,/g, "");
		    
		   
		    return json;
		}
		
	