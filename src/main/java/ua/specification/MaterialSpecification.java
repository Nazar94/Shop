package ua.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.form.BasicFilter;
import ua.entity.Material;



public class MaterialSpecification implements Specification<Material>  {

	private final BasicFilter filter;

	public MaterialSpecification(BasicFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("material"), filter.getSearch()+"%");
	}
	
}
