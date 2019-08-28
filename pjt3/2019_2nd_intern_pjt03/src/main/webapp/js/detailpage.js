/**
 * api 데이터 받아오는 함수
 */
const detailpage = {
		
		dispalyInfoId : '',
		detailPageInit : function(apiId) {
			
			detailpage.dispalyInfoId = apiId;
			const displayInfoParam = 'commentLimitFlag=true';
			const dispalyInfoUrl = `/reservation/api/products/${apiId}`;
			
			const detailAjax=$.ajax({
					type : 'GET',
					url : dispalyInfoUrl,
					data : displayInfoParam,
					success : detailpage.drawDetailPage,
					error : ajaxError.errorMessage
				}
			);
			
			const etImageUrl = '/reservation/api/image/et';
			const etImageParam = `displayInfoId=${apiId}`;
			
			$.when(detailAjax).done(function(){
					$.ajax({
						type : 'GET',
						url : etImageUrl,
						data : etImageParam,
						success :detailpage. drawEtImage,
						error : ajaxError.errorMessage
						}
					);
				}
			);
		},
		
		/**
		 * 받아온 데이터를 가지고 디테일페이지를 그려주는 함수
		 */
		drawDetailPage : function(displayInfoResponseData) {
			
			// 좌석 할인율
			const discountTemplate = $('#discountTemplate').html();
			const discountBindTemplate = Handlebars.compile(discountTemplate);
			const discountBindTemplateResult = discountBindTemplate(displayInfoResponseData);
			$('.event_info>.in_dsc').append(discountBindTemplateResult);

			// 타이틀
			const titleTemplate = $('#titleTemplate').html();
			const titleBindTemplate = Handlebars.compile(titleTemplate);
			const titleBindTemplateResult = titleBindTemplate(displayInfoResponseData)
			$('.container_visual>ul').append(titleBindTemplateResult);

			// 길찾기
			const pathTemplate = $('#pathTemplate').html();
			const pathBindTemplate = Handlebars.compile(pathTemplate);
			const pathBindTemplateResult = pathBindTemplate(displayInfoResponseData)
			$('.section_info_tab').append(pathBindTemplateResult);

			commondraw.drawCommonSection (displayInfoResponseData);
			
			// 소개
			const productContent = displayInfoResponseData.displayInfo.productContent;
			$('.store_details').text(productContent);
			$('.detail_info_lst>p').text(productContent);

			// 한줄평 더보기 url
			const commentUrl = `review?displayInfoId=${displayInfoResponseData.displayInfo.displayInfoId}`;
			$('.btn_review_more').attr('href', commentUrl);
			
			//예약 url
			const bookinglogin = 'bookinglogin';
			$('.btn_my').attr('href', bookinglogin);
		},
		
		/**
		 * et 이미지 그려주는 함수
		 */
		drawEtImage : function (etImageSaveFileName) {

			const etImagesNotValue = 'NotValue';

			if (etImageSaveFileName === '' || etImageSaveFileName === etImagesNotValue) {
				const notEtPageNum = 1;
				
				$('.figure_pagination>span>span').text(notEtPageNum);
				$('.btn_nxt').hide();
				$('.btn_prev').hide();
			} else {
				$('.container_visual>ul>.item').clone().appendTo('.container_visual>ul');
				$('.container_visual>ul>li:nth-of-type(2)>img').attr('src', `/reservation/${etImageSaveFileName}`);
				detailpage.slideImages();
			}
		},
		
		/**
		 * 슬라이드 효과
		 */
		slideImages : function() {

			const slideContents = document.querySelectorAll('.visual_img>li');
			const slideList = document.querySelector('.visual_img');
			const SLIDE_SIZE = slideContents.length;
			const slideBtnNext = document.querySelector('.btn_nxt');
			const slideBtnPrev = document.querySelector('.btn_prev');
			const SLIDE_WIDTH = 414;
			const SLIDE_SPEED = 1000;
			const SLIDE_LAST_INDEX = SLIDE_SIZE - 1;
			const SLIDE_FIRST_INDEX = 0;
			const STOP_SPEED = 0;
			const VISUAL_INDEX = 2;
			const RESET = -1;
			let pageCount = 0;
			let currentIndex = 0;
			let clickMoveFlag = true;
			
			//앞 뒤 이미지 붙이기
			const firstChild = slideList.firstElementChild;
			const lastChild = slideList.lastElementChild;
			const clonedFirst = firstChild.cloneNode(true);
			const clonedLast = lastChild.cloneNode(true);
			slideList.appendChild(clonedFirst);
			slideList.insertBefore(clonedLast, slideList.firstElementChild);

			slideList.style.transform = 'translate3d(-' + SLIDE_WIDTH + 'px, 0px, 0px)';
			slideList.style.transition = STOP_SPEED + 'ms';
				
			//오른쪽 버튼 클릭
			slideBtnNext.addEventListener('click', function() {
					if(clickMoveFlag){
						clickMoveFlag = false;
						if (currentIndex <= SLIDE_LAST_INDEX) {
							slideList.style.transition = SLIDE_SPEED + 'ms';
							slideList.style.transform = 'translate3d(-' + (SLIDE_WIDTH * (currentIndex + VISUAL_INDEX)) + 'px, 0px, 0px)';
						}
						if (currentIndex === SLIDE_LAST_INDEX) {
							setTimeout(function() {
								slideList.style.transition = STOP_SPEED + 'ms';
								slideList.style.transform = 'translate3d(-' + SLIDE_WIDTH + 'px, 0px, 0px)';
								clickMoveFlag = true;
							}, SLIDE_SPEED);
							currentIndex = RESET;
						} else{
							clickMoveFlag = true;
						}
						++currentIndex;
						$('#slideNum').text(++pageCount % SLIDE_SIZE + 1);
					}
				}
			);

			//왼쪽 버튼 클릭
			slideBtnPrev.addEventListener('click', function() {
					if(clickMoveFlag){
						clickMoveFlag = false;
						if (currentIndex >= SLIDE_FIRST_INDEX) {
							slideList.style.transition = SLIDE_SPEED + 'ms';
							slideList.style.transform = 'translate3d(-' + (SLIDE_WIDTH * currentIndex) + 'px, 0px, 0px)';
						}
						if (currentIndex === SLIDE_FIRST_INDEX) {
							setTimeout(function() {
								slideList.style.transition = STOP_SPEED + 'ms';
								slideList.style.transform = 'translate3d(-' + (SLIDE_WIDTH * SLIDE_SIZE) + 'px, 0px, 0px)';
								clickMoveFlag = true;
							}, SLIDE_SPEED);
							currentIndex = SLIDE_SIZE;
						} else{
							clickMoveFlag = true;
						}
						--currentIndex;
						$('#slideNum').text(++pageCount % SLIDE_SIZE + 1);
					}
				}
			);
		},
		/**
		 * path, detail switch
		 */
		itemSwitch : function() {
			$('.detail_area_wrap').toggleClass('hide');
			$('.detail_area_wrap').toggleClass('active');
			$('.detail_location').toggleClass('hide');
			$('.detail_location').toggleClass('active');
			
			$('.info_tab_lst>._detail>a').toggleClass('active');
			$('.info_tab_lst>._path>a').toggleClass('active');
		},

		/**
		 * open, close switch
		 */
		moreSwitch : function() {
			$('.store_details').toggleClass('close3');
			$('._open').toggle();
			$('._close').toggle();
		}
}

/**
 * 펼쳐보기/접기 클릭
 */
$(document).on('click', '.bk_more', detailpage.moreSwitch);

/**
 * 오시는길/상세정보 클릭
 */
$(document).on('click', '.info_tab_lst>.item', detailpage.itemSwitch);

/**
 * 예매하기 클릭
 */
$(document).on('click', '.bk_btn', function() {
	location.href = `reserve?displayInfoId=${detailpage.dispalyInfoId}`
	}
);