<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.bakery.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/full.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SHOW ITEMS</title>
</head>
<body class="full">
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">Bootcamp Bakery</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="readDB.jsp">View Baked Goods</a></li>
				<li><a href="addToDB.html">Add Item</a></li>
				<li><a href="deleteItem.jsp">Delete Item</a></li>
				<li><a href="modifyItem.jsp">Modify Item</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

	<div class="container img-rounded" style="background-color: white;">
		<h1>Bakery Items</h1>
		<!--  <div class="well well-sm">-->
		<h3>See below for the list of available products:</h3>
		<!--  </div>-->
		<table class="table table-striped table-hover table-nonfluid">
			<thead>
				<tr>
					<th>Product ID</th>
					<th>Type</th>
					<th>Calories</th>
					<th>Price</th>
					<th>Topping</th>
				</tr>
			</thead>
			<tbody>
				<%
					DAO.readFromDB();
				%>
				<%
					BakeryItem readToJSP = new BakeryItem();
					for (int i = 0; i < DAO.bakeryItems.size(); i++) {
						readToJSP = DAO.bakeryItems.get(i);
				%>
				<tr>
					<td><%=readToJSP.getProductID()%></td>
					<td><%=readToJSP.getType()%></td>
					<td><%=readToJSP.getCalories()%></td>
					<td><%=readToJSP.getPrice()%></td>
					<td><%=readToJSP.getTopping()%></td>
				</tr>
				<%
					}
				%>
				<%
					DAO.bakeryItems.clear();
				%>
			</tbody>
		</table>

	</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>