<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddBorrower</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
#contact label{
	display: inline-block;
	width: 100px;
	text-align: right;
}
#contact input{
	width: 300px;
}
#contact_submit{
	padding-left: 100px;
}
#contact_submit input{
	width: 100px;
}
#contact div{
	margin-top: 1em;
}
textarea{
	vertical-align: top;
	height: 5em;
}
.error{
	display: none;
	margin-left: 10px;
}		

.error_show{
	color: red;
	margin-left: 10px;
}
input.invalid, textarea.invalid{
	border: 2px solid red;
}

input.valid, textarea.valid{
	border: 2px solid green;
}
#header {
	background-color: black;
	color: white;
	text-align: center;
	overflow: auto;
}

</style>
<script>
function required()
{
    $('span').text("");
    $('input').removeClass('invalid');
    $('input').removeClass('error');
    var form_data=$("#contact").serializeArray();
    var invalid = false;
    for (var input in form_data){
		var element=$("#contact_"+form_data[input]['name']);
                invalid = ValidateInputElement(element);
                
    }

    if(invalid)
     {
        return false;
     }
}

function ValidateInputElement(element)
{
     var isValid = false;
     var re = '';
     var display_text = '';
     var is_validElement = true;
     var element_name = element.attr('name');
     var display_field = element.data('msg');
     var text;
     
     if(element.val() == "")
     {
         element.addClass("invalid");
         var error_element=$("span", element.parent());
         text = display_field  + " is required";
         error_element.text(text);
         error_element.removeClass("error").addClass("error_show");
         return true;
     }
     if(element_name == 'ssn')
     {
       re = /^\d{3}-\d{2}-\d{4}$/;
       display_text = "SSN must be in correct format";
     }
     else if(element_name == 'fname'|| element_name == 'lname' || element_name == 'city'|| element_name == 'state'){
         re = /^[a-zA-Z]*$/;
         display_text = display_field + " should contain alphabets only";
     }
     else if(element_name == 'email'){
        re = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        display_text = "Enter valid email Id";
        
    }
    else if(element_name == 'phone'){
       re = /^\(\d{3}\) ?\d{3}( |-)?\d{4}|^\d{3}( |-)?\d{3}( |-)?\d{4}/;
       display_text = "Enter valid phone number";
	}
    is_validElement = re.test(element.val());
    if(!is_validElement)
     {
      	element.removeClass("valid").addClass("invalid");       
      	var error_element=$("span", element.parent());     
      	error_element.text(display_text );
      	error_element.removeClass("error").addClass("error_show");
       	isValid = true;
    }
    return isValid 
}

</script>
</head>
<body>
<div id="header">
	<h2>Create a new borrower</h2>
</div>
<form id="contact" action="BorrowerManageService" method="post" onsubmit="return required()">
<div>
		<label for="contact_ssn">Ssn:</label>
		<input type="text" id="contact_ssn" name="ssn" placeholder="Enter in format 123-45-6789"></input>
		<span class="error"></span>
                
</div>
<div>
		<label for="contact_fname">First name:</label>
		<input type="text" id="contact_fname" name="fname" placeholder="Enter first name"></input>
		<span class="error"></span>
</div>
<div>
		<label for="contact_lname">Last name:</label>
		<input type="text" id="contact_lname" name="lname" placeholder="Enter last name"></input>
		<span class="error"></span>
</div>
<div>
		<label for="contact_email">Email:</label>
		<input type="text" id="contact_email" name="email" placeholder="Enter valid email"></input>
		<span class="error"></span>
</div>
<div>
		<label for="contact_address">Address:</label>
		<input type="text" id="contact_address" name="address" placeholder="Enter address"></input>
		<span class="error"></span>
</div>
<div>
		<label for="contact_city">City:</label>
		<input type="text" id="contact_city" name="city" placeholder="Enter city"></input>
		<span class="error"></span>
</div>
<div>
		<label for="contact_state">State:</label>
		<input type="text" id="contact_state" name="state" placeholder="Enter state"></input>
		<span class="error"></span>
</div>
<div>
		<label for="contact_phone">Phone:</label>
		<input type="text" id="contact_phone" name="phone" placeholder="Enter in format (123) 456-7890"></input>
		<span class="error"></span>
</div>
<div id="contact_submit">				
		<input type="submit" value="Add">
</div>
</form>
<div class="error_show">
<c:if test="${not empty error}">
   <c:out value="${error}"/>
</c:if>
</div>
<c:if test="${not empty success}">
   <c:out value="${success}"/>
</c:if>
</body>
</html>