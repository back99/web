/**
 * myreservation 페이지
 */
const myreservation = {
		
		myreservationPageInit : function () {
			const reservationUrl = '/reservation/api/myreservation';

			$.ajax({
				type : 'GET',
				url : reservationUrl,
				success : myreservation.drawReservationPage,
				error : function(error) {
					$('body').html(error['responseText']);
				}
			});
		},
		
		/**
		 * 예약이 있을때 draw
		 */
		drawReservationPage : function (reservationInfoResponse) {
			$('.err').hide();
			
			const currentDate = new Date();
			let confirmedCount = 0;
			let finishedCount = 0;
			let cancelCount = 0;
			
			reservationInfoResponse.reservations.forEach(function(reservations){
				
				const reserveTypeConvertor = reservations.priceCount.map(priceCounts=> {
					
					const typeConvertor = {
							'A' : '성인',
							'Y' : '청소년',
							'B' : '유아',
							'S' : '셋트',
							'D' : '장애인',
							'C' : '지역주민',
							'E' : '얼리버드'
					}
					priceCounts.typeName = typeConvertor[priceCounts.typeName];

					return priceCounts;
					}
				);

				reservations.priceCount = reserveTypeConvertor;
				
				if(currentDate.getTime() - reservations.reservationDate < 0 && !reservations.cancelFlag){
					// 예약 확정 부분
					confirmedCount++;

					const confirmedReservationData = reservations;
					const confirmedReservationTemplate = $('#confirmedReservationTemplate').html();
					const confirmedReservationBindTemplate = Handlebars.compile(confirmedReservationTemplate);
					const confirmedReservationBindTemplateResult = confirmedReservationBindTemplate(confirmedReservationData);
					$('#confirmed_list').append(confirmedReservationBindTemplateResult);
					
				} else if(currentDate.getTime() - reservations.reservationDate >= 0 && !reservations.cancelFlag){
					// 이용완료 부분
					finishedCount++;
					
					const finishedReservationData = reservations;
					const finishedReservationTemplate = $('#finishedReservationTemplate').html();
					const finishedReservationBindTemplate = Handlebars.compile(finishedReservationTemplate);
					const finishedReservationBindTemplateResult = finishedReservationBindTemplate(finishedReservationData);
					$('#finished_list').append(finishedReservationBindTemplateResult);
					
				} else{
					// 취소 부분
					cancelCount++;
					
					const cancelReservationData = reservations;
					const cancelReservationTemplate = $('#cancelReservationTemplate').html();
					const cancelReservationBindTemplate = Handlebars.compile(cancelReservationTemplate);
					const cancelReservationBindTemplateResult = cancelReservationBindTemplate(cancelReservationData);
					$('#cancel_list').append(cancelReservationBindTemplateResult);
				}
				
			});
			
			$('#total_count').text(reservationInfoResponse.size);
			$('#reservation_count').text(confirmedCount);
			$('#finished_count').text(finishedCount);
			$('#cancel_count').text(cancelCount);
		},
		
		/**
		 * 예약 취소 팝업창
		 */
		moveToCancelList : function(event) {
			const reservationTitle = $(event.currentTarget).closest('.card_detail').children('.tit').text();
			const reservationDate = $(event.currentTarget).closest('.card_detail').find('.detail>li>#reservationDate').text();
			
			$('.pop_tit>span').text(reservationTitle);
			$('.pop_tit>small').text(reservationDate);
			
			$('.popup_booking_wrapper').show();
			
			const noBtn = document.querySelector('#no_btn');
			
			noBtn.addEventListener('click', function() {
					$('.popup_booking_wrapper').hide();
				}
			);
			
			const yesBtn = document.querySelector('#yes_btn');
			const reservationInfoId = $(event.currentTarget).closest('article').attr('id');
			const currentCardItem = $(event.currentTarget).closest('article');
			
			yesBtn.addEventListener('click', function() {
					let reservationCount = $('#reservation_count').text();
					let cancelCount = $('#cancel_count').text();
					reservationCount--;
					cancelCount++;
					
					currentCardItem.children('.fn-share1').remove();
					currentCardItem.find('.link_booking_details>.card_body>.middle>.card_detail>.booking_cancel>.btn').remove();
					$('#cancel_list').append(currentCardItem);
					$(event.currentTarget).closest('article').remove;
					$('#reservation_count').text(reservationCount);
					$('#cancel_count').text(cancelCount);
					$('.popup_booking_wrapper').hide();

					const updateReservationParam = `reservationInfoId=${reservationInfoId}`;
					const updateReservationUrl = '/reservation/api/updateReservation';

					$.ajax({
						type : 'GET',
						url : updateReservationUrl,
						data : updateReservationParam,
						error : ajaxError.errorMessage
					});
				}
			);
		}
}

/**
 * 취소버튼 클릭
 */
$(document).on('click', '.booking_cancel>.btn', myreservation.moveToCancelList);