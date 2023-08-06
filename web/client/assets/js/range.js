window.onload = function(){
    slideOne();
    slideTwo();
}
let money = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'VND'
});
let sliderOne = document.getElementById("slider-1");
let sliderTwo = document.getElementById("slider-2");
let displayValOne = document.getElementById("range1");
let displayValTwo = document.getElementById("range2");
let minGap = 0;
let sliderTrack = document.querySelector(".slider-track");
let sliderMaxValue = document.getElementById("slider-1").max;

function slideOne(){
    if(parseInt(sliderTwo.value) - parseInt(sliderOne.value) <= minGap){
        sliderOne.value = parseInt(sliderTwo.value) - minGap;
    }
    var temp = sliderOne.value;
    var money1 = money.format(temp);
    displayValOne.textContent = money1;
    fillColor();
}
function slideTwo(){
    if(parseInt(sliderTwo.value) - parseInt(sliderOne.value) <= minGap){
        sliderTwo.value = parseInt(sliderOne.value) + minGap;
    }
    var temp = sliderTwo.value;
    var money2 = money.format(temp);
    displayValTwo.textContent = money2;
    fillColor();
}
function fillColor(){
    percent1 = (sliderOne.value / sliderMaxValue) * 100;
    percent2 = (sliderTwo.value / sliderMaxValue) * 100;
    sliderTrack.style.background = `linear-gradient(to right, #dadae5 ${percent1}% , #6F6F6F ${percent1}% , #6F6F6F ${percent2}%, #dadae5 ${percent2}%)`;
}