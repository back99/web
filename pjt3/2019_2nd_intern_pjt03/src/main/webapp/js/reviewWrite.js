/**
 * reviewWrite page
 */
const reviewWrite = {

	REVIEW_MAX_SIZE : 400,
	REVIEW_MIN_SIZE : 5,
	reservationInfoId : '',
	imageFlag : false,
	imageFile : '',

	/**
	 * 타이틀 적기
	 */
	reviewWritePageInit : function(reservationInfoId, title) {
		$('.title').text(title);
		reviewWrite.reservationInfoId = reservationInfoId;
	},

	/**
	 * 리뷰등록하기
	 */
	registerBtn : function() {
		var reviewText = $('.review_contents>.review_textarea').val();
		let reviewTextSize = reviewText.length;

		if (reviewTextSize < reviewWrite.REVIEW_MIN_SIZE) {
			alert("최소 5자 이상 입력 가능합니다.");
			return ;
		}

		if (!reviewWrite.imageFlag) {
			alert("이미지를 넣어 주세요.");
			return ;
		}

		if (reviewTextSize >= reviewWrite.REVIEW_MIN_SIZE && reviewTextSize <= reviewWrite.REVIEW_MAX_SIZE) {
			const starScore = $('.rating>.star_rank').text();
			var formData = new FormData();

			// 이미지 파일
			formData.append('imageFile', reviewWrite.imageFile);
			// comment
			formData.append('comment', reviewText);
			// reservationInfoId
			formData.append('reservationInfoId', reviewWrite.reservationInfoId);
			// 별점
			formData.append('score', starScore);

			const reviewWriteUrl = '/reservation/api/reviewWrite';

			$.ajax({
				type : 'POST',
				url : reviewWriteUrl,
				data : formData,
				processData : false,
				contentType : false,
				enctype : 'multipart/form-data',
				success : function() {
					location.href = '/reservation/myreservation';
				},
				error : ajaxError.errorPage(error)
			});

		}
	},

	/**
	 * 별점 주기
	 */
	startBtn : function(event) {
		$('.rating>.rating_rdo').removeClass('checked');
		$('.rating>.rating_rdo').prop('checked', false);
		let checkBoxList = document.querySelectorAll('.rating>.rating_rdo');
		const currentStar = $(event.currentTarget);
		const starScore = currentStar[0].value;

		for (let checkBoxIndex = 0; checkBoxIndex < starScore; checkBoxIndex++) {
			checkBoxList[checkBoxIndex].classList.add('checked');
		}

		$('.rating>.star_rank').text(starScore);
		$('.rating>.star_rank').removeClass('gray_star');
	},

	/**
	 * 리뷰내용 적는 페이지
	 */
	writePage : function() {
		$('.review_contents>.review_write_info').hide();
		$('.review_contents>.review_textarea').focus();

		$('.review_contents>.review_textarea')
				.keyup(
						function(event) {
							let reviewTextContent = $(event.currentTarget).val();
							$('#writing_count').text(reviewTextContent.length);

							if (reviewTextContent.length > reviewWrite.REVIEW_MAX_SIZE) {
								alert("최대 400자까지 입력 가능합니다.");
								$(this).val(reviewTextContent.substring(0, reviewWrite.REVIEW_MAX_SIZE));
								$('#writing_count').text(reviewWrite.REVIEW_MAX_SIZE);
							}
						});
	},

	/**
	 * 이미지 올리기
	 */
	uploadImage : function(event) {
		reviewWrite.imageFile = event.currentTarget.files[0];

		if (validator.imageValidator(reviewWrite.imageFile)) {
			reviewWrite.imageFlag = true;
			$('.item>img').attr('src', window.URL.createObjectURL(reviewWrite.imageFile));
			$('.lst_thumb>.item').show();
		} else {
			alert('잘못된 이미지 입니다.');
		}
	},

	/**
	 * 이미지 삭제하기
	 */
	deleteImage : function(event) {
		$(event.currentTarget).closest('.lst_thumb>.item').hide();
		reviewWrite.imageFlag = false;
	},
}

/**
 * 리뷰페이지 포커스 잃기
 */
$('.review_contents>.review_textarea').blur(function() {
	$('.review_contents>.review_write_info').show();
});

$(document).on('click', 'a>.ico_del', reviewWrite.deleteImage);

$(document).on('change', '#reviewImageFileOpenInput', reviewWrite.uploadImage);

$(document).on('click', '.box_bk_btn>.bk_btn', reviewWrite.registerBtn);

$(document).on('click', '.review_contents>.review_write_info', reviewWrite.writePage);

$(document).on('click', 'input', reviewWrite.startBtn);