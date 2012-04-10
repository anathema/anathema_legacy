package net.sf.anathema.character.equipment.item.personalization;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentPersonalizationModel;

public class EquipmentPersonalizationModel implements IEquipmentPersonalizationModel {

	private String title;
	private String description;
	
	public EquipmentPersonalizationModel(IEquipmentItem item) {
		setTitle(getCustomTitle(item));
		setDescription(getCustomDescription(item));
	}
	
	@Override
	public void setTitle(String text) {
		this.title = text;
	}

	@Override
	public void setDescription(String text) {
		this.description = text;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	private String getCustomTitle(IEquipmentItem item) {
		if (item.getTitle().equals(item.getTemplateId())) {
		  return null;
		}
		return item.getTitle();
	}
	  
	private String getCustomDescription(IEquipmentItem item) {
		if (item.getDescription().equals(item.getBaseDescription())) {
		  return null;
		}
		return item.getDescription();
	}

}
