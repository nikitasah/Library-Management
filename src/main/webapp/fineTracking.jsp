<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>FineTracking</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
	#update {
		padding: 2%;
		width: 20%;
		display: inline;
		margin-left: 18%;
	}
	#display {
		padding: 2%;
		margin-top: 5%;
		width: 20%;
		margin-left: 18%;
		display: inline;
	}
	#error {
		text-align:center;
		font-size:18pt;
		color:red;
	}
	.container {
		margin-top: 5%;
	}
	#header {
		background-color: black;
		color: white;
		text-align: center;
		overflow: auto;
		}
	#success {
		color:black;
		text-align:center;
		font-size:18pt;
		}
	
	</style>
</head>
<body>
	<div id="header">
		<h2>Fine Tracking</h2>
	</div>
	<form action="FineTrackingService" method="post">
		<input type="submit" id="update" name="update" value="Update-Fine">
		<input type="submit" id="display" name="display" value="Display-Fine">
	</form>
	<div id="error">
		<c:if test="${not empty errordisplayfine}">
	   		<c:out value="${errordisplayfine}"/>
		</c:if>
	</div>
	<div id="error">
		<c:if test="${not empty errordisplayfine}">
	   		<c:out value="${errordisplayfine}"/>
		</c:if>
	</div>
	<div id ="success">
		<c:if test="${not empty success}">
   			<c:out value="${success}"/>
		</c:if>
	</div>
	<div class="container">
		<table class="table table-bordered">
			<c:if test="${not empty fine}">
				<tr>
				   	<td><strong>Card_id</strong></td>
				   	<td><strong>Fname</strong></td>
				   	<td><strong>Lname</strong></td>
				   	<td><strong>Fine_Amount</strong></td>
				</tr>
				<c:forEach items="${fine}" var="fine">
				<tr>
				   	<td>${fine.loan.cardId}</td>
				   	<td>${fine.borrower.fname}</td>
				   	<td>${fine.borrower.lname}</td>
				   	<td>${fine.fineAmt}</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>