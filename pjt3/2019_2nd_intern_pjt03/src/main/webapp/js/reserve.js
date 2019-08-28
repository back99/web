/**
 * reserve page
 */
const reserve = {
		apiId : '',
		reserveResponse : '',
		
		/**
		 * reserve 페이지 시작시 그려줄수 잇는 데이터 api 받아오기
		 */
		reservePageInit : function (displayInfoId) {
			reserve.apiId = displayInfoId;
			const reserveParam = `displayInfoId=${displayInfoId}`;
			const reserveUrl = '/reservation/api/reserve';

			$.ajax({
				type : 'GET',
				url : reserveUrl,
				data : reserveParam,
				success : reserve.drawReservePage,
				error : ajaxError.errorMessage
			});
		},
		
		/**
		 * reserve 페이지 그려주기
		 */
		drawReservePage : function (reserveResponseData){
			
			reserve.reserveResponse = reserveResponseData;
			
			const saveFileUrl = reserveResponseData.saveFileName;
			$('.img_thumb').attr('src', `/reservation/${saveFileUrl}`);

			const placeName = reserveResponseData.placeName;
			$('#detail_place_dsc').text(placeName);

			const openingHours = reserveResponseData.openingHours;
			$('#detail_hours_dsc').text(openingHours);

			$('.inline_txt').html($('.inline_txt').html().replace('{reserveDate}', reserveResponseData.reserveDate));

			const title = reserveResponseData.title;
			$('.preview_txt_tit').text(title);
			$('.title').text(title);
			
			const backUrl = `detail?displayInfoId=${reserve.apiId}`;
			$('.btn_back').attr('href', backUrl);

			// 영어로 되어있는 타입 한글로 컨버터
			const reserveTypeConvertor = reserveResponseData.reserve.map(reserves=> {
				
					const typeConvertor = {
							'A' : '성인',
							'Y' : '청소년',
							'B' : '유아',
							'S' : '셋트',
							'D' : '장애인',
							'C' : '지역주민',
							'E' : '얼리버드'
					}
					reserves.priceTypeName = typeConvertor[reserves.priceTypeName];
			
					return reserves;
				}
			);

			reserveResponseData.reserve = reserveTypeConvertor;

			const reserveTemplate = $('#reserveTemplate').html();
			const reserveBindTemplate = Handlebars.compile(reserveTemplate);
			const reserveBindTemplateResult = reserveBindTemplate(reserveResponseData);
			$('#detail_price_dsc').append(reserveBindTemplateResult);

			const reservePriceTemplate = $('#reservePriceTemplate').html();
			const reservePriceBindTemplate = Handlebars.compile(reservePriceTemplate);
			const reservePriceBindTemplateResult = reservePriceBindTemplate(reserveResponseData);
			$('.ticket_body').append(reservePriceBindTemplateResult);
		} ,
		
		/**
		 * 항목에 대한 + 버튼 클릭시
		 */
		plusSwitch : function(event) {
			let parentNode = event.currentTarget.parentNode;
			let ticketCount = parentNode.querySelector('input').value;
			const ticketPrice = parentNode.querySelector('.ticket_price').value;
			const ticketMaxCount = 100;
			const flagCount = 1;

			if(ticketCount<ticketMaxCount){
				ticketCount++;
				parentNode.querySelector('input').value = ticketCount;
				const totalPrice = ticketPrice * ticketCount;
				let priceNode = parentNode.nextElementSibling
			
				priceNode.querySelector('.total_price').innerHTML = totalPrice;
				let totalCount = $('#totalCount').text();
				totalCount++;
				$('#totalCount').text(totalCount);
			}

			if (ticketCount === flagCount) {
				$(event.currentTarget).closest('.count_control').children('.individual_price').toggleClass('on_color');
				$(event.currentTarget).closest('div').children('.ico_minus3').toggleClass('disabled');
				$(event.currentTarget).closest('div').children('.count_control_input').toggleClass('disabled');
			}
		},
		
		/**
		 * 항목에 대한 - 버튼 클릭시
		 */
		minusSwitch : function(event) {
			let parentNode = event.currentTarget.parentNode;
			let ticketCount = parentNode.querySelector('input').value;
			const ticketPrice = parentNode.querySelector('.ticket_price').value;
			const ticketMinCount = 0;
			const flagCount = 0;
			
			if(ticketCount > flagCount){
				ticketCount--;
				parentNode.querySelector('input').value = ticketCount;
				const totalPrice = ticketPrice * ticketCount;
				let priceNode = parentNode.nextElementSibling
			
				priceNode.querySelector('.total_price').innerHTML = totalPrice;
				let totalCount = $('#totalCount').text();
				totalCount--;
				$('#totalCount').text(totalCount);
			}
			
			if (ticketCount === ticketMinCount) {
				$(event.currentTarget).closest('.count_control').children('.individual_price').toggleClass('on_color');
				$(event.currentTarget).closest('div').children('.ico_minus3').toggleClass('disabled');
				$(event.currentTarget).closest('div').children('.count_control_input').toggleClass('disabled');
			}
		},
		
		/**
		 * 약관동의 항목 클릭
		 */
		agreementBtn : function(event) {
			$(event.currentTarget).closest('div').toggleClass('open');
			$(event.currentTarget).children('i').toggleClass('fn-up2');
			$(event.currentTarget).children('i').toggleClass('fn-down2');
		},

		/**
		 * 약관 더보기
		 */
		labelBtn :function() {
			$('.agreement').addClass('open');
			$('.btn_agreement>i').removeClass('fn-down2');
			$('.btn_agreement>i').addClass('fn-up2');
			$('.bk_btn_wrap').toggleClass('disable');
		},
		
		/**
		 * 예매하기 버튼 클릭
		 */
		reserveBtn : function() {
			
			if(reserve.reservationFormValidate()){
				
				const priceList = document.querySelectorAll('.qty');
				let priceArray = [];
				
				for(let priceListIndex = 0; priceListIndex < priceList.length; priceListIndex++) {
					let priceCount = priceList[priceListIndex].querySelector('.count_control>.clearfix>#count').value;
					let priceId = priceList[priceListIndex].querySelector('.count_control>.clearfix>#productPriceId').value;
					let priceInfoId = priceList[priceListIndex].querySelector('.count_control>.clearfix>#reservationInfoId').value;
					if(priceCount > 0){
						priceArray.push({'count':priceCount, 'productPriceId':priceId, 'priceInfoId':priceInfoId});
					}
				}
				
				// 서버로 보낼 데이터 만들기
				const reservationParam = {
						displayInfoId : reserve.apiId,
						prices : priceArray,
						productId : reserve.reserveResponse.productId,
						reservationEmail : document.reserveForm.email.value,
						reservationName : document.reserveForm.name.value,
						reservationTelephone : document.reserveForm.tel.value,
						reservationYearMonthDay : reserve.reserveResponse.reserveDate,
				}
				const reservationUrl = '/reservation/api/reservation';
				
				$.ajax({
					type : 'POST',
					url : reservationUrl,
					contentType : 'application/json; charset=UTF-8',
					dataType : 'json',
					data : JSON.stringify(reservationParam)
					success : function() {
						alert("예약되었습니다");
					},
					error : ajaxError.errorPage(error)
				});
			}
		},
		
		/**
		 * email, 전화번호, 약관에 대한 유효성 검사
		 */
		reservationFormValidate : function() {
			
			const validateData = document.reserveForm;
			let validateFlag = true;
			const visualSecond = 2000;
			const flagCount = 0;
			
			const emailData = validateData.email.value;
			if(!validator.emailValidate(emailData)) {
				$('#email_warning').css("visibility", "visible");
				$('#email_warning').fadeOut(visualSecond);
				validateFlag= false;
			}
			
			const telData = validateData.tel.value;
			if(!validator.telValidate(telData)) {
				$('#tel_warning').css("visibility", "visible");
				$('#tel_warning').fadeOut(visualSecond);
				validateFlag = false;
			}
			
			if($('.bk_btn_wrap').hasClass('disable')) {
				validateFlag = false;
			}
			
			const totalCount = $('#totalCount').text();
			if(totalCount === flagCount) {
				validateFalg = false;
			}
			
			return validateFlag;
		}
}

$(document).on('click', '.ico_plus3', reserve.plusSwitch);

$(document).on('click', '.ico_minus3', reserve.minusSwitch);

$(document).on('click', '.btn_agreement', reserve.agreementBtn);

$(document).on('click', '.chk_txt_label', reserve.labelBtn);

$(document).on('click', '.bk_btn', reserve.reserveBtn);