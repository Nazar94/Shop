package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Manufacturer;
import ua.service.ManufacturerService;

public class ManufacturerEditor extends PropertyEditorSupport {

	private final ManufacturerService manufacturerService;

	public ManufacturerEditor(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Manufacturer manufacturer = manufacturerService.findOne(Integer.valueOf(text));
		setValue(manufacturer);
	}
	
}
