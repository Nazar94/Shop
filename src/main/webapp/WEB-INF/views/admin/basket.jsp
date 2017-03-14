<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12 col-xs-12">
				<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/subcategory">Subcategory</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/good">Good</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/manufacturer">Manufacturer</a></li>
					<li><a href="/admin/material">Material</a></li>
					<li><a href="/admin/delivery">Delivery</a></li>
					<li><a href="/basket">Basket</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		<form:form class="form-horizontal" action="/basket" method="GET">
		</form:form>
			<c:forEach items="${users}" var="user" >
	
	${user.username}
	<c:forEach items="${user.goods}" var="good" >
	${good.good.good}
	</c:forEach>
	<br>

		</c:forEach>
		
	</div>
</div>