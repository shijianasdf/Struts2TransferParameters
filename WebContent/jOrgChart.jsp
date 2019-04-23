<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>
    <!-- jquery -->
    <script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
    <script src="js/jquery-1.12.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
    <!-- jquery-ui -->
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <link href="css/jquery-ui.min.css" rel="stylesheet">
  
    <!-- jquery.jOrgChart -->
    <link rel="stylesheet" href="css/jquery.jOrgChart.css"/>
       <link rel="stylesheet" href="css/prettify.css"/>
    <script src="js/jquery.jOrgChart.js"></script>
    <script src="js/prettify.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  $("#org").jOrgChart({
		  
		  chartElement : '#chart',
          dragAndDrop  : true
	  });
	   
	   
	   
  });
   
  
  
  </script>
  
    
    
   
</head>
<body>

<ul id="org" style="display:none;">
<li>
  Food
  <ul>
    <li>Beer</li>
    <li>Vegetables
      <ul>
        <li>Pumpkin</li>
        <li><a href="http://tquila.com" target="_blank">Aubergine</a></li>
      </ul>
    </li>
    <li>Bread</li>
    <li >Chocolate
      <ul>
        <li>Topdeck</li>
        <li>Reese's Cups</li>
      </ul>
    </li>
  </ul>
</li>
</ul>
<div id="chart" class="orgChart"></div>
</body>
</html>