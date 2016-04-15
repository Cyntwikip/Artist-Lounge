var canvasDiv = document.getElementById('canvasdiv');

var colorPurple = "#cb3594";
var colorGreen = "#659b41";
var colorYellow = "#ffcf33";
var colorBrown = "#986928";
var colorWhite="#ffffff";
var curColor = colorGreen;
var clickColor = new Array();










canvas = document.createElement('canvas');
canvas.setAttribute('width', 1200);
canvas.setAttribute('height',600);
canvas.setAttribute('id', 'canvas');
canvasDiv.appendChild(canvas);
if(typeof G_vmlCanvasManager != 'undefined') {
	canvas = G_vmlCanvasManager.initElement(canvas);
}
context = canvas.getContext("2d");


$('.eraser').mousedown(function(e){
	
	 curColor=colorWhite;
});
$('.pen').mousedown(function(e){
	
	 curColor=colorGreen;
});
$('.save').mousedown(function(e){
	
	//var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");
	
	//window.location.href=image;
	
	var image = canvas.toDataURL();

    var aLink = document.createElement('a');
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click");
    aLink.download = 'image.png';
    aLink.href = image;
    aLink.dispatchEvent(evt);
	
	//php save
	/*
	var myDrawing = document.getElementById("canvas");
	var drawingString= myDrawing.toDataURL("image/png");
	var postData="canvasData="+drawingString;
	var ajax=new XMLHttpRequest();
	ajax.open("POST",'saveImage.php',true);
	ajax.setRequestHeader('Content-Type','canvas/upload');
	ajax.onreadystatechange=function()
	{
		if(ajax.readyState==4)
			(alert("image saved"));
	}
	ajax.send(postData);

	*/
	
	
});
$('#canvas').mousedown(function(e){
	  var mouseX = e.pageX - this.offsetLeft;
	  var mouseY = e.pageY - this.offsetTop;
	
	  paint = true;
	  addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop);
	  redraw();
	});
$('#canvas').mousemove(function(e){
	  if(paint){
	    addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true);
	    redraw();
	  }
	});
$('#canvas').mouseup(function(e){
	  paint = false;
	});
$('#canvas').mouseleave(function(e){
	  paint = false;
	});
var clickX = new Array();
var clickY = new Array();
var clickDrag = new Array();
var paint;

function addClick(x, y, dragging)
{
  clickX.push(x);
  clickY.push(y);
  clickDrag.push(dragging);
  clickColor.push(curColor);
}
function redraw(){
	  context.clearRect(0, 0, context.canvas.width, context.canvas.height); // Clears the canvas
	  
	  //context.strokeStyle = "#df4b26";
	  context.lineJoin = "round";
	  context.lineWidth = 5;
				
	  for(var i=0; i < clickX.length; i++) {		
	    context.beginPath();
	    if(clickDrag[i] && i){
	      context.moveTo(clickX[i-1], clickY[i-1]);
	     }else{
	       context.moveTo(clickX[i]-1, clickY[i]);
	     }
	     context.lineTo(clickX[i], clickY[i]);
	     context.closePath();
	     context.strokeStyle = clickColor[i];
	     context.stroke();
	  }

	}