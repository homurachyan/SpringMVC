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
	username:<input type="text" id="username" />
	<p>
	password:<input type="password" id="password"/>
	<p>
	<input id="submit" type="button" value="submit" />
</form>     

<input id="demo" type="button" value="json" />
</body>
<script type="text/javascript">
$(function(){
	$('#demo').on('click',function(){
		var token = "";
		var userid = "";
		var allCookie=document.cookie.split("; ");
		for(var i=0;i<allCookie.length;i++){ 
			var cookie = allCookie[i].split("=");
			if("token"==cookie[0]){ 
				token = cookie[1];
			}
			if("uid"==cookie[0]){
				userid = cookie[1];
			}
		}
		
	  	$.ajax({
			url:'/SpringMVC/hello', //目标controler
	        type: 'GET',
	        data:{
	        		"token":token,
	        		"userid":userid
	        	 },
	        dataType: "json",
	        contentType:"application/json",  
	        cache: false,
	        async: false,
	        success: function (data,textStatus) {
	        	alert(JSON.stringify(data));
	        	if(data.error_code!=null&&data.error_code=='001'){
	        		window.location.href="/SpringMVC/"+data.view;
	        	}else{
	        		window.location.href="/SpringMVC/hello?client=pc";
	        	}
			},
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
			}
	    });
	});
	
	$('#submit').on('click',function(){
	  	$.ajax({
			url:'/SpringMVC/login.json', //目标controler
	        type: 'POST',
	        data:{
	        		"username":$('#username').val(),
	        		"password":$('#password').val()
	        	 },
	        dataType: "json",
	        contentType:"application/x-www-form-urlencoded",  
	        cache: false,
	        async: false,
	        success: function (data,textStatus) {
	        	alert(JSON.stringify(data));
/* 	        	if(data.error_code!=null&&data.error_code=='001'){
	        		window.location.href="/SpringMVC/"+data.view;
	        	} */
	        	document.cookie="token="+data.token; 
	        	document.cookie="uid="+data.userid; 
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