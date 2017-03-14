package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dto.form.SubcategoryFilter;
import ua.entity.Subcategory;
import ua.repository.SubcategoryRepository;
import ua.service.SubcategoryService;
import ua.specification.SubcategorySpecification;
@Service
public class SubcategoryServiceImpl implements  SubcategoryService {

	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Subcategory> findAll() {
		return subcategoryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		subcategoryRepository.delete(id);
	}

	@Override
	public void save(Subcategory form) {
		subcategoryRepository.save(form);
	}

	@Override
	public Subcategory findOne(int id) {
		return subcategoryRepository.findOne(id);
	}

	@Override
	public Page<Subcategory> findAll(SubcategoryFilter filter, Pageable pageable) {
		return subcategoryRepository.findAll(new SubcategorySpecification(filter), pageable);
	}

	@Override
	public Subcategory findOne(String subcategory) {
		return subcategoryRepository.findBySubcategory(subcategory);
	}



}
