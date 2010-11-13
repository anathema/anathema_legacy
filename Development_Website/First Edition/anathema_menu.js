var sourceImages = new Array(18);
var hoverImages = new Array(18);
var content = new Array(18);
var blank = new Image();
blank.src = "images/blank.gif";
var selected = new Image();
selected.src = "images/selected.gif";

function register(imgNumber, imgName) {
	sourceImages[imgNumber] = new Image();
	sourceImages[imgNumber].src = "images/u_" + imgName + ".gif";
	hoverImages[imgNumber] = new Image();
	hoverImages[imgNumber].src = "images/s_" + imgName + ".gif";
	content[imgNumber] = new String(imgName);	
}

function hover(imgNumber) {
	var display = hoverImages[imgNumber].src		
	window.document.images[imgNumber].src = display;
}
    
function unhover(imgNumber) {
	var display = sourceImages[imgNumber].src
	window.document.images[imgNumber].src = display;
}

function setSelectedStatus(selectedContent) {	
	for (var index = 2; index <= content.length; index=index+2){
	  if (selectedContent == content[index]){
	    window.document.images[index-1].src=selected.src;
	  }
	}	
}