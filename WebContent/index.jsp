<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Search</title>
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 	<style type="text/css">
		#txtbox {
	     	font-size:13pt;
	     	height:10%;
	     	width:40%;
	   	 	margin-top: 5%;
	   	 	margin-left: 25%;
		}
		
		#search {
			height:10%;
			margin-top: 3.5%;
			font-size:12pt;
			text-align:center;
			width:70px;
		}
		
		#error {
			color:red;
			text-align:center;
			font-size:18pt;
		}
		#success {
			color:black;
			text-align:center;
			font-size:18pt;
		}

		h2 {
			text-align: center;
		}
		.container #table1{
			margin-top: 5%;
		}

	</style>
	<script type="text/javascript">
		function checkAvailability() {
			var count = $('input[name=check]:checked').val();
			var noOfCopies = document.getElementById('available_'+count).innerText;
			var isbn = document.getElementById('isbn13_'+count).innerText;
			if (noOfCopies == 0){
				alert("Book is not available for checkout!");
			}
			else{
				var cardId = prompt("Please enter your card id and then click on ok", "");
		    	if (cardId == null || cardId == "") {
		    		cardId = prompt("Please enter your card id and then click on ok", "");
		    	} else {
		    		document.getElementById('card-id').value = cardId;
		    		document.getElementById('selected-isbn13').value = isbn;
		    		document.getElementById("formcheckout").submit();
		    	}
			}
		}
	</script>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<ul class="nav navbar-nav">
		<li><a href="addBorrower.jsp">Add-Borrower</a></li>
		<li><a href="checkInBook.jsp">Check-in</a></li>
		<li><a href="fineTracking.jsp">Fine-Tracking</a></li>
	</ul>
	</nav>
	<div class="container">
		<h2>Library Management System</h2>
	</div>
	<form action="BookSearchService" method="post">
		<input type="text" name="search" placeholder="Search" id="txtbox">
		<input type="submit" value="Search" id="search"> 
	</form>
	<div id="error">
		<c:if test="${not empty errorsearch}">
		   <c:out value="${errorsearch}"/>
		</c:if>
	
		<c:if test="${not empty errorloan}">
		   <c:out value="${errorloan}"/>
		</c:if>
	</div>
	<div id ="success">
		<c:if test="${not empty success}">
   			<c:out value="${success}"/>
		</c:if>
	</div>
	<div class="container">
	<form action="BookCheckOutService" method="post" id="formcheckout">		
			<table class="table table-bordered" id="table1">
				<c:if test="${not empty books}">
					<tr>
						<td><strong>ISBN13</strong></td>
					   	<td><strong>ISBN10</strong></td>
					   	<td><strong>Title</strong></td>
					   	<td><strong>Author</strong></td>
					   	<td><strong>Available</strong></td>
					</tr>
					<c:set var="count" value="0" scope="page"/>
					<c:forEach items="${books}" var="books">				
			  		<tr>
						<td id="isbn13_${count}"><input type="radio" name="check" onclick = "checkAvailability();" value="${count}"> ${books.isbn13}</td>
					   	<td>${books.isbn10}</td>
					   	<td>${books.title}</td>
					   	<td>${books.author.authorName}</td>
					   	<td id="available_${count}">${books.noOfCopies}</td>
					</tr>
					<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
				</c:if>
			</table>
		<input type="hidden" name="selected-isbn13" id="selected-isbn13" value="">
		<input type="hidden" name="card-id" id="card-id" value="">
	</form>
	</div>
</body>
</html>
