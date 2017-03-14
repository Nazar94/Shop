<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">
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
</div>
<div class="row">
	<div class="col-sm-12 col-xs-12 parent">
	<c:set var="s" value="0"></c:set>
		<c:forEach items="${user.goods}" var="good">
			<div>
				<img class="img-rounded" width="100%" src="/images/good/${good.id}.jpg?version=${good.version}">
				<p>${good.good}</p>
				<c:set var="s" value="${s+good.price}"></c:set>
				<p>${good.price} грн</p>
				<div>
					<a href="/basket/deleteGood/${good.id}" class="btn btn-danger">З корзини</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="row">
	<div class="col-sm-12 col-xs-12 ">
	<h1>Сума покупки:</h1>
	<h3>${s} грн</h3>
	</div>
	</div>
