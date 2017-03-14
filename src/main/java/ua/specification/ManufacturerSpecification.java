package ua.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.form.BasicFilter;
import ua.entity.Manufacturer;
public class ManufacturerSpecification implements Specification<Manufacturer> {

	
	private final BasicFilter filter;

	public ManufacturerSpecification(BasicFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Manufacturer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("manufacturer"), filter.getSearch()+"%");
	}
	
	
}
