package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Delivery;
import ua.specification.DeliverySpecification;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>, JpaSpecificationExecutor<Delivery> {


	Delivery findByDelivery(String delivery);


	
}
