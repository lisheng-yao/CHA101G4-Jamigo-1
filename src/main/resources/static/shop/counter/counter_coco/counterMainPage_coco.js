// 輪播圖
let slider_img = document.querySelector('#carouselExampleIndicators .carousel-inner');
let slider_dot = document.querySelector('#carouselExampleIndicators .carousel-indicators');

// 櫃位資訊
let counter_logo = document.querySelector('.counterIntroduce_area .effect-lexi img');
let counter_name = document.querySelector('.counterIntroduce_area .tittle-w3layouts');
let counter_about = document.querySelector('.counterIntroduce_area .introduce_grid p');
let counterInfo;
let currentCounterName;

// 商品
let product_count = document.querySelector('.shop_area .page_amount p');
let product_space = document.querySelector('.shop_wrapper');

// 商品燈箱
let modal_body_img = document.querySelector('.modal_body .modal_tab_img')




getCounter();


// 獲取櫃位編號
function getCounter(){
	let url = location.search;
	let counterNo = new URLSearchParams(url).get('counterNo');

	// 刷出櫃位頁面
	getCounterInfo(counterNo);
	getCarousel(counterNo);
	getCounterLogo(counterNo);
	getProduct(counterNo);
}

// 獲取櫃位輪播圖
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
		if(resp.data.counterStat == 0 || resp.data.counterStat == 2) {
			Swal.fire({
				icon: 'warning',
				title: '查無此櫃位',
				text: '此櫃位查詢無結果',
				confirmButtonText: "確認"
			})
			.then(() => window.location.href = '/Jamigo/shop/main_page/商城首頁.html')
			
		} else{
			return resp.data
		}
	})
	.then(data => {
		console.log(data);
		counterInfo = data;
		counter_name.innerText = data.counterName;
		counter_about.innerText = data.counterAbout;
		currentCounterName = data.counterName;
		document.title = `${data.counterName} | 品牌首頁 | Jamigo Mall 線上購物`;
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
	console.log(product_space);
	// console.log(counterInfo);
	// console.log(data);
	let ele = document.createElement('div');
	ele.classList.add('col-lg-3');
	ele.classList.add('col-md-4');
	ele.classList.add('col-sm-6');
	ele.classList.add('col-12');
	product_space.append(ele);
	let score = data.evalTotalPeople == 0 ? 0 : data.evalTotalScore / data.evalTotalPeople;
	score = parseFloat(score.toFixed(1));
	
	let html = `
	<article class="single_product">
		<figure>
			<div class="product_thumb">
				<a class="primary_img" href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${data.productNo}">
					<img src="/Jamigo/shop/product_picture/product/${data.productNo}" alt="">
				</a>
				<div class="action_links">
					<ul>
						<li class="wishlist">
							<a href="#" onclick="addWish(this, ${data.productNo});" title="追蹤商品">
								<i class="fa-solid fa-heart" aria-hidden="true"></i>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="product_content grid_content">
				<div class="product_rating">
					<ul>
						<div class="Stars" style="--rating: ${score};">
							<span>${score} (${data.evalTotalPeople})</span>
						</div>
					</ul>
				</div>
				<h5 class="counter_name">
					<a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${counterInfo.counterNo}">${counterInfo.counterName}</a>
				</h5>
				<h4 class="product_name">
					<a href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${data.productNo}">${data.productName}</a>
				</h4>
				<div class="price_box">
					<span class="current_price">NT$ ${data.productPrice}</span>
				</div>
				<div class="add_to_cart">
					<a class="add_to_cart_btn" href="#" data-toggle="modal" data-target="#modal_box">加入購物車</a>
				</div>
			</div>
			<div class="product_content list_content">
				<div class="product_rating">
					<ul>
						<div class="Stars" style="--rating: ${score};">
							<span>${score} (${data.evalTotalPeople})</span>
						</div>
					</ul>
				</div>
				<h5 class="counter_name">
					<a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${counterInfo.counterNo}">${counterInfo.counterName}</a>
				</h5>
				<h4 class="product_name">
					<a href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${data.productNo}">${data.productName}</a></h4>
				<div class="product_desc">
					<p>${data.productInfo}</p>
				</div>
				<div class="price_box">
					<span class="current_price2">NT$ ${data.productPrice}</span>
				</div>

				<div class="action_links list_action_right">
					<ul>
						<li class="add_to_cart">
							<a href="#" data-toggle="modal" data-target="#modal_box">加入購物車</a>
						</li>
						<li class="wishlist">
							<a href="#" title="追蹤商品">
								<i class="fa-solid fa-heart" aria-hidden="true"></i>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</figure>
	</article>
	`;

	product_space.lastElementChild.innerHTML = html;
}
product_space.addEventListener('click', e => {
	if(e.target.classList.contains('add_to_cart_btn')) {
		// 獲取遍利用的父元素
		let parent = e.target.parentElement;
		let granParent = parent.parentElement;
		// 獲取商品編號
		let link = granParent.querySelector('h4 a').href;
		let queryString = link.split('?')[1];
		let num = new URLSearchParams(queryString).get("productNo");
		// 到後端拿商品資訊
		axios.get(`/Jamigo/products/getProductForDetailPage/${num}`)
		.then(resp => {
			return resp.data;
		})
		.then(data => {
			console.log("data");
			console.log(data);
			document.querySelector('.modal_right .counter_no').innerText = data.counterNo;
			document.querySelector('.modal_counter_name').innerText = currentCounterName;
			document.querySelector('.modal_title h2').innerText = data.productName;
			document.querySelector('.modal_tab_img img').src = `/Jamigo/shop/product_picture/product/${data.productNo}`;
			document.querySelector('.modal_price .new_price').innerText = data.productPrice;
			document.querySelector('.modal_description p').innerText = data.productInfo;

		})
	}
})