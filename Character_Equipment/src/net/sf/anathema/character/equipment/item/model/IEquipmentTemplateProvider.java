package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public interface IEquipmentTemplateProvider {

  String[] getAllAvailableTemplateIds();

  IEquipmentTemplate loadTemplate(String templateId);
}