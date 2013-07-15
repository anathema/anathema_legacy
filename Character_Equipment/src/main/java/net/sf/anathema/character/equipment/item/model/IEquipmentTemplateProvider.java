package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.equipment.core.IEquipmentTemplate;

public interface IEquipmentTemplateProvider {

  String[] getAllAvailableTemplateIds();

  IEquipmentTemplate loadTemplate(String templateId);
}