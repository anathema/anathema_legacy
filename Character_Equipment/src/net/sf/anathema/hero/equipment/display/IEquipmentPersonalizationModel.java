package net.sf.anathema.hero.equipment.display;

public interface IEquipmentPersonalizationModel {
	void setTitle(String text);
	
	void setDescription(String text);
	
	String getTitle();
	
	String getDescription();
}