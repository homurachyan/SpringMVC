<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
    <body>      <h1>登入表单</h1>    
      
<form action="/SpringMVC/login" method="post">
	username:<input type="text" name="username" />
	<p>
	password:<input type="password" name="password"/>
	<p>
	<input type="submit" value="submit" />
</form>     

<input id="demo" type="button" value="json" />
</body>
<script type="text/javascript">
$(function(){
	$('#demo').on('click',function(){
	  	$.ajax({
			url:'/SpringMVC/hello.json', //目标controler
	        type: 'GET',
	        data:{},
	        dataType: "json",
	        contentType:"application/json",  
	        cache: false,
	        async: false,
	        success: function (data,textStatus) {
	        	//alert('success');
	        	alert(JSON.stringify(data));
			},
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
			}
	    });
	});
});
</script>
</html>