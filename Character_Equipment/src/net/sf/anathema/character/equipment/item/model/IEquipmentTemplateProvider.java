package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public interface IEquipmentTemplateProvider {

  public String[] getAllAvailableTemplateIds();

  public IEquipmentTemplate loadTemplate(String templateId);

}