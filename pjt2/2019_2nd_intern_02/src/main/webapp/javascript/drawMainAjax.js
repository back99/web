/**
 * 시작시 메인 화면을 그려주는 함수
 */
function drawMain(jsonArray) {
	var stateFlag = jsonArray.type;

	var fragment = document.createDocumentFragment();
	var article = document.createElement('article');
	article.id = 'article_id';
	article.className = jsonArray.id;
	fragment.appendChild(article);

	var tr = document.createElement('tr');
	tr.id = 'tr_id';
	fragment.getElementById('article_id').appendChild(tr);

	var h1 = document.createElement('h1');
	h1.id = 'h1_id';
	fragment.getElementById('article_id').appendChild(h1);
	fragment.getElementById('h1_id').textContent = jsonArray.title;

	var div = document.createElement('div');
	div.id = 'div_id';
	fragment.getElementById('article_id').appendChild(div);

	var h5 = document.createElement('h5');
	h5.id = 'h5_id';
	fragment.getElementById('div_id').appendChild(h5);
	fragment.getElementById('h5_id').textContent = '등록날짜:';
	fragment.getElementById('h5_id').textContent += jsonArray.regdate + ',';
	fragment.getElementById('h5_id').textContent += jsonArray.name + ',';
	fragment.getElementById('h5_id').textContent += '우선순위';
	fragment.getElementById('h5_id').textContent += jsonArray.sequence;

	if (stateFlag != 'DONE') {
		var button = document.createElement('button');
		button.id = 'button';
		fragment.getElementById('div_id').appendChild(button);
		button.addEventListener('click', clickBtn);
	}

	if (stateFlag === 'TODO') {
		document.getElementById('TODO').appendChild(fragment);
	} else if (stateFlag === 'DOING') {
		document.getElementById('DOING').appendChild(fragment);
	} else if (stateFlag === 'DONE') {
		document.getElementById('DONE').appendChild(fragment);
	} else {
		alert('todo type 값 에러!');
	}

}

/**
 * 이벤트 발생시 옆으로 옮겨주는 함수
 */
function clickBtn(event) {

	var xhr = new XMLHttpRequest();
	xhr.open('post', '/webapiexam/type', true);

	var dbId = this.parentNode.parentNode.className;
	var dbType = this.parentNode.parentNode.parentNode.id;
	var targetArticle = this.parentNode.parentNode;
	const serverErrorStatus = 500;
	const doneReadyState = 4;
	const serverSuccessStatus = 200;

	xhr.onreadystatechange = function() {
		if (xhr.responseText === 'success'
				&& xhr.status === serverSuccessStatus
				&& xhr.readyState === doneReadyState) {

			targetArticle.remove();

			if (dbType === 'DOING') {
				document.getElementById('DONE').appendChild(targetArticle);
				event.target.remove();

			} else if (dbType === 'TODO') {
				document.getElementById('DOING').appendChild(targetArticle);

			}
		} else if (xhr.status = serverErrorStatus) {
			alret('server error!');
		} else if (xhr.readyState != doneReadyState) {
			alret('데이터를 받지 못하였습니다.');
		} else {
			alret('응답을 받지 못하였습니다.');
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	const data = `id=${dbId}&type=${dbType}`;

	xhr.send(data);

}