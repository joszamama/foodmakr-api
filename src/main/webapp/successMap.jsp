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
<title>Nearby Supermarkets</title>
</head>
<body>

	<h1>Find here your nearby supermarkets</h1>

	<div id="mapa">

		<iframe id="mapembed" src="${url}"></iframe>
		<br />

	</div>

	<fieldset id="superm">

		<c:forEach items="${requestScope.places}" var="place">
			<div id="columnas">
				<span><c:out value="${place.name}" /></span> <br /> <span>Address:
					<c:out value="${place.vicinity}" />
				</span> <br /> <span>Rating: <c:out value="${place.rating}" />/5
				</span> <br /> <img
					src="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${place.photos.get(0).photoReference}&key=AIzaSyDZoKEdcapvOACB9iE95Qnxi8vukkqmjDM"
					width="100" height="100"> <br /> <br />
			</div>
		</c:forEach>


	</fieldset>





</body>
</html>