/**
 * detail , review page 공통 부분 
 */
const commondraw = {
		drawCommonSection : function(displayInfoResponseData) {
			
			// 소수점 표시
			const commentscoreConvertor = displayInfoResponseData.comments.map(comment=> { 
				comment.score=comment.score.toFixed(1);
				return comment;
				}
			);
			displayInfoResponseData.comments = commentscoreConvertor;
			
			// 코멘트
			const commentsTemplate = $('#commentsTemplate').html();
			const commentsBindTemplate = Handlebars.compile(commentsTemplate);
			const commentsBindTemplateResult = commentsBindTemplate(displayInfoResponseData)
			$('.list_short_review').append(commentsBindTemplateResult);
			
			// 평균점수
			const averageScore = displayInfoResponseData.averageScore;
			$('.grade_area>strong>span').text(averageScore);
			
			// 별점
			const grapthValue = averageScore/5*100;
			$('.graph_mask>.graph_value').css('width', `${grapthValue}%`);
			
			// 코멘트 건수 등록
			const commentsCount = displayInfoResponseData.totalCount;
			$('.join_count>.green').text(`${commentsCount}건`);
			
		}
}