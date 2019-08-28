/**
 * api 데이터 받아오는 함수
 */
const reviewpage = {
	reviewpageInit : function(apiId) {
		dispalyInfoUrl = `/reservation/api/products/${apiId}`,

		$.ajax({
			type : 'GET',
			url : dispalyInfoUrl,
			success : reviewpage.drawDetailPage,
			error : ajaxError.errorMessage
		});
	},

	/**
	 * 받아온 데이터를 가지고 리뷰페이지를 그려주는 함수
	 */
	drawDetailPage : function(displayInfoResponseData) {
		commondraw.drawCommonSection(displayInfoResponseData);

		// 타이틀
		$('.resoc_name').text(displayInfoResponseData.displayInfo.productDescription);

		// 뒤로가기
		const backUrl = `detail?displayInfoId=${displayInfoResponseData.displayInfo.displayInfoId}`;
		$('.btn_back').attr('href', backUrl);
	}
}