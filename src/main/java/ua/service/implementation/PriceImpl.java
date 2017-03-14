package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Country;
import ua.entity.Price;
import ua.repository.CountryRepository;
import ua.repository.PriceRepository;
import ua.service.PriceService;
@Service
public class PriceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;
	
	@Override
	public List<Price> findAll() {
		return priceRepository.findAll();
	}

	@Override
	public void delete(int id) {
		priceRepository.delete(id);
	}

	@Override
	public void save(Price form) {
		priceRepository.save(form);
	}

	@Override
	public Price findOne1(int id) {
		return priceRepository.findOne(id);
	}

	@Override
	public Price findOne(int price) {
		return priceRepository.findByPrice(price);
	}

}
