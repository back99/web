<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<!DOCTYPE html>
<html>
<head>

<meta charset='UTF-8'>
<title>todoMain</title>
<link href='css/main.css' rel='stylesheet' type='text/css' />
</head>
<body>

	<header>
		<div id='headerLeft'>나의 해야할 일들</div>
		<div id='headerRight'>
			<a href='/webapiexam/form'> 새로운 TODO 등록 </a>
		</div>
	</header>

	<div>
		<section id='TODO'>
			<article class='type'>TODO</article>
		</section>

		<section id='DOING'>
			<article class='type'>DOING</article>
		</section>

		<section id='DONE'>
			<article class='type'>DONE</article>
		</section>
	</div>

	<script type='text/javascript'
		src='javascript/drawMainAjax.js?version=123'></script>
	<script type='text/javascript'>
		var jsonArray = JSON.parse('${todosJson}');

		for ( var todoJsonIndex in jsonArray) {
			drawMain(jsonArray[todoJsonIndex]);
		}
	</script>

</body>
</html>