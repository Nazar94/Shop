package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Good;
import ua.service.GoodService;



public class GoodEditor  extends PropertyEditorSupport {

/*	private final GoodService goodService;

	public GoodEditor(GoodService goodService) {
		this.goodService = goodService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Object good = goodService.findOne(Integer.valueOf(text));
		setValue(good);
	}

*/	
}
