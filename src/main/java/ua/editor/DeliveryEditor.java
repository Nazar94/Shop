package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Delivery;
import ua.service.DeliveryService;



public class DeliveryEditor extends PropertyEditorSupport {

	private final DeliveryService deliveryService;

	public DeliveryEditor(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Delivery delivery = deliveryService.findOne(Integer.valueOf(text));
		setValue(delivery);
	}
	
	
}
