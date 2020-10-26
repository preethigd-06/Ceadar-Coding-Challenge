var headers;
var dataList;
var header;
$( document ).ready(function() {
	getData();
});

function getData(){
	$.ajax({
	    url: "/ceadar-software-developer/APIMethod",
	    data: {
	        function_name: 'getTableData'
	    },
	    type: "POST",
	    dataType : 'json',
	    success: function(data) {
	        if(data.status == true){
	        	drawTable(data);
	        }
	    }
	  });
}

function drawTable(data){
	var dataset = [];
	dataList = data.dataList;
	header = [];
	var html = '<select style="float: right;margin: 21px;font-size: 15px;height: 25px;" id="sort">';
	headers = data.header;
	for(var j=0;j<data.header.length;j++){
		var obj = {};
		html += '<option value="'+j+'">'+data.header[j]+'</option>';
		obj.title = data.header[j];
		header.push(obj);
	}
	html += '</select><p style="float: right;font-size: 15px;font-weight: bold;color: black;margin: 19px; ">sort by</p>';
	$('#header').html(html);
	var index = 1;
	for(var i=0;i<dataList.length;i++){
		var arr = [];
		for(var j=0;j<data.header.length;j++){
			var temp = dataList[i][data.header[j]].replace(/&/g, "&amp;")
	         .replace(/</g, "&lt;")
	         .replace(/>/g, "&gt;")
	         .replace(/"/g, "&quot;")
	         .replace(/'/g, "&#039;");
			arr.push('<input id="id_'+index+'" style = "width: 100%;" type="text" value="'+temp+'">');
			index = index+1;
		}
		dataset.push(arr);
	}
	$('#data-table').DataTable({
        data: dataset,
        columns: header,
        "bSort" : false,
        "bLengthChange": false,
        "searching": false
    } );
	$('#footer').html('<button style="font-size: 17px;width: 64px;height: 34px;" type="button" class="btn btn-primary">save</button>');
	$(".btn").click(function(){
		var obj = $('#data-table').DataTable().rows().data().toArray();
		for(var i=0;i<obj.length;i++){
			for(var j=0;j<obj[i].length;j++){
				obj[i][j] = $('#'+obj[i][j].substring(obj[i][j].indexOf('id="')+4,obj[i][j].indexOf('" style'))).val();
			}
		}
		$.ajax({
		    url: "/ceadar-software-developer/APIMethod",
		    data: {
		        function_name: 'saveTableData',
		        data: JSON.stringify(obj),
		        header: JSON.stringify(headers),
		    },
		    type: "POST",
		    dataType : 'json',
		    success: function(data) {
		        alert("saved successfully");
		        location.reload();
		    }
		  });
	});
	$('#sort').change(function() {
		  var ind = $('#sort').val();
		  for(var i=0;i<dataList.length;i++){
			  for(var j=i+1;j<dataList.length;j++){
				  if(dataList[i][data.header[ind]] > dataList[j][data.header[ind]]){
					  var tempVar = dataList[i];
					  dataList[i] = dataList[j];
					  dataList[j] = tempVar;
				  }
			  }
		  }
		  dataset = [];
		  for(var i=0;i<dataList.length;i++){
				var arr = [];
				for(var j=0;j<data.header.length;j++){
					var temp = dataList[i][data.header[j]].replace(/&/g, "&amp;")
			         .replace(/</g, "&lt;")
			         .replace(/>/g, "&gt;")
			         .replace(/"/g, "&quot;")
			         .replace(/'/g, "&#039;");
					arr.push('<input id="id_'+index+'" style = "width: 100%;" type="text" value="'+temp+'">');
					index = index+1;
				}
				dataset.push(arr);
			}
		  $('#data-table').DataTable().destroy();
		  $('#data-table').DataTable({
		        data: dataset,
		        columns: header,
		        "bSort" : false,
		        "bLengthChange": false,
		        "searching": false
		    } );
	});
}