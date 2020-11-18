const audio = document.getElementById('audio');
const select = document.getElementById('select');

function changeRate() {
    audio.playbackRate = select.options[select.selectedIndex].value;
}

function playend() {
    audio.src = "0687.mp3";
    audio.load();
    audio.play();
}