<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/vnd.microsoft.icon"
	href="images/favicon.ico" sizes="16x16">
<link rel="stylesheet" type="text/css" href="includes/style.css">
<title>Ingredient results</title>
</head>
<body>

	<h1>We have found the following possible meals:</h1>
	<fieldset>


		<c:forEach items="${requestScope.ingredients}" var="ingredient">


			<a title="${ingredient.strMeal}"
				href="/MealController?meal=${ingredient.strMeal}"> <img src="${ingredient.strMealThumb}" alt="${ingredient.strMeal}" width="24%" height="24%"></a>
		</c:forEach>

	</fieldset>

</body>
</html>