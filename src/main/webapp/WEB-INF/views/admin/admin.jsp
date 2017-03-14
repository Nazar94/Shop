<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/subcategory">Subcategory</a></li>
					<li><a href="/admin/good">Good</a></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/manufacturer">Manufacturer</a></li>
					<li><a href="/admin/material">Material</a></li>
					<li><a href="/admin/delivery">Delivery</a></li>
						<li><a href="/admin/basket">Basket</a></li>
			
				</ul>
			</div>
		</div>
	</nav>
</div>