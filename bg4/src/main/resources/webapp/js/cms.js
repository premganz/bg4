var greet='';

var greet='';
$(document).ready(function(){
	$("#fileselect2").change(function(){
		var msg= 'Select  '+ $(this).attr('id')+'   '+ $(this).val();
		$("#myEdit").html(msg);
		$("#myTextArea").val("new text");

	});
});


$(document).ready(function(){
	$("#fileselect1").click(function(){
		$.ajax({
			url: "/admin/list2/"+$(this).val(),async: false, 
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
		if($("#fileselect2").val()=='index.txt'){
			return;
		}
		$.ajax({
			url: "/admin/list3/"+$("#fileselect1").val()+"/"+$("#fileselect2").val(),
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
	$("#fileselect3").click(function(){
		console.log($("#fileselect3").val());
		if($("#fileselect3").val()=='index.txt'){
			return;
		}
		$.ajax({
			url: "/admin/list4/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val(),
			async: false, 
			dataType: 'json',
			success: function(data,textStatus){
				var len = data.length;
				$("#fileselect4").empty();
				var i = 0;            
				var options='';
				for (;data[i];) {
					console.log(data[i]);
					options += '<option value="' + data[i]+ '">' + data[i]+ '</option>';
					i++;
				}
				$("#fileselect4").html(options);
				//$('#fileselect2').append(['a','b']);
			},

			error:function(exception){alert('Exeption:'+exception);}

		});

	});
});

$(document).ready(function(){
	$("#fetch1").click(fetchStaged);
});

$(document).ready(function(){
	$("#fetch").click(fetch);
});



var fetch=function(){
	var path1 = $("#fileselect1").val();
	var path2 = $("#fileselect2").val();
	var path3 = $("#fileselect3").val();
	var path4=  $("#fileselect4").val();
	var url =  "/admin/content/";
//	if (path2 == '1'){
//	path2='';
//	}if(path3 =='1'){
//	path3='';
//	}if(path4=='1'){
//	path4="";
//	}


	$.ajax(
			{url: "/admin/content/"+path1+"/"+path2+"/"+path3+"/"+path4,async: false, 
				success: function(result){
					console.log(result);
					//tinyMCE.activeEditor.setContent(result);
					tinyMCE.get('mytextarea').setContent(result);
					$("#fileName").prop("background-color", "PINK");
				}
			});

	$.ajax(
			{url: "/admin/contentmeta/"+path1+"/"+path2+"/"+path3+"/"+path4,async: false, 
				success: function(result){
					console.log(result);
					//tinyMCE.activeEditor.setContent(result);
					$("#mytextarea3").val(result);
				}
			});
}





var fetchStaged=function(){

	$.ajax(
			{url: "/admin/contentStaging/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val(),async: false, 
				success: function(result){
					console.log(result);
					//tinyMCE.activeEditor.setContent(result);
					tinyMCE.get('mytextarea').setContent(result);
					$("#fileName").prop("background-color", "PINK");
				}
			});
	$.ajax(
			{url: "/admin/contentStagingMeta/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val(),async: false, 
				success: function(result){
					console.log(result);
					//tinyMCE.activeEditor.setContent(result);
					$("#mytextarea3").val(result);
				}
			});

}



$(document).ready(function(){
	$("#createfile").click(function(){
		$.ajax({
			url:"/admin/content/createFile/"+$("#fileselect1").val()+"/"+$("#fileselect2").val()+"/"+$("#fileselect3").val()+"/"+$("#fileName").val(),
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


