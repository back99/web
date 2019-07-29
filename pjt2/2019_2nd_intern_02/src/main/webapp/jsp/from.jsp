<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width'>
<title>todoRegister</title>
<link href='css/form.css' rel='stylesheet' type='text/css'/>
</head>

<body>

	<header>
		<h1>할일 등록</h1>
	</header>

	<form action='/webapiexam/add' accept-charset='utf-8' name='todo_info'
		method='POST'>
			어떤일인가요? <br/> 
			<input type='text' name='title' placeholder='swift 공부하기(24자까지)'/> <br/> 
			누가할일인가요? <br/> 
			<input type='text' name='name' placeholder='홍길동'/> <br/> 
			우선순위를 선택하세요 <br/>
			<input type='radio' name='sequence' value=1/> 1순위 
			<input type='radio' name='sequence' value=2/> 2순위
			<input type='radio' name='sequence' value=3/> 3순위 <br>
			<button>
				<a href='/webapiexam/todo'> < 이전 </a>
			</button>
		<input type='submit' value='제출'/> <input type='reset' value='내용지우기'/>
	</form>

	<script type='text/javascript'>
		
		if('${errorMessage}'){
			var errorMessage = '${errorMessage}';
			alert(errorMessage);
		}
	</script>

</body>
</html>