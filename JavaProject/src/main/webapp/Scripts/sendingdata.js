
// Add Film to the database
$('#postfilmbtn').on('click', function(event){
	
event.preventDefault();
// Combo box formatting variable check the input format than send the data as json or xml format
var dataf = $("input[name= 'dataformat']:checked").val();

// url variable ,datatype: json or xml
var urljx;
var jx;
var jxdtype;


// If and else condition 	
if(dataf == 'sendj'){
	sendjson(json); 
}
else if(dataf == 'sendx'){
	sendxml(xml);
}
			
$.ajax({
	  type: "POST",
	  url: `/rest/filmsList/`,
	  contentType: `${urljx}; charset=UTF-8`,
	  data:jx,    
      dataType: jxdtype,
   	  success: function(data){
	
	alert("Film Added");
	console.log(data);

	},
	
	    error: function(errMsg) {
	         alert(errMsg);
		}
		});
// Send data in json format function
function sendjson(json){

var film = {
id: $("#mid").val(),
title:$("#mtitle").val(),
year:$("#myear").val(),
director:$("#mdirector").val(),
stars:$("#mstars").val(),
review:$("#mreview").val()
}

//console.log(film.id);
// converts a JavaScript object or value to a JSON string
var json = JSON.stringify(film);

// url variable
urljx = "application/json"; 
jx = json;
jxdtype = 'json';
}	


// send in Xml function
function sendxml(xml){

var addId =$('#mid').val();
var addTitle =$('#mtitle').val();
var addYear=$('#myear').val();
var addDirector=$('#mdirector').val();
var addStars=$('#mstars').val();
var addReview=$('#mreview').val();

var xml = `<film> 
           <id>${addId}</id>
           <title>${addTitle}</title>
           <year>${addYear}</year>
           <director>${addDirector}</director>
           <stars>${addStars}</stars>
           <review>${addReview}</review></film>`;

urljx = "application/xml";
jx = xml;
jxdtype= 'xml';
}		
});
	
	
	
	
	
// Update Films
	$('#putfilmbtn').on('click', function(event){
	
event.preventDefault();
//var jsonstr;

// Combo box formatting variable check the input format than send the data as json or xml format
var dataupdate = $("input[name= 'Udataformat']:checked").val();
var urlupdate;
var formatjx;
var formattype;
	
	

if(dataupdate == 'updatej'){
		updatejson(json); 
}
else if(dataupdate == 'updatex'){
	updatexml(xml);
}
$.ajax({
	  type: "PUT",
	  url: `/rest/filmsList/`,
	  contentType: `${urlupdate}; charset=UTF-8`,
	  data: formatjx,
      dataType: formattype,
   	  success: function(data){
	
	alert("Updated Film");
	console.log(data);

	},
	
	    error: function(errMsg) {
	         alert(errMsg);
		}
});
		
// Update send data json format
function updatejson(json){
	var film = {
		id: $("#uid").val(),
		title:$("#utitle").val(),
		year:$("#uyear").val(),
		director:$("#udirector").val(),
		stars:$("#ustars").val(),
		review:$("#ureview").val()
		}
	
// converts a JavaScript object or value to a JSON string
var json = JSON.stringify(film);

urlupdate = "application/json";
formatjx = json;
formattype = 'json';
}
// format send data xml format
function updatexml(xml){
// variable getting id from the index page
var updateId =$('#uid').val();
var updateTitle =$('#utitle').val();
var updateYear=$('#uyear').val();
var updateDirector=$('#udirector').val();
var updateStars=$('#ustars').val();
var updateReview=$('#ureview').val();

var xml = `<film> 
           <id>${updateId}</id>
           <title>${updateTitle}</title>
           <year>${updateYear}</year>
           <director>${updateDirector}</director>
           <stars>${updateStars}</stars>
           <review>${updateReview}</review></film>`;


           urlupdate="application/xml";
           formatjx=xml;
           formattype = 'xml';


}				
});

// Delete Film		
$('#deletefilmbtn').on('click', function(event){
	
event.preventDefault();
//  get the id value from index page
let id = $("#did").val();
	
$.ajax({
	  type: "DELETE",
	  url: `/rest/filmsList/${id}`,
   	  success: function(data){
	
	alert("Deleted film");
	console.log(data);

	},
	
	    error: function(errMsg) {
	         alert(errMsg);
		}
		});
	});
	