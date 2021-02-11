<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="includes/style2.css">
<link rel="icon" type="image/vnd.microsoft.icon"
	href="images/favicon.ico" sizes="16x16">
<title>Recipe for <c:out value="${param.meal}" /></title>
</head>
<body>
	<h1>
		<c:out value="${param.meal}" />
	</h1>


	<fieldset id="instructions">
		<h2>Recipe</h2>
		<c:forEach items="${requestScope.meal}" var="meal">
			<span><c:out value="${meal.strInstructions}" /></span>
			<br />
		</c:forEach>
	</fieldset>


	<div id="yt">
		<c:choose>
			<c:when test="${not empty param.meal}">
				<c:forEach items="${requestScope.videos.items}" var="item">
					<iframe id = "ytiframe"
						src="https://www.youtube.com/embed/<c:out value="${item.id.videoId}"/>">
					</iframe>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span>No hay resultados.</span>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="mapsbutton">

		<form id="searchMaps" action="GetStartedController" method="post">
			<button type="submit" name="mapsbutton" value="button"
				class="botonmap">Nearby Supermarkets</button>
		</form>
	</div>
	<fieldset id="drive">

		<%
			String controller = "/googleDriveFileNew";
		%>

		<c:if test="${not empty file}">
			<%
				controller = "/googleDriveFileUpdate";
			%>
		</c:if>

		<div class="container">
			<h2>Upload the recipe to Google Drive</h2>
			<p class="message">${message}</p>

			<form action="<%=controller%>" method="post">
				<c:if test="${not empty file}">
					<input type="hidden" name="id" value="${file.id}">
				</c:if>
				<label for="title">File name:</label> <input type="text"
					name="title" id="title"
					<c:if test="${not empty file}">
                   disabled="true" 
                   value="${file.title}"
               </c:if> />
				<br> <br> <label for="recipe">Recipe:</label>

				<c:forEach items="${requestScope.meal}" var="meal">
					<c:set var="recipe" value="${meal.strInstructions}" />

				</c:forEach>

				<textarea id="recipe" name="recipe">${recipe}</textarea>

				<div class="bottom_links" style="width: 50%;">
					<button type="submit"
						onClick="javascript:window.location.href = '/'" class="button">Submit</button>
					<button type="button"
						onClick="javascript:window.location.href = '/'" class="button">Cancel</button>
				</div>
			</form>
		</div>
	</fieldset>





</body>
</html>