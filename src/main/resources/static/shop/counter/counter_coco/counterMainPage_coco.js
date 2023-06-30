// 輪播圖
let slider_img = document.querySelector('#carouselExampleIndicators .carousel-inner');
let slider_dot = document.querySelector('#carouselExampleIndicators .carousel-indicators');

// 櫃位資訊
let counter_logo = document.querySelector('.counterIntroduce_area .effect-lexi img');
let counter_name = document.querySelector('.counterIntroduce_area .tittle-w3layouts');
let counter_about = document.querySelector('.counterIntroduce_area .introduce_grid p');

// 商品
let product_count = document.querySelector('.shop_area .page_amount p');
let product_space = document.querySelector('.shop_wrapper .row');

let counter_name_text = '';

getCounter();


// 獲取櫃位編號
function getCounter(){
	let url = location.search;
	let counterNo = new URLSearchParams(url).get('counterNo');

	// 刷出櫃位頁面
	getCarousel(counterNo);
	getCounterLogo(counterNo);
	getCounterInfo(counterNo);
	getProduct(counterNo);
}

// 獲取貴為輪播圖
function getCarousel(counterNo){
	axios.get(`/Jamigo/counterCarousel/getAllByCounterNoWithoutPic/${counterNo}`)
	.then(resp => resp.data)
	.then(datas => {
		for(let i = 0; i < datas.length; i++) 
			creatSliderImg(datas[i].counterCarouselNo, i);
	})
	.catch(err => console.log(err))
}

// 塞入櫃位輪播圖
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

// 獲取櫃位頭貼
function getCounterLogo(counterNo) {
	counter_logo.src = `/Jamigo/counterCtrlImg/${counterNo}`;
}

// 獲取櫃位資訊
function getCounterInfo(counterNo){
	axios.get(`/Jamigo/get/count/${counterNo}`)
	.then(resp => {
		return resp.data
	})
	.then(data => {
		counter_name.innerText = data.counterName;
		counter_about.innerText = data.counterAbout;
		counter_name_text = data.counterName;
	})
}

// 獲取櫃位商品
function getProduct(counterNo){
	axios.get(`/Jamigo/products/listAllCounterProducts/${counterNo}`)
	.then(resp => {
		return resp.data;
	})
	.then(datas => {
		for(let data of datas) {
			createProductItem(data);
		}
		let count = document.querySelectorAll('.shop_wrapper .single_product').length;
		product_count.innerText = `顯示 1-${count} 件商品，共有 ${count} 件商品`;
	})
}

// 塞入商品
function createProductItem(data){
	let ele = document.createElement('div');
	ele.classList.add('col-lg-3');
	ele.classList.add('col-md-4');
	ele.classList.add('col-sm-6');
	ele.classList.add('col-12');
	product_space.append(ele);

	let html = `
	<article class="single_product">
		<figure>
			<div class="product_thumb">
				<a class="primary_img" href="#">
					<img src="assets/img/product/product1.jpg" alt="">
				</a>
				<a class="secondary_img" href="#">
					<img src="assets/img/product/product2.jpg" alt="">
				</a>
				<div class="action_links">
					<ul>
						<li class="wishlist">
							<a href="#" title="追蹤商品">
								<i class="fa fa-heart-o" aria-hidden="true"></i>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="product_content grid_content">
				<div class="product_rating">
					<ul>
						<div class="Stars" style="--rating: 5;">
							<span>${data.evalTotalScore} (${data.evalTotalPeople})</span>
						</div>
					</ul>
				</div>
				<h5 class="counter_name">
					<a href="#">${counter_name_text}</a>
				</h5>
				<h4 class="product_name">
					<a href="#">${data.productName}</a>
				</h4>
				<div class="price_box">
					<span class="current_price">NT$ ${data.productPrice}</span>
				</div>
				<div class="add_to_cart">
					<a href="#" data-toggle="modal" data-target="#modal_box">加入購物車</a>
				</div>
			</div>
		</figure>
	</article>
	`;


	product_space.lastElementChild.innerHTML = html;
}
