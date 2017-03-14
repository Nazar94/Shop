package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>, JpaSpecificationExecutor<Manufacturer> {

	Manufacturer findByManufacturer(String manufacturer);
	
}
