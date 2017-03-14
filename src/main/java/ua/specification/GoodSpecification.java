package ua.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.form.GoodFilter;
import ua.entity.Good;

public class GoodSpecification implements Specification<Good> {

	private final GoodFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public GoodSpecification(GoodFilter filter) {
		this.filter = filter;
	}	
	
	private void filterByManufacturer(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getManufacturerIds().isEmpty()){
			predicates.add(root.get("manufacturer").in(filter.getManufacturerIds()));
		}
	}
	
	private void filterByCountry(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getCountryIds().isEmpty()){
			predicates.add(root.get("country").in(filter.getCountryIds()));
		}
	}
	
	private void filterBySubcategory(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getSubcategoryIds().isEmpty()){
			predicates.add(root.get("subcategory").in(filter.getSubcategoryIds()));
		}
	}
	
	private void filterByDelivery(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getDeliveryIds().isEmpty()){
			predicates.add(root.get("delivery").in(filter.getDeliveryIds()));
		}
	}
	
	private void filterByMaterial(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getMaterialIds().isEmpty()){
			predicates.add(root.get("material").in(filter.getMaterialIds()));
		}
	}
	
	private void filterByPrice(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("price"), filter.getMin(), filter.getMax()));
		}else if(filter.getMax()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMax()));
		}else if(filter.getMin()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMin()));
		}
	}
	
	private void filterBySearch(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(root.get("good"), filter.getSearch()+"%"));
		}
	}
	
	
	private void fetch(Root<Good> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("subcategory", JoinType.LEFT);
			root.fetch("manufacturer", JoinType.LEFT);
			root.fetch("material", JoinType.LEFT);
			root.fetch("delivery", JoinType.LEFT);
			root.fetch("country", JoinType.LEFT);
		}
	}
	
	
	@Override
	public Predicate toPredicate(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterBySearch(root, query, cb);
		filterByPrice(root, query, cb);
		filterByManufacturer(root, query, cb);
		filterByMaterial(root, query, cb);
		filterByCountry(root, query, cb);
		filterBySubcategory(root, query, cb);
		filterByDelivery(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}
}
	

