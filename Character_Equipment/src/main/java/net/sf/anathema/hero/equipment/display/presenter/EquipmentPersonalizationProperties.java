package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.framework.environment.Resources;

public class EquipmentPersonalizationProperties {
  private final Resources resources;

  public EquipmentPersonalizationProperties(Resources resources) {
	   this.resources = resources;
  }
  
  public String getPersonalizeMessage() {
	   return resources.getString("Equipment.Personalization.DefaultMessage");
  }
  
  public String getPersonalizeDetails() {
	   return resources.getString("Equipment.Personalization.Details");
 }
  
  public String getTitleMessage() {
	   return resources.getString("Equipment.Personalization.CustomTitle");
  }
  
  public String getDescriptionMessage() {
	   return resources.getString("Equipment.Personalization.CustomDescription");
  }
}
