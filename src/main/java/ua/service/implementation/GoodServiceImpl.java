package ua.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ua.dto.form.GoodFilter;
import ua.dto.form.GoodForm;
import ua.entity.Country;
import ua.entity.Delivery;
import ua.entity.Good;
import ua.entity.Manufacturer;
import ua.entity.Material;
import ua.entity.Subcategory;
import ua.repository.CountryRepository;
import ua.repository.DeliveryRepository;
import ua.repository.GoodRepository;
import ua.repository.ManufacturerRepository;
import ua.repository.MaterialRepository;
import ua.repository.SubcategoryRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.GoodService;
import ua.specification.GoodSpecification;

@Service
public class GoodServiceImpl implements GoodService {

	
	@Autowired
	private GoodRepository goodRepository;
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private ManufacturerRepository smanufacturerRepository;
	
	@Autowired
	private MaterialRepository materialRepository;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	@Transactional(readOnly=true)
	public List<Good> findAll() {
		return goodRepository.findAll();
	}

	@Override
	public void save(Good goods) {
		goodRepository.save(goods);	
	}
	
	@Override
	public void delete(int id) {
		goodRepository.delete(id);
	}

	
	@Override
	@Transactional
	public void save(GoodForm goodForm) {
		Good good = new Good();
		Subcategory subcategory = new Subcategory();
		Country country = new Country();
		Delivery delivery = new Delivery();
		Manufacturer manufacturer = new Manufacturer();
		Material material = new Material();
		good.setId(goodForm.getId());
		good.setSubcategory(goodForm.getSubcategory());
		good.setCountry(goodForm.getCountry());
		good.setDelivery(goodForm.getDelivery());
		good.setGood(goodForm.getGood());
		good.setPrice(new BigDecimal(goodForm.getPrice().replace(',', '.')));
		good.setManufacturer(goodForm.getManufacturer());
		good.setMaterial(goodForm.getMaterial());
		good=goodRepository.saveAndFlush(good);
		if(fileWriter.write(Folder.GOOD, goodForm.getFile(), good.getId())){
//			if(good.getVersion()==null)good.setVersion(0);
//			else good.setVersion(good.getVersion()+1);
		}
		goodRepository.save(good);
	}

	
	@Override
	public Page<Good> findAll(GoodFilter filter, Pageable pageable) {
		Page<Good> goods = goodRepository.findAll(new GoodSpecification(filter),pageable);
		return goods;
	}

	@Override
	public GoodForm findOne(int id) {
		Good entity = goodRepository.findOne(id);
		GoodForm form = new GoodForm();
		form.setCountry(entity.getCountry());
		form.setDelivery(entity.getDelivery());
		form.setManufacturer(entity.getManufacturer());
		form.setMaterial(entity.getMaterial());
		form.setSubcategory(entity.getSubcategory());
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setId(entity.getId());
		return form;
	}

	@Override
	public Good findById(int id) {
		return goodRepository.findOne(id);
	}

	@Override
	public int findCount(int id) {
		Integer count = goodRepository.findCount(id);
		if(count==null)return 0;
		return count;
	}



}
