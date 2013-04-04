package net.sf.anathema.character.equipment.item.personalization;

import net.sf.anathema.lib.resources.Resources;

public class EquipmentPersonalizationProperties {
  private final Resources resources;

  public EquipmentPersonalizationProperties(Resources resources) {
	   this.resources = resources;
  }
  
  public String getPersonalizeMessage() {
	   return resources.getString("Equipment.Personalization.DefaultMessage"); //$NON-NLS-1$    
  }
  
  public String getPersonalizeDetails() {
	   return resources.getString("Equipment.Personalization.Details"); //$NON-NLS-1$    
 }
  
  public String getTitleMessage() {
	   return resources.getString("Equipment.Personalization.CustomTitle"); //$NON-NLS-1$    
  }
  
  public String getDescriptionMessage() {
	   return resources.getString("Equipment.Personalization.CustomDescription"); //$NON-NLS-1$    
  }
}
