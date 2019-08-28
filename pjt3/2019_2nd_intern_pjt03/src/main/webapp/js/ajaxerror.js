/**
 * errorMessage 출력
 */
const ajaxError = {
	errorMessage : function(request, exception) {

		NETWORK_ERROR_CODE: 0;
		PAGE_ERROR_CODE: 404;
		SERVER_ERROR_CODE: 500;
		PARSING_ERROR_CODE: 'parsererror';
		TIMEOUT_ERROR_CODE: 'timeout';

		if (response.status === ajaxError.NETWORK_ERROR_CODE) {
			alert('네트워크 에러!');
		} else if (response.status === ajaxError.PAGE_ERROR_CODE) {
			alert('페이지를 찾을수 없습니다.');
		} else if (response.status === ajaxError.SERVER_ERROR_CODE) {
			alert('서버 에러!');
		} else if (exception === ajaxError.PARSING_ERROR_CODE) {
			alert('parsing Request error!');
		} else if (exception === ajaxError.TIMEOUT_ERROR_CODE) {
			alert('time out!');
		} else if (exception === null || exception === undefined) {
			alert('Not data!');
		} else {
			alert('error! ' + request.responseText);
		}
	},
	errorPage : function(error) {
		$('body').html(error['responseText']);
	}
}