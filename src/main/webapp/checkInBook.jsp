<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Check-in Book</title>
	<script type="text/javascript">
		function setIsbn() {
			var count = $('input[name=check]:checked').val();
			var isbn = document.getElementById('isbn_'+count).innerText;
			var cardId = document.getElementById('card_'+count).innerText;
			document.getElementById('card-id').value = cardId;
			document.getElementById('selected-isbn13').value = isbn;
			document.getElementById("formcheckout").submit();
		}
	</script>
	<style type="text/css">
		.container {
			margin-top: 4%;
		}
		#txtbox {
	     	font-size:10pt;
	     	height:10%;
	     	width:40%;
	   	 	margin-top: 5%;
	   	 	margin-left: 20%;
		}
		#success {
			color:black;
			text-align:center;
			font-size:18pt;
		}
		#search {
			padding:8px 20px;
			margin-top: 3%;
		}
		#error {
			text-align:center;
			font-size:18pt;
			color:red;
		}
		#header {
			background-color: black;
			color: white;
			text-align: center;
			overflow: auto;
		}
	</style>
</head>
<body>
	<div id="header">
		<h2>Check-in Book</h2>
	</div>
	<form action="BookCheckInService" method="post">
		<input type="text" name="search" placeholder="Type card_id, name or isbn to search for a book" id="txtbox">
		<input type="submit" value="Search" id="search">
	</form>
	<div id="error">
		<c:if test="${not empty errorcheckin}">
   			<c:out value="${errorcheckin}"/>
		</c:if>
	</div>
	<div id ="success">
		<c:if test="${not empty success}">
   			<c:out value="${success}"/>
		</c:if>
	</div>
	
	<div class="container">
		<form id="formcheckout" action="BookCheckInService" method="post">
			<table class="table table-bordered">
				<c:if test="${not empty loan}">
					<tr>
						<td><strong>Book_id</strong></td>
						<td><strong>Card_id</strong></td>
						<td><strong>Fname</strong></td>
						<td><strong>Lname</strong></td>
						<td><strong>Date_out</strong></td>
						<td><strong>Due_date</strong></td>
					</tr>
					<c:set var="count" value="0" scope="page" />
					<c:forEach items="${loan}" var="loan">
						<tr>
							<td id="isbn_${count}"><input type="radio" name="check"
								onclick="setIsbn();" value="${count}"> ${loan.isbn13}</td>
							<td id="card_${count}">${loan.cardId}</td>
							<td>${loan.borrower.fname}</td>
							<td>${loan.borrower.lname}</td>
							<td>${loan.dateOut}</td>
							<td>${loan.dueDate}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<input type="hidden" name="selected-isbn13" id="selected-isbn13" value=""> 
			<input type="hidden" name="card-id" id="card-id" value="">
		</form>
	</div>
</body>
</html>