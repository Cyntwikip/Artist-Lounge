function doFirst() {
    var x = document.getElementById('paintbox');
    window.canvas = x.getContext('2d');
    window.addEventListener("mousemove", rys, false);
    // we need to keep track of mousedown and mouseup events as well
    window.addEventListener("mousedown", onmousedown, false);
    window.addEventListener("mouseup", onmouseup, false);
}

function onmousedown(e) {
    // keep track of when the first mouse button is pressed in a global variable
    window.mousepressed = (e.button == 0);
}

function onmouseup(e) {
    // if the user released the first mouse button, reset the global mousepressed flag
    window.mousepressed = !(e.button == 0);
}

function rys(e) {
    // we only want to draw if the user is currently pressing the first mouse button
    if (!window.mousepressed) return;
    var xPos = e.clientX;
    var yPos = e.clientY;
    if (e.layerX || e.layerX == 0) { // Firefox
        x = e.layerX;
        y = e.layerY;
    }
    else if (e.offsetX || e.offsetX == 0) { // Opera
        x = e.offsetX;
        y = e.offsetY;
    }
    canvas.fillStyle = "#000";
    canvas.beginPath();
    canvas.arc(xPos, yPos, 10, 0, Math.PI * 2, true);
    canvas.closePath();
    canvas.fill();

}
window.addEventListener("load", doFirst, false);
doFirst();