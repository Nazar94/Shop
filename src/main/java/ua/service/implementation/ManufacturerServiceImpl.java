package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.form.BasicFilter;
import ua.entity.Country;
import ua.entity.Manufacturer;
import ua.repository.CountryRepository;
import ua.repository.ManufacturerRepository;
import ua.service.ManufacturerService;
import ua.specification.CountrySpecification;
import ua.specification.ManufacturerSpecification;
@Service
public class ManufacturerServiceImpl implements  ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	
	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}

	@Override
	public void delete(int id) {
		manufacturerRepository.delete(id);
	}

	@Override
	public void save(Manufacturer form) {
		manufacturerRepository.save(form);
	}

	@Override
	public Manufacturer findOne(int id) {
		return manufacturerRepository.findOne(id);
	}

	@Override
	public Manufacturer findOne(String manufacturer) {
		return manufacturerRepository.findByManufacturer(manufacturer);
	}

	@Override
	public Page<Manufacturer> findAll(BasicFilter filter, Pageable pageable) {
		return manufacturerRepository.findAll(new ManufacturerSpecification(filter), pageable);
	}
	

}
