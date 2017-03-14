package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.form.BasicFilter;
import ua.entity.Delivery;
import ua.repository.DeliveryRepository;
import ua.service.DeliveryService;
import ua.specification.DeliverySpecification;
@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Override
	public List<Delivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		deliveryRepository.delete(id);
	}

	@Override
	public void save(Delivery form) {
		deliveryRepository.save(form);
	}

	@Override
	public Delivery findOne(int id) {
		return deliveryRepository.findOne(id);
	}

	@Override
	public Delivery findOne(String delivery) {
		return deliveryRepository.findByDelivery(delivery);
	}

	@Override
	public Page<Delivery> findAll(BasicFilter filter, Pageable pageable) {
		return deliveryRepository.findAll(new DeliverySpecification(filter), pageable);
	}
	
}