/**
 * 메인페이지 draw and ajax
 */
const mainpage = {
		 startingPoint : 0,
		 selectProduct : 0,
		 selectProductTotalCount : 0,
		 
		 /**
		  * 로드시 promotion draw
		  */
		 promotionDraw : function(promotionData) {
			let html = $('#promotionItem').html();
			let resultHTML = '';
			$('.pink').html(promotionData.totalCount);
			
			promotionData.items.forEach(function(item) {
				resultHTML += html.replace('{promotionUrl}', item.promotionUrl);
			});
			
			$('.visual_img').append(resultHTML);
		},

		
		/**
		 * 로드시 category draw
		 */
		categoryDraw : function(categoryData) {
				
						let html = $('#categoryList').html();
						let totalCount = 0;
						let resultHTML = '';
						$('.pink').html(categoryData.totalCount);
						resultHTML += html.replace('{name}', '전체리스트').replace(/{id}/g, 0);
				
						categoryData.items.forEach(function(item) {
							totalCount += item.count;
							resultHTML += html.replace('{name}', item.name)
											  .replace(/{id}/g, item.id)
											  .replace('{count}', item.count);
						}
					);
			
					resultHTML = resultHTML.replace('{count}', totalCount);
					mainpage.selectProductTotalCount = totalCount;
			
					$('#category').html(resultHTML);
					$('.pink').html(totalCount);
					$('#category>li>a').attr('class', 'anchor');
					$('#category>#0>a').attr('class', 'anchor active');
				},
		
		/**
		 * category클릭시 get 통신
		 */
		getProduct : function() {
			let productRequestData;
			
			if(mainpage.selectProduct == 0){
				productRequestData = `start=${mainpage.startingPoint}`;
			} else{
				productRequestData = `start=${mainpage.startingPoint}&categoryId=${mainpage.selectProduct}`;
			}
			$.ajax({
					type : 'GET',
					url : '/reservation/api/products',
					data : productRequestData,
					success :mainpage.drawFourProductList,
					error : ajaxError.errorMessage
				}
			);
		},
		
		/**
		 * 4개의 product를 가지고 왔을시 draw 하는 함수
		 */
		drawFourProductList : function(productData) {

			let html = $('#itemList').html();
			let evenCount = 0;
			$('.pink').html(productData.totalCount);

			productData.items.forEach(function(item) {
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
			mainpage.startingPoint += 4;

			if (mainpage.startingPoint >= mainpage.selectProductTotalCount) {
				$('.more>.btn').hide();
			}
		},
		
		changeCategory : function(event) {
			$('#category>li>a').attr('class', 'anchor');
			$(event.currentTarget).children('a').attr('class', 'anchor active');
			$('.pink').html(event.currentTarget.dataset.count);
		
			mainpage.selectProductTotalCount = event.currentTarget.dataset.count;
			mainpage.startingPoint = 0;
			mainpage.selectProduct = event.currentTarget.dataset.category;
		
			$('.more>.btn').show();
			$('#leftProductContainer').empty();
			$('#rightProductContainer').empty();
		
			mainpage.getProduct();
		},
		
		slideMove : function() {
			let imgSlideIndex = 0;
			setInterval(function() {
				let imgSlide = document.querySelector('.visual_img');
				let imgSize = imgSlide.querySelectorAll('.item').length;
				imgSlideIndex++;
			
				if(imgSlideIndex % imgSize==0){
					$('.visual_img').append($('.visual_img').html());
				}
				imgSlide.style.transform = 'translate('+ -(100 * imgSlideIndex) + '%)';
			}, 2000);
		}
}

/**
 * category ajax
 */
$.ajax({
		type : 'GET',
		url : '/reservation/api/categories',
		success : mainpage.categoryDraw,
		error : ajaxError.errorMessage
	}
),

/**
 * prmotion ajax
 */
$.ajax({
		type : 'GET',
		url : '/reservation/api/promotions',
		success : mainpage.promotionDraw,
		error : ajaxError.errorMessage
	}
),

mainpage.getProduct();

/**
 * 카테고리 클릭시 프로덕트 전환
 */
$(document).on('click', '#category>li', mainpage.changeCategory);

/**
 * 프로모션 슬라이드 효과 추가
 */
$(document).ready(mainpage.slideMove);

/**
 * 더보기 클릭시 product 4개 추가
 */
$(document).on('click', '.more>.btn', function() {
		getProduct();
	}
);
