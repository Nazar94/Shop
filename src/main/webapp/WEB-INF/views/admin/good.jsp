<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
	#filter>.form-group>.col-sm-12>span{
		display: block;
	}
</style>
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
					<li><a href="/admin/good">Good</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/country">Country</a></li>
					<li><a href="/admin/manufacturer">Manufacturer</a></li>
					<li><a href="/admin/material">Material</a></li>
					<li><a href="/admin/delivery">Delivery</a></li>
					<li><a href="/admin/good/basket">Basket</a></li>
				
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
	<form:form class="form-horizontal" action="/admin/good" method="GET" modelAttribute="filter" id="filter">
		<custom:hiddenInputs excludeParams="search, minPrice, maxPrice, manufacturerIds, countryIds, materialIds" excludeParamsPrefix="specDigitFilters"/>
				<div class="form-group">
					<div class="col-sm-12">
	      				<form:input type="text" class="form-control" path="search" placeholder="Search"/>
	    			</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="minPrice" placeholder="Min price"/>
	    			</div>
	    			<div class="col-sm-6 col-xs-6">
	      				<form:input type="text" class="form-control" path="maxPrice" placeholder="Max price"/>
	    			</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Subcategory</label>
    					<div class="col-sm-8">
      						<form:select class="form-control" path="subcategoryIds" itemLabel="subcategory" itemValue="id" items="${subcategories}"/>
    					</div>
    			</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Manufacturer</label>
    					<div class="col-sm-8">
      						<form:select class="form-control" path="manufacturerIds" itemLabel="manufacturer" itemValue="id" items="${manufacturers}"/>
    					</div>
    			</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Material</label>
    					<div class="col-sm-8">
      						<form:select class="form-control" path="materialIds" itemLabel="material" itemValue="id" items="${materials}"/>
    					</div>
    			</div>
				<div class="form-group">
				<label class="col-sm-12 control-label text-center">Country</label>
					<div class="col-sm-12">
						<form:checkboxes items="${countries}" path="countryIds" itemLabel="country" itemValue="id"/>
					</div>
				</div>
				<div class="form-group">
				<label class="col-sm-12 control-label text-center">Delivery</label>
					<div class="col-sm-12">
						<form:checkboxes items="${deliveries}" path="deliveryIds" itemLabel="delivery" itemValue="id"/>
					</div>
				</div>
				
				<div class="form-group">
    				<div class="col-sm-12">
      					<button type="submit" class="btn btn-primary">Search</button>
    				</div>
  				</div>
			</form:form>
	
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/good" method="POST" modelAttribute="good" enctype="multipart/form-data">
  					<div class="form-group">
						<label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="good"/></label>
					</div>
  					<div class="form-group">
    					<label class="col-sm-1 control-label">Good</label>
    					<div class="col-sm-11">
      						<form:input type="text" class="form-control" path="good" id="good"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Subcategory</label>
    					<div class="col-sm-4">
      						<form:select class="form-control" path="subcategory" itemLabel="subcategory" itemValue="id" items="${subcategories}"/>
    					</div>
    					<label class="col-sm-2 control-label">Country</label>
    					<div class="col-sm-4">
      						<form:select class="form-control" path="country" itemLabel="country" itemValue="id" items="${countries}"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Manufacturer</label>
    					<div class="col-sm-4">
      						<form:select class="form-control" path="manufacturer" itemLabel="manufacturer" itemValue="id" items="${manufacturers}"/>
    					</div>
    					<label class="col-sm-2 control-label">Material</label>
    					<div class="col-sm-4">
      						<form:select class="form-control" path="material" itemLabel="material" itemValue="id" items="${materials}"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label class="col-sm-2 control-label">Delivery</label>
    					<div class="col-sm-4">
      						<form:select class="form-control" path="delivery" itemLabel="delivery" itemValue="id" items="${deliveries}"/>
    					</div>
    					<label class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-4">
      						<form:input type="text" class="form-control" path="price" id="price"/>
    					</div>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-offset-2 col-sm-10">
		  					<label class="btn btn-default btn-file">
		        				Browse <input type="file" name="file" style="display: none;">
		    				</label>
	    				</div>
	    			</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
	<div class="col-md-1 col-xs-12">
	<div class="row">
			<div class="col-md-12 col-xs-12 text-center">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="good"/>
						<custom:sort innerHtml="Name desc" paramValue="good,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price desc" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 text-center">
 				<custom:size posibleSizes="1,2,5,10" size="${page.size}" /> 
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>
<div class="col-md-12 col-xs-12 text-center">
<div class="row">
			<div class="col-md-1 col-xs-1"><h5>View</h5></div>
			<div class="col-md-1 col-xs-1"><h5>Good</h5></div>
			<div class="col-md-1 col-xs-1"><h5>Price</h5></div>
			<div class="col-md-1 col-xs-1"><h5>Country</h5></div>
			<div class="col-md-1 col-xs-1"><h5>Delivery</h5></div>
			<div class="col-md-2 col-xs-2"><h5>Subcategory</h5></div>
			
			<div class="col-md-2 col-xs-2"><h5>Manufacturer</h5></div>
			<div class="col-md-1 col-xs-1"><h5>Update</h5></div>
			<div class="col-md-1 col-xs-1"><h5>Delete</h5></div>
		</div>
			<c:forEach items="${page.content}" var="good">
				<div class="row">
				<div class="col-md-1 col-xs-1"><img class="img-rounded" width="100%" src="/images/good/${good.id}.jpg?version=${good.version}"></div>
					<div class="col-md-1 col-xs-1">${good.good}</div>
					<div class="col-md-1 col-xs-1">${good.price}</div>
					<div class="col-md-1 col-xs-1">${good.country.country}</div>
					<div class="col-md-1 col-xs-1">${good.delivery.delivery}</div>
					<div class="col-md-2 col-xs-2">${good.subcategory.subcategory}</div>
					<div class="col-md-2 col-xs-2">${good.manufacturer.manufacturer}</div>
					<div class="col-md-1 col-xs-1"><a class="btn btn-warning" href="/admin/good/update/${good.id}">update</a></div>
					<div class="col-md-1 col-xs-1"><a class="btn btn-danger" href="/admin/good/delete/${good.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
</div>	