/**
 * 메인페이지 draw and ajax
 */
let startingPoint = 0;
let selectProduct = 0;
let selectProductTotalCount = 0;

/**
 * 로드시 promotion draw
 */
$.ajax({
	type : 'GET',
	url : '/reservation/api/promotions',
	success : function(data) {

		let html = $('#promotionItem').html();
		let resultHTML = '';
		$('.pink').html(data.totalCount);

		data.items.forEach(function(item) {
			resultHTML += html.replace('{promotionUrl}', item.promotionUrl);
		});

		$('.visual_img').append(resultHTML);
	},
	error : errorMessage
});

/**
 * 로드시 category draw
 */
$.ajax({
	type : 'GET',
	url : '/reservation/api/categories',
	success : function(data) {
	
			let html = $('#categoryList').html();
			let totalCount = 0;
			let resultHTML = '';
			$('.pink').html(data.totalCount);
			resultHTML += html.replace('{name}', '전체리스트').replace(/{id}/g, 0);
	
			data.items.forEach(function(item) {
	
				totalCount += item.count;
				resultHTML += html.replace('{name}', item.name)
								  .replace(/{id}/g, item.id)
								  .replace('{count}', item.count);
			}
		);

		resultHTML = resultHTML.replace('{count}', totalCount);
		selectProductTotalCount = totalCount;

		$('#category').html(resultHTML);
		$('.pink').html(totalCount);
		$('#category>li>a').attr('class', 'anchor');
		$('#category>#0>a').attr('class', 'anchor active');
	},
	error : errorMessage
});

/**
 * 로드시 product draw
 */
getProduct();

/**
 * 카테고리 클릭시 프로덕트 전환
 */
$(document).on('click', '#category>li', function(event) {
	$('#category>li>a').attr('class', 'anchor');
	$(event.currentTarget).children('a').attr('class', 'anchor active');
	$('.pink').html(event.currentTarget.dataset.count);

	selectProductTotalCount = event.currentTarget.dataset.count;
	startingPoint = 0;
	selectProduct = event.currentTarget.dataset.category;

	$('.more>.btn').show();
	$('#leftProductContainer').empty();
	$('#rightProductContainer').empty();

	getProduct();

});

/**
 * 프로모션 슬라이드 효과 추가
 */
$(document).ready(
		function() {
			let imgSlideIndex = 0;
			setInterval(function() {
				let imgSlide = document.querySelector(".visual_img");
				let imgSize = imgSlide.querySelectorAll(".item").length;
				imgSlideIndex = (++imgSlideIndex) % imgSize;
				imgSlide.style.transform = "translate("+ -(100 * imgSlideIndex) + "%)";
			}, 2000);
		}
);

/**
 * 더보기 클릭시 product 4개 추가
 */
$(document).on('click', '.more>.btn', function() {
	getProduct();
});

/**
 * category클릭시 get 통신
 */
function getProduct() {
	let productRequestData;
	
	if(selectProduct===0){
		productRequestData = `start=${startingPoint}`;
	} else{
		productRequestData = `start=${startingPoint}&categoryId=${selectProduct}`;
	}
	
	$.ajax({
			type : 'GET',
			url : '/reservation/api/products',
			data : productRequestData,
			success : drawFourProductList,
			error : errorMessage
		}
	);
}

/**
 * 4개의 product를 가지고 오지 못햇을시 errorMessage 출력
 */
function errorMessage(request, status, error) {
	console.log('code:'+request.status+'\n message:'+request.responseText+'\n error:'+error);
}

/**
 * 4개의 product를 가지고 왔을시 draw 하는 함수
 */
function drawFourProductList(data) {

	let html = $('#itemList').html();
	let evenCount = 0;
	$('.pink').html(data.totalCount);

	data.items.forEach(function(item) {
			evenCount++;
			let resultHTML = html.replace(/{description}/g, item.productDescription)
								 .replace('{id}', item.displayInfoId)
								 .replace('{placeName}', item.placeName)
								 .replace('{thumbnailUrl}', item.productImageUrl)
								 .replace('{content}', item.productContent);
	
			if (evenCount % 2 === 0) {
				$('#leftProductContainer').append(resultHTML);
			} else {
				$('#rightProductContainer').append(resultHTML);
			}
		}
	);
	startingPoint += 4;

	if (startingPoint >= selectProductTotalCount) {
		$('.more>.btn').hide();
	}
}
