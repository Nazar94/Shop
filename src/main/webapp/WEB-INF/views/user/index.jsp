<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<!-- <script src="http://bootstrap-ru.com/204/assets/js/bootstrap-carousel.js"></script> -->
<h2>Hello</h2>
<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
	<a href="/admin">admin</a>
	<%-- 	<security:authentication property="principal.email"/> --%>
</security:authorize>
<security:authorize access="!isAuthenticated()">
	<a href="/registration">registration</a>
	<a href="/login">login</a>
</security:authorize>
<div class="col-md-2 col-xs-12"> 
<div class="row"> 
<div class="col-md-6 col-xs-6 text-center"> 
<div class="dropdown"> 
<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span> 
</button> 
<ul class="dropdown-menu"> 
<custom:sort innerHtml="Name asc" paramValue="good"/> 
<custom:sort innerHtml="Name desc" paramValue="good,desc"/> 
<custom:sort innerHtml="Price asc" paramValue="price"/> 
<custom:sort innerHtml="Price asc" paramValue="price,desc"/> 
</ul> 
</div> 
</div> 
<div class="col-md-6 col-xs-6 text-center"> 
<custom:size posibleSizes="1,2,5,10" size="${page.size}" /> 
</div> 
</div> 
</div> 
<div class="row">
	<div class="col-sm-6 col-sm-offset-3">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
			
				<div class="item active">
					<img src="/resources/img/owl.jpg" alt="Chania">
				</div>
				<div class="item">
					<img src="/resources/img/lamp.jpg" alt="Chania">
				</div>
				<div class="item">
					<img src="/resources/img/clock.jpg" alt="Flower">
				</div>
				<div class="item">
					<img src="/resources/img/cof.jpg" alt="Flower">
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
				aria-hidden="true"></span> <span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-12 col-xs-12 parent">

		<c:forEach items="${page.content}" var="good">
			<div>
				<img class="img-rounded" width="100%" src="/images/good/${good.id}.jpg?version=${good.version}">
				<p>${good.good}</p>
	
				<p>${good.price} грн</p>
			
				<div>
					<a href="/buy/${good.id}<custom:allParams/>" class="btn btn-primary">В корзину</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<div class="row"> 
<div class="col-md-12 col-xs-12 text-center"> 
<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" /> 
</div> 
</div> 

<script type="text/javascript">
$("#myCarousel").carousel();
</script>