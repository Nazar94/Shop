package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.form.BasicFilter;
import ua.entity.Country;
import ua.repository.CountryRepository;
import ua.service.CountryService;
import ua.specification.CountrySpecification;


@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		countryRepository.delete(id);
	}

	@Override
	public void save(Country form) {
		countryRepository.save(form);
	}

	@Override
	public Country findOne(int id) {
		return countryRepository.findOne(id);
	}

	@Override
	public Country findOne(String country) {
		return countryRepository.findByCountry(country);
	}

	@Override
	public Page<Country> findAll(BasicFilter filter, Pageable pageable) {
		return countryRepository.findAll(new CountrySpecification(filter), pageable);
	}
	
}