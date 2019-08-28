/**
 * 유효성 검사
 */
const validator = {
		
		/**
		 * 이메일 형식 체크
		 */
		emailValidate : function (emailData) {
			const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			return (emailData != '' && emailData != 'undefined' && emailRegex.test(emailData));
		},
		
		/**
		 * 전화번호 형식 체크
		 */
		telValidate : function (telData) {
			const telRegex = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
			return (telData != '' && telData != 'undefined' && telRegex.test(telData));
		},
		
		/**
		 * 이미지 확장자 체크
		 */
		imageValidator : function (imageData) {
			return (imageData.type === 'image/jpg' || imageData.type === 'image/png');
		}
}