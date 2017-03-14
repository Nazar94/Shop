package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Country;
import ua.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer>, JpaSpecificationExecutor<Material> {

	
	Material findByMaterial(String material);
	
}
