<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
 #formstyle{
 	
 }
</style>
</head>
<body>
<a href="test?name=lala&age=76">跳转</a>
<div id="formstyle">
    <form action="Search" method="post">
    	<input type="text" name="name" >
    	<button type="submit">Search</button>
    </form>
    
    <form action="TestForm" method="post">
    	 您喜欢的水果？<br /><br />
<label><input name="Fruit" type="checkbox" value="longzhilin" />苹果 </label>
<label><input name="Fruit" type="checkbox" value="dengyulan" />桃子 </label>
<label><input name="Fruit" type="checkbox" value="shijian" />香蕉 </label>
<label><input name="Fruit" type="checkbox" value="sadgf" />梨 </label>

<select name="animals" multiple="multiple">
   <option value="man">小猫</option>
   <option value="woman">小狗</option>
   <option value="dfgsf">小猪</option>
   <option value="fgawr">小马</option>
</select>
    	<button type="submit">Search</button>
    </form>
</div>
</body>
</html>