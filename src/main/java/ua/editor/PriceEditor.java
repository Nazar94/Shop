package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Price;
import ua.service.PriceService;


public class PriceEditor extends PropertyEditorSupport {

	private final PriceService priceService;

	public PriceEditor(PriceService priceService) {
		this.priceService = priceService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Price price = priceService.findOne(Integer.valueOf(text));
		setValue(price);
	}
	
}
