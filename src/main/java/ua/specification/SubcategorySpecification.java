package ua.specification;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


import ua.dto.form.SubcategoryFilter;
import ua.entity.Subcategory;


public class SubcategorySpecification implements Specification<Subcategory> {

	private final SubcategoryFilter filter;
	private final List<Predicate> predicates = new ArrayList<>();
	public SubcategorySpecification(SubcategoryFilter filter) {
		this.filter = filter;
	}

	private void filterByCategory(Root<Subcategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!(filter.getCategoryIds().isEmpty())){
			predicates.add(root.get("country").in(filter.getCategoryIds()));
		}
	}

	private void fetch(Root<Subcategory> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("category", JoinType.LEFT);
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Subcategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterByCategory(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}
	

	
}
