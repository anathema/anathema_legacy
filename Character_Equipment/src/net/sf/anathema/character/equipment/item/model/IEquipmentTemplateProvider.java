package net.sf.anathema.character.equipment.item.model;

import com.db4o.query.Predicate;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public interface IEquipmentTemplateProvider {

  public String[] getAllAvailableTemplateIds();

  public IEquipmentTemplate loadTemplate(String templateId);

  public void queryContainer(Predicate<IEquipmentTemplate> predicate);

}