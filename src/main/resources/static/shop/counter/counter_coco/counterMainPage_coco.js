let slider_img = document.querySelector('#carouselExampleIndicators .carousel-inner');
let slider_dot = document.querySelector('#carouselExampleIndicators .carousel-indicators');
let counter_logo = document.querySelector('.counterIntroduce_area .effect-lexi img');


// 目前先拿櫃位1的輪播圖
getPicNoByCounterNo(1);
getCounterLogo(1);
function getPicNoByCounterNo(counterNo){
	axios.get(`/Jamigo/counterCarousel/getAllByCounterNoWithoutPic/${counterNo}`)
	.then(resp => resp.data)
	.then(datas => {
		for(let i = 0; i < datas.length; i++) 
			creatSliderImg(datas[i].counterCarouselNo, i);
	})
	.catch(err => console.log(err))
}

function creatSliderImg(counterCarouselNo, i) {
	
	//創建新的dot
	let elementDot = document.createElement('li');
	if(i == 0) 
		elementDot.classList.add('active');
	elementDot.setAttribute('data-slide-to', i);
	elementDot.setAttribute('data-target', '#carouselExampleIndicators');
	slider_dot.append(elementDot);
	
	
	// 創建新的圖片輪播
	let elementImg = document.createElement('div');
	if(i == 0)
		elementImg.classList.add('active');
	elementImg.classList.add('carousel-item');
	slider_img.append(elementImg);
	slider_img.lastElementChild.innerHTML = 
		`<div class="carousel-item-img">
            <img class="d-block" src="/Jamigo/counterCarouselImg/${counterCarouselNo}" alt="counterCarousel${i + 1}">
        </div>`;
}

function getCounterLogo(counterNo) {
	counter_logo.src = `/Jamigo/counterCtrlImg/${counterNo}`;
}
