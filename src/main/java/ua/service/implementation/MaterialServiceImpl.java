package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ua.dto.form.BasicFilter;
import ua.entity.Material;
import ua.repository.MaterialRepository;
import ua.service.MaterialService;
import ua.specification.MaterialSpecification;
@Service
public class MaterialServiceImpl implements  MaterialService {

	@Autowired
	private MaterialRepository materialRepository;
	
	@Override
	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	@Override
	public void delete(int id) {
		materialRepository.delete(id);
	}

	@Override
	public void save(Material form) {
		materialRepository.save(form);
	}

	@Override
	public Material findOne(int id) {
		return materialRepository.findOne(id);
	}

	@Override
	public Material findOne(String material) {
		return materialRepository.findByMaterial(material);
	}

	@Override
	public Page<Material> findAll(BasicFilter filter, Pageable pageable) {
		return materialRepository.findAll(new MaterialSpecification(filter), pageable);
	}
	
}
