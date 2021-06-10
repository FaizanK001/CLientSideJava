/**
 * 
 */
console.log("hello");



// Hide the buttons 
$("#deletebtn").hide();
$("#updatebtn").hide();
$("#filmsdetails").hide();
$("#RawDatabtn").hide();	

// Get Film Details
$('#getfilmbtn').on('click', function(event){
	
	
	// show the buttons
	$("#deletebtn").show();
	$("#updatebtn").show();
	$("#filmsdetails").show();
	$("#RawDatabtn").show();	
	
var type = $("input[name= 'data']:checked").val();
var id = $('#getMovie').val();
var dataType = $('#combobox option:selected').text();

//raw data 
var rawdata= $('#rawdata');
// Variables 
  let filmId = $('#id');
let filmTitle = $('#title');
let filmYear = $('#year');
let filmDirector = $('#director');
let filmStars = $('#stars');
let filmReview = $('#review');

// Json Parser Function	
function Jsonparser(data){ 
	
let filmData = JSON.parse(data);

filmId.text(filmData.id);
filmTitle.text(filmData.title);
filmYear.text(filmData.year);
filmDirector.text(filmData.director);
filmStars.text(filmData.stars);
filmReview.text(filmData.review);

rawdata.html(data).hide();

console.log(rawdata);

}
// Xml parser function
function Xmlparser(data){	   
var s = new XMLSerializer();
var newXmlStr = s.serializeToString(data);

// DOMParser interface provides the ability to parse XML or HTML source code from a string into a DOM Document 
let parser = new DOMParser();
//XML formatted text and you need to handle it as an XML document
xmlDoc = parser.parseFromString(newXmlStr,"text/xml");


//getElementsByTagName() method returns a live HTMLCollection of elements with the given tag name
let xmlid = xmlDoc.getElementsByTagName("id")[0].childNodes[0].nodeValue;
let xmlTitle = xmlDoc.getElementsByTagName("title")[0].childNodes[0].nodeValue;
let xmlYear = xmlDoc.getElementsByTagName("year")[0].childNodes[0].nodeValue;
let xmlDirector = xmlDoc.getElementsByTagName("director")[0].childNodes[0].nodeValue;
let xmlStars = xmlDoc.getElementsByTagName("stars")[0].childNodes[0].nodeValue;
let xmlReview = xmlDoc.getElementsByTagName("review")[0].childNodes[0].nodeValue;


filmId.text(xmlid);
filmTitle.text(xmlTitle);
filmYear.text(xmlYear);
filmDirector.text(xmlDirector);
filmStars.text(xmlStars);
filmReview.text(xmlReview);

rawdata.text(newXmlStr).hide();
console.log(rawdata);
}
// String parser
function textparser(data){
	
var txtArray = data.split("#");

filmId.text(txtArray[0]);
filmTitle.text(txtArray[1]);
filmYear.text(txtArray[2]);
filmDirector.text(txtArray[3]);
filmStars.text(txtArray[4]);
filmReview.text(txtArray[5]);

rawdata.text(txtArray).hide();
				
}
	//console.log(dataType);
event.preventDefault();
	
$.ajax({
		type: 'GET',
		url: `/getFilm?getFilm=${id}&data=${type}&format=${dataType}`,
		success: function(data){ 

		if(dataType =="json"){
			Jsonparser(data);
		}
		
		else if(dataType == "xml"){
		Xmlparser(data);
		}
	   else if (dataType =="text"){
		 textparser(data);
		}
		
		
			
		},
		error: function(err){
		console.log(err)
		}
	
	});
		
});


	